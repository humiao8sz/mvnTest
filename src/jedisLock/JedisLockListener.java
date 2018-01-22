package jedisLock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import redis.clients.jedis.JedisPubSub;

/**
 * 
 * @author humiao
 *
 */
public class JedisLockListener extends JedisPubSub {
	private ConcurrentHashMap<String, List<Semaphore>> semaphoreMap = new ConcurrentHashMap<String, List<Semaphore>>();
	
	public ConcurrentHashMap<String, List<Semaphore>> getSemaphoreMap() {
		return semaphoreMap;
	}

	public void subscribeChanel(String channel,Semaphore semaphore){
		synchronized(semaphoreMap){
			if(!semaphoreMap.containsKey(channel)){
				this.subscribe(channel);
				semaphoreMap.put(channel, Collections.synchronizedList(new ArrayList<Semaphore>()));
			}
			semaphoreMap.get(channel).add(semaphore);			
		}
	}
	
	public void removeSemaphore(String channel,Semaphore semaphore){
		synchronized(semaphoreMap){
			if(semaphoreMap.containsKey(channel)){
				semaphoreMap.get(channel).remove(semaphore);
				if(semaphoreMap.get(channel).isEmpty()){
					this.unsubscribe(channel);
					semaphoreMap.remove(channel);
				}
			}			
		}
	}

	public void onMessage(String channel, String message) {
		synchronized(semaphoreMap){
			if(semaphoreMap.containsKey(channel)){
				for(Semaphore semaphore : semaphoreMap.get(channel)){
					semaphore.release();
				}				
			}
		}
	}
}
