package com.lession.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	/*public static LinkedList<Jedis> pool = (LinkedList<Jedis>)Collections.synchronizedCollection(new LinkedList<Jedis>());
	*/
	public static JedisPoolConfig config;
	public static JedisPool jedisPool;
	static {
		try {
			config=new JedisPoolConfig(); //连接池的配置对象
	        config.setMaxTotal(100); //设置最大连接数
	        config.setMaxIdle(10); //设置最大空闲连接数
	        jedisPool=new JedisPool(config,"18.188.89.37",6379,10000);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized static Jedis getJedis() {
		Jedis jedis=null;
		try {
			 jedis=jedisPool.getResource(); // 获取连接
		     jedis.auth("123456"); // 设置密码
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jedis;
	}	
}
