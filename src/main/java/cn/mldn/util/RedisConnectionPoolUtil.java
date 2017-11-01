package cn.mldn.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnectionPoolUtil {
   private 	static final String REDIS_HOST = "192.168.88.128";
   private static final int READ_PORT = 6379;
   private static final int TIMEOUT = 2000;
   private static final int MAX_TOTAL = 200;
   private static final int MAX_IDLE = 20;
   private static final int MAX_WAIT_MILLS = 1000;
   private static final boolean TEST_ON_BORROW = true;
   private 	static final String REDIS_AUTH = "mldnjava";
   private JedisPool pool = null;
   public RedisConnectionPoolUtil() {
	   JedisPoolConfig config = new JedisPoolConfig();
	   config.setMaxTotal(MAX_TOTAL);
	   config.setMaxIdle(MAX_IDLE);
	   config.setMaxWaitMillis(MAX_WAIT_MILLS);
	   config.setTestOnBorrow(TEST_ON_BORROW);
	   this.pool = new JedisPool(config,REDIS_HOST,READ_PORT,TIMEOUT,REDIS_AUTH);
   }
   public Jedis getConnection() {
	   return this.pool.getResource();   //获取连接
   }
   public void close() {
	   this.pool.close();
   }
}
