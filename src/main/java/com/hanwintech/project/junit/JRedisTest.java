package com.hanwintech.project.junit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hanwintech.project.HanwintechSpringBootApplication;
import com.hanwintech.project.config.JRedisConfiguration;
import com.hanwintech.project.entity.TestTable;
import com.hanwintech.project.util.RedisObjectSerializer;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HanwintechSpringBootApplication.class)
@WebAppConfiguration
public class JRedisTest {
	private static final Logger logger = LoggerFactory.getLogger(JRedisTest.class);
	@Autowired
	private RedisObjectSerializer redisObjectSerializer;
//	@Autowired
//	private RedisTemplate<String, List<TestTable>> redisTemplate2;
	@Autowired
	JedisPool jedisPool;
	@Test
	public void test() throws Exception {

		Jedis jedis =jedisPool.getResource();
		jedis.set("2222", "测试");
	
		logger.info(jedis.get("2222")+"===================================");
		// 保存对象
		
		
//		redisTemplate.opsForValue().set(String.valueOf(user.getId()), user);
		TestTable user2 = new TestTable();
		user2.setName("网吧2");
		user2.setAge(30);
		user2.setId(2);
		TestTable user3 = new TestTable();
		user3.setName("网吧3");
		user3.setAge(40);
		user3.setId(3);
		List<TestTable> t=new ArrayList<TestTable>();
	
		t.add(user2);
		t.add(user3);
		jedis.set(redisObjectSerializer.serialize("333"),redisObjectSerializer.serialize(t));
		
		List<TestTable> t2=(List<TestTable>) redisObjectSerializer.deserialize(jedis.get(redisObjectSerializer.serialize("333")));
		logger.info(t2.size()+"$$$$$$===================================");
//		redisTemplate2.opsForValue().set("5", t);
	}
}
