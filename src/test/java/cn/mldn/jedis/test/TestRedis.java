package cn.mldn.jedis.test;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import cn.mldn.util.RedisConnectionUtil;
import redis.clients.jedis.Jedis;

public class TestRedis {
   public static Jedis jedis = null;
   static {
	   RedisConnectionUtil rcu = new RedisConnectionUtil();
	   jedis = rcu.getConnection();
   }
   @Test
   public void testDemoA() throws Exception{
	   jedis.setex("mldn",3,"helloworld");
	   TimeUnit.SECONDS.sleep(4);
	   System.err.println(jedis.get("mldn"));
   }
   @Test
   public void testDataHash() throws Exception{
	   jedis.hset("use-mldn","name","十一月到了");
	   jedis.hset("use-mldn","age",String.valueOf(11));
	   jedis.hset("use-mldn", "sex","男");
	   System.err.println(jedis.hget("use-mldn", "name"));
   }
   @Test
   public void testDataList() throws Exception{
	   jedis.flushDB();
	   jedis.lpush("user-mldn", "A","B","C");
	   System.err.println(jedis.rpop("user-mldn"));
	   System.err.println(jedis.rpop("user-mldn"));
	   System.err.println(jedis.rpop("user-mldn"));
	   System.err.println(jedis.rpop("user-mldn"));
   }
   @Test
   public void testKeys() throws Exception{
	   jedis.flushDB();
	   jedis.set("user-mldn","ceshi1");
	   jedis.set("user-mldn","ceshi2");
	   Set<String> keys = 	jedis.keys("user-*");
	   Iterator<String> iter = keys.iterator();
	   while(iter.hasNext()) {
		   String key = iter.next();
		   System.err.println(key+"="+jedis.get(key));
	   }
   }
}
