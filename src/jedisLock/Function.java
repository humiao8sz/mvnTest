package jedisLock;
import redis.clients.jedis.Jedis;

public interface Function<T> {
	T apply(Jedis jedis) throws Exception;
}
