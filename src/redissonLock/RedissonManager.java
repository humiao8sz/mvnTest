package redissonLock;


import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import io.netty.util.internal.StringUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedissonManager {

    //private static final String RAtomicName = "genId_";

    private static Config config = new Config();
    private static RedissonClient redisson = null;
    public static JedisPool jedisPool = null;

    public static void init(){
        try {
    		JedisPoolConfig poolConfig = new JedisPoolConfig();
    		poolConfig.setMaxTotal(100);
    		poolConfig.setMaxIdle(2);
    		poolConfig.setMaxWaitMillis(50000);
    		poolConfig.setTestOnBorrow(true);
    		jedisPool = new JedisPool(poolConfig, "192.168.0.25", 6379, 10000, "testredis123", 5);
        	
            config.useSingleServer() //这是用的集群server
                    .setAddress("redis://192.168.0.25:6379")
                    .setPassword("testredis123")
                    .setDatabase(5);
            config.setUseLinuxNativeEpoll(false);
            redisson = Redisson.create(config);
            //清空自增的ID数字
            RAtomicLong atomicLong = redisson.getAtomicLong("RAtomicName");
            atomicLong.set(1);
            
            RLock rlock =  redisson.getLock("TTT");
            rlock.lock();
            
            rlock.unlock();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static RedissonClient getRedisson(){
        return redisson;
    }

    /** 获取redis中的原子ID */
    public static Long nextID(){
        RAtomicLong atomicLong = getRedisson().getAtomicLong("RAtomicName");
        atomicLong.incrementAndGet();
        return atomicLong.get();
    }
    
    public static void main (String[] args){
    	//init();
    	redisLock();
    }
    
    private static void redisLock(){
        RedissonManager.init(); //初始化
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String key = "test123";
                        for(int k =0;k<100;k++){
                            DistributedRedisLock.acquire(key);
                            Jedis jedis = jedisPool.getResource();
                            String index = jedis.get("TTTTTTTT");
                            if(StringUtil.isNullOrEmpty(index)){
                            	jedis.set("TTTTTTTT", "1");
                            	System.out.println(1);
                            }else{
                            	int value = Integer.parseInt(index)+1;
                            	jedis.set("TTTTTTTT", String.valueOf(value));
                            	System.out.println(value);
                            }
                            jedisPool.returnResourceObject(jedis);
                            DistributedRedisLock.release(key);                        	
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
    }
}