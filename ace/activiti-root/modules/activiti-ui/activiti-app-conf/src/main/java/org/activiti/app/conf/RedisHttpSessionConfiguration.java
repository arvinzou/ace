package org.activiti.app.conf;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionStrategy;

import redis.clients.jedis.JedisPoolConfig;

import com.huacainfo.ace.common.redis.AspireRedisClusterConfiguration;
import com.huacainfo.ace.common.redis.AspireRedisTemplate;
@Configuration
@EnableRedisHttpSession(redisNamespace="portal",maxInactiveIntervalInSeconds=1800,redisFlushMode=RedisFlushMode.IMMEDIATE)
public class RedisHttpSessionConfiguration {
	
	private static final Logger logger = LoggerFactory
			.getLogger(RedisHttpSessionConfiguration.class);
	private Properties properties=new Properties();
	public RedisHttpSessionConfiguration(){
		try {
			this.properties.load(RedisHttpSessionConfiguration.class
					.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			logger.error("", e);
		}
	}
	@Bean(name = "redisClusterConfiguration")
	public RedisClusterConfiguration getRedisClusterConfiguration(){
		int maxRedirects = Integer.valueOf(properties
				.getProperty("redis.cluster.maxRedirects"));
		String redisClusterNodes = properties.getProperty("redis.cluster.serverList");
		AspireRedisClusterConfiguration cfg = new AspireRedisClusterConfiguration();
		cfg.setMaxRedirects(maxRedirects);
		cfg.setRedisClusterNodes(redisClusterNodes);
		this.logger.info("==========redis.cluster.serverList========>{}", redisClusterNodes);
		return cfg;
	}

	@Bean(name = "jedisPoolConfig")
	public JedisPoolConfig getJedisPoolConfig()  {
		int maxTotal= Integer.valueOf(properties
				.getProperty("redis.cluster.maxTotal"));
		int maxIdle= Integer.valueOf(properties
				.getProperty("redis.cluster.maxIdle"));
		int maxWaitMillis= Integer.valueOf(properties
				.getProperty("redis.cluster.maxWaitMillis"));
		JedisPoolConfig cfg = new JedisPoolConfig();
		cfg.setMaxTotal(maxTotal);
		cfg.setMaxIdle(maxIdle);
		cfg.setMaxWaitMillis(maxWaitMillis);
		this.logger.info("==========jedisPoolConfig========>", cfg);
		return cfg;
	}
	
	@Bean
    public RedisConnectionFactory connectionFactory() {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(this.getRedisClusterConfiguration(),this.getJedisPoolConfig());
        logger.info("====================connectionFactory========================");
        return connectionFactory;
    }
	@Bean(name = "cookieSerializer")
	public DefaultCookieSerializer getCookieSerializer(){
		String cookiePath=properties
		.getProperty("spring.session.cookiePath");
		DefaultCookieSerializer cfg = new DefaultCookieSerializer();
		cfg.setCookiePath(cookiePath);
		this.logger.info("==========cookieSerializer========>", cfg);
		return cfg;
	}
	@Bean(name = "redisTemplateString")
	public AspireRedisTemplate getAspireRedisTemplate(){
		AspireRedisTemplate cfg = new AspireRedisTemplate();
		cfg.setConnectionFactory(this.connectionFactory());
		this.logger.info("==========redisTemplateString========>", cfg);
		return cfg;
	}
	
	@Bean(name = "httpSessionStrategy")
	public HttpSessionStrategy getHttpSessionStrategy(){
		CookieHttpSessionStrategy cfg = new CookieHttpSessionStrategy();
		cfg.setCookieSerializer(this.getCookieSerializer());
		this.logger.info("==========httpSessionStrategy========>", cfg);
		return cfg;
	}
	
}
