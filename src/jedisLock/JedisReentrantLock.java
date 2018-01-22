package jedisLock;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class JedisReentrantLock implements Lock {
    private String name;
	final UUID id = UUID.randomUUID();
	protected long internalLockLeaseTime;
	
	/**
	 * @param name Redis Key
	 * @param internalLockLeaseTime 锁有效时间（毫秒） 超时后锁自动释放，防止锁释放失败后导致永久占有
	 */
    public JedisReentrantLock(String name,long internalLockLeaseTime) {
        this.name = name;
        this.internalLockLeaseTime = internalLockLeaseTime;
    }

    //尝试获取锁Lua
	public static String acquireLuaScript = "if (redis.call('exists', KEYS[1]) == 0) then " + "redis.call('hset', KEYS[1], ARGV[2], 1); "
			+ "redis.call('pexpire', KEYS[1], ARGV[1]); " + "return nil; " + "end; " + "if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then "
			+ "redis.call('hincrby', KEYS[1], ARGV[2], 1); " + "redis.call('pexpire', KEYS[1], ARGV[1]); " + "return nil; " + "end; "
			+ "return redis.call('pttl', KEYS[1]);";
	//释放锁Lua
	public static String unlockLuaScript = "if (redis.call('exists', KEYS[1]) == 0) then " + "redis.call('publish', KEYS[2], ARGV[1]); "
			+ "return 1; " + "end;" + "if (redis.call('hexists', KEYS[1], ARGV[3]) == 0) then " + "return nil;" + "end; "
			+ "local counter = redis.call('hincrby', KEYS[1], ARGV[3], -1); " + "if (counter > 0) then " + "redis.call('pexpire', KEYS[1], ARGV[2]); "
			+ "return 0; " + "else " + "redis.call('del', KEYS[1]); " + "redis.call('publish', KEYS[2], ARGV[1]); " + "return 1; " + "end; "
			+ "return nil;";
    
    public String getName() {
		return name;
	}

    protected String getEntryName() {
        return id + ":" + getName();
    }

    protected String prefixName(String prefix, String name) {
        if (name.contains("{")) {
            return prefix + ":" + name;
        }
        return prefix + ":{" + name + "}";
    }
    
    /**
     * 订阅的channel
     * @return
     */
    String getChannelName() {
        return prefixName("redisson_lock_channel", getName());
    }

    String getLockName(long threadId) {
        return id + ":" + threadId;
    }

    @Override
    public void lock() {
        try {
            lockInterruptibly();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * 
     * @param leaseTime 最大持有锁时间，超时后自动释放
     * @param unit 时间单位
     */
    public void lock(long leaseTime, TimeUnit unit) {
        try {
            lockInterruptibly(leaseTime, unit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        lockInterruptibly(internalLockLeaseTime, TimeUnit.MILLISECONDS);
    }

    public void lockInterruptibly(long leaseTime, TimeUnit unit) throws InterruptedException {
        long threadId = Thread.currentThread().getId();
        Long ttl = tryAcquire(leaseTime, unit, threadId);
        // lock acquired
        if (ttl == null) {
            return;
        }
        Semaphore semaphore = new Semaphore(0);
        try {
        	JedisLockManager.getInstance().getJedisPubSub().subscribeChanel(getChannelName(), semaphore);
            while (true) {
                ttl = tryAcquire(leaseTime, unit, threadId);
                // lock acquired
                if (ttl == null) {
                    break;
                }
                // waiting for message
                if (ttl >= 0) {
                	semaphore.tryAcquire(ttl, TimeUnit.MILLISECONDS);
                } else {
                	semaphore.acquire();
                }
            }
        } finally {
        	JedisLockManager.getInstance().getJedisPubSub().removeSemaphore(getChannelName(), semaphore);
        }
    }

    @Override
    public void unlock() {
    	JedisLockManager.getInstance().eval(unlockLuaScript, Arrays.asList(getName(),getChannelName()),Arrays.asList("0",String.valueOf(internalLockLeaseTime),getLockName(Thread.currentThread().getId())));
    }

	@Override
	public boolean tryLock() {
		Long ttl = tryAcquire(internalLockLeaseTime, TimeUnit.MILLISECONDS, Thread.currentThread().getId());
        if (ttl == null) {
            return true;
        }
        return false;
	}

	/**
	 * @param waitTime 尝试获取锁时最大等待时间，超时还未获取到返回false
     * @param unit 时间单位
	 */
	@Override
	public boolean tryLock(long waitTime, TimeUnit unit) throws InterruptedException {
        long time = unit.toMillis(waitTime);
        long current = System.currentTimeMillis();
        final long threadId = Thread.currentThread().getId();
        Long ttl = tryAcquire(internalLockLeaseTime, TimeUnit.MILLISECONDS, threadId);
        // lock acquired
        if (ttl == null) {
            return true;
        }
        
        time -= (System.currentTimeMillis() - current);
        if (time <= 0) {
            return false;
        }
        
        current = System.currentTimeMillis();
        Semaphore semaphore = new Semaphore(0);
        try {
            time -= (System.currentTimeMillis() - current);
            if (time <= 0) {
                return false;
            }
            JedisLockManager.getInstance().getJedisPubSub().subscribeChanel(getChannelName(), semaphore);
            while (true) {
                long currentTime = System.currentTimeMillis();
                ttl = tryAcquire(internalLockLeaseTime, TimeUnit.MILLISECONDS, threadId);
                // lock acquired
                if (ttl == null) {
                    return true;
                }

                time -= (System.currentTimeMillis() - currentTime);
                if (time <= 0) {
                    return false;
                }

                // waiting for message
                currentTime = System.currentTimeMillis();
                if (ttl >= 0 && ttl < time) {
                    semaphore.tryAcquire(ttl, TimeUnit.MILLISECONDS);
                } else {
                	semaphore.tryAcquire(time, TimeUnit.MILLISECONDS);
                }

                time -= (System.currentTimeMillis() - currentTime);
                if (time <= 0) {
                    return false;
                }
            }
        } finally {
        	JedisLockManager.getInstance().getJedisPubSub().removeSemaphore(getChannelName(), semaphore);
        }
	}

    private Long tryAcquire(long leaseTime, TimeUnit unit, long threadId) {
        Object object = JedisLockManager.getInstance().eval(acquireLuaScript, Arrays.asList(getName()),Arrays.asList(String.valueOf(unit.toMillis(leaseTime)),getLockName(threadId)));
        if(object == null){
        	return null;
        }
        return (Long)object;
    }
    
	@Override
	public Condition newCondition() {
		return null;
	}
}