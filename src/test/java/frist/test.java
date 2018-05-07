package frist;

import org.junit.Test;

import com.lession.utils.JedisPoolUtil;

import redis.clients.jedis.Jedis;

public class test {
	
	@Test
	public void ss() {
		//Jedis jedis = JedisPoolUtil.getJedis();
		
		Byte byte1 = new Byte("127");
		System.out.println(byte1);
		System.out.println(byte1.SIZE);
		System.out.println();
		
		
		//jedis.hset(key, field, value)
	}
}
