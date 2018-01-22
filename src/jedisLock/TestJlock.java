package jedisLock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TestJlock {
	public static int a = 0;
	public static void main(String args[]) {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(10);
		poolConfig.setMaxIdle(10);
		poolConfig.setMaxWaitMillis(50000);
		poolConfig.setTestOnBorrow(true);
		final JedisPool jedisPool = new JedisPool(poolConfig, "192.168.0.25", 6379, 10000, "testredis123", 5);
		
		TestJlock testJlock = new TestJlock();
		JedisLockManager jedisLockManager = testJlock.initJeidsPool().getInstance();
		jedisLockManager.startPubSub();
		Thread aThread = new Thread(new Runnable() {
			public void run() {
				for(int i = 0;i<100;i++){
					try{
						Thread.currentThread().sleep(200L);
					}catch(InterruptedException e){
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JedisReentrantLock lock = new JedisReentrantLock("TESTJLOCK",Integer.MAX_VALUE);
					lock.lock();
					
					Jedis jedis = jedisPool.getResource();
					int num = Integer.parseInt(jedis.get("REWQ"));
					num++;
					System.out.println(Thread.currentThread().getId()+":"+num);
					jedis.set("REWQ", String.valueOf(num));
					jedisPool.returnResource(jedis);
					
					lock.unlock();					
				}
			}
		});
		Thread bThread = new Thread(new Runnable() {
			public void run() {
				for(int i = 0;i<100;i++){
					try{
						Thread.currentThread().sleep(200L);
					}catch(InterruptedException e){
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JedisReentrantLock lock = new JedisReentrantLock("TESTJLOCK",Integer.MAX_VALUE);
					lock.lock();
					
					Jedis jedis = jedisPool.getResource();
					int num = Integer.parseInt(jedis.get("REWQ"));
					num++;
					System.out.println(Thread.currentThread().getId()+":"+num);
					jedis.set("REWQ", String.valueOf(num));
					jedisPool.returnResource(jedis);

					lock.unlock();
				}
			}
		});
		
		Thread cThread = new Thread(new Runnable() {
			public void run() {
				for(int i = 0;i<100;i++){
					try{
						Thread.currentThread().sleep(200L);
					}catch(InterruptedException e){
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JedisReentrantLock lock = new JedisReentrantLock("TESTJLOCK",Integer.MAX_VALUE);
					lock.lock();

					Jedis jedis = jedisPool.getResource();
					int num = Integer.parseInt(jedis.get("REWQ"));
					num++;
					System.out.println(Thread.currentThread().getId()+":"+num);
					jedis.set("REWQ", String.valueOf(num));
					jedisPool.returnResource(jedis);

					lock.unlock();
				}
			}
		});

		Thread dThread = new Thread(new Runnable() {
			public void run() {
				for(int i = 0;i<100;i++){
					try{
						Thread.currentThread().sleep(200L);
					}catch(InterruptedException e){
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JedisReentrantLock lock = new JedisReentrantLock("TESTJLOCK",Integer.MAX_VALUE);
					lock.lock();

					Jedis jedis = jedisPool.getResource();
					int num = Integer.parseInt(jedis.get("REWQ"));
					num++;
					System.out.println(Thread.currentThread().getId()+":"+num);
					jedis.set("REWQ", String.valueOf(num));
					jedisPool.returnResource(jedis);

					lock.unlock();
				}
			}
		});

		aThread.start();
		bThread.start();
		cThread.start();
		dThread.start();
		try{
			Thread.sleep(25000L);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println(jedisLockManager.getJedisPubSub().getSemaphoreMap().size());
		System.out.println(jedisLockManager.getJedisPubSub().getSubscribedChannels());
	}
	
	public JedisLockManager initJeidsPool(){
		// 池基本配置
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(10);
		poolConfig.setMaxIdle(10);
		poolConfig.setMaxWaitMillis(Integer.MAX_VALUE);
		poolConfig.setTestOnBorrow(false);
		//"192.168.0.25", 6379, 10000, "testredis123", 5)
		JedisPool jedisPool = new JedisPool(poolConfig, "192.168.0.25", 6379, Integer.MAX_VALUE, "testredis123", 5);
		
		
		// 池基本配置
		JedisPoolConfig poolConfig2 = new JedisPoolConfig();
		poolConfig2.setMaxTotal(1);
		poolConfig2.setMaxIdle(1);
		poolConfig2.setMaxWaitMillis(Integer.MAX_VALUE);
		poolConfig2.setTestOnBorrow(false);
		JedisPool jedisPool2 = new JedisPool(poolConfig2, "192.168.0.25", 6379, Integer.MAX_VALUE, "testredis123", 5);
		JedisLockManager jedisLockManager = new JedisLockManager(jedisPool,jedisPool2);
		return jedisLockManager;
	}
}
