package jedisLock;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisLockManager {
	private static final Log log = LogFactory.getLog(JedisLockManager.class);
	private static JedisLockManager instance = null;
	private JedisPool jedisPool;
	private JedisPool jedisPubSubPool;
	final JedisLockListener jedisPubSub = new JedisLockListener();
	
	public JedisLockManager(JedisPool jedisPool,JedisPool jedisPubSubPool){
		instance = this;
		this.jedisPool = jedisPool;
		this.jedisPubSubPool = jedisPubSubPool;
	}
	
	/**
	 * 获取实例对象
	 * 
	 * @return
	 */
	public static JedisLockManager getInstance() {
		return instance;
	}

	/**
	 * 获取jedis里面key值为ext+jedisKeyRule的value值
	 * 
	 * @param ext
	 * @param jedisKeyRule
	 * @return
	 */
	public String get(final short jedisKeyRule, final Object[] ext) {
		return instance.doInJedis(new Function<String>() {
			public String apply(Jedis jedis) {
				return jedis.get(JedisKeyRule.getExtKey(jedisKeyRule, ext));
			}
		});
	}
	
	public Object eval(final String luaStr, final List<String> keyList, final List<String> argvList) {
		return instance.doInJedis(new Function<Object>() {
			@Override
			public Object apply(Jedis jedis) throws Exception {
				return jedis.eval(luaStr, keyList, argvList);
			}
		});
	}
	
	/**
	 * 直接调用redis [注意：此方法已异常常用异常捕获
	 *
	 * @param poolName
	 *            对应的redis池
	 * @param function
	 *            内部方法
	 */
	public <T> T doInJedis(Function<T> function) {
		try{
			Jedis jedis = jedisPool.getResource();

			// 防止第一次没拿到
			if(jedis == null){
				jedis = jedisPool.getResource();
			}
			if(jedis == null){
				log.error("jedis is null!" );
				return null;
			}
			try{
				Date now = new Date();
				T apply = function.apply(jedis);
				Date now2 = new Date();
				if(now2.getTime() - now.getTime() > 200){
					StringBuilder logSb = new StringBuilder();
					for(StackTraceElement b : Thread.currentThread().getStackTrace()){
						logSb.append("			").append(b.toString()).append("\n");
					}
					log.error("操作redis花费的时间:" + (now2.getTime() - now.getTime()) + "\n" + logSb.toString());
				}
				return apply;
			}finally{
				// 返回pool
				returnPool(jedis);
			}
		}catch(Exception e){
			log.error("", e);
			//上报 
			//ReportUtil.redisError();
		}
		return null;
	}

	/**
	 * 释放jedis
	 *
	 * @param jedis
	 * @param jedisPool
	 */
	@SuppressWarnings("deprecation")
	private void returnPool(Jedis jedis) {
		try{
			if(jedis != null && jedisPool != null){
				jedisPool.returnResourceObject(jedis);
			}
		}catch(Exception e){
			log.error("||   >>>>>>>>>   ||  JedisManager returnPool method throw Excepton: ", e);
		}
	}

	/**
	 * 获取一个Jedis实例
	 * @return
	 */
	synchronized Jedis getSubJedis(){
		return jedisPubSubPool.getResource();
	}
	
	/**
	 * 释放jedis
	 *
	 * @param jedis
	 * @param jedisPool
	 */
	@SuppressWarnings("deprecation")
	synchronized void returnSubPool(Jedis jedis) {
		try{
			if(jedis != null && jedisPubSubPool != null){
				jedisPubSubPool.returnResourceObject(jedis);
			}
		}catch(Exception e){
			log.error("||   >>>>>>>>>   ||  JedisManager jedisPubSubPool method throw Excepton: ", e);
		}
	}
	
	/**
	 * 所有的redis都回收掉
	 */
	public void destroy() {
		jedisPool.destroy();
	}

	public void setJedisConfig(JedisConfigVO config) {
		try{
			// 池基本配置
			JedisPoolConfig poolConfig = new JedisPoolConfig();
			poolConfig.setMaxTotal(config.getMaxActive());
			poolConfig.setMaxIdle(config.getMaxIdle());
			poolConfig.setMaxWaitMillis(config.getMaxWait());
			poolConfig.setTestOnBorrow(config.isTestOnBorrow());
			log.error("redis ip:" + config.getConnIp() + " auto:" + config.getAuth() + " dateIndex:" + config.getDatabase());
			jedisPool = new JedisPool(poolConfig, config.getConnIp(), config.getConnPort(), config.getConnTimeOut(), config.getAuth(), config.getDatabase());
			instance = this;
		}catch(IllegalArgumentException e){
			log.error("", e);
		}
	}

	public void setSubJedisConfig(JedisConfigVO config) {
		try{
			// 池基本配置
			JedisPoolConfig poolConfig = new JedisPoolConfig();
			poolConfig.setMaxTotal(config.getMaxActive());
			poolConfig.setMaxIdle(config.getMaxIdle());
			poolConfig.setMaxWaitMillis(config.getMaxWait());
			poolConfig.setTestOnBorrow(config.isTestOnBorrow());
			log.error("redis ip:" + config.getConnIp() + " auto:" + config.getAuth() + " dateIndex:" + config.getDatabase());
			jedisPubSubPool = new JedisPool(poolConfig, config.getConnIp(), config.getConnPort(), config.getConnTimeOut(), config.getAuth(), config.getDatabase());
			startPubSub();
		}catch(IllegalArgumentException e){
			log.error("", e);
		}
	}
	
	/**
	 * 启动发布订阅线程
	 */
	public void startPubSub() {
    	new Thread(new Runnable() {
			public void run() {
				jedisPubSubPool.getResource().subscribe(jedisPubSub,"LockTemp");
			}
		}).start();
	}
	
	public JedisLockListener getJedisPubSub() {
		return jedisPubSub;
	}
}
