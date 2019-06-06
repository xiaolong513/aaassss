package com.sofb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

@Configuration
public class RedisConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public JedisConnectionFactory JedisConnectionFactory() {
        String[] ipPortPair = redisProperties.getClusterNodes().split(":");

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(ipPortPair[0].trim());
        redisStandaloneConfiguration.setPort(Integer.valueOf(ipPortPair[1].trim()));
        //redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        jedisClientConfiguration.connectTimeout(Duration.ofMillis(redisProperties.getConnectionTimeout()));
        JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfiguration,
                jedisClientConfiguration.build());
        return factory;
    }

    @Bean
    public RedisTemplate redisClusterTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

       /* ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.PUBLIC_ONLY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);*/

        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisTemplate stringClusterTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

   /* @Bean
    public JedisCluster jedisCluster(RedisProperties redisProperties) {
        String[] serverArray = redisProperties.getClusterNodes().split(",");
        Set<HostAndPort> nodes = new HashSet<>();

        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));

        }
        *//*return new JedisCluster(nodes, redisProperties.getConnectionTimeout(), redisProperties.getSoTimeout(),
                redisProperties.getMaxAttempts(), redisProperties.getPassword(), new JedisPoolConfig());*//*
        return new JedisCluster(nodes);
    }*/

    @Bean
    public JedisPool redisPoolFactory(RedisProperties redisProperties) {
        String[] ipPortPair = redisProperties.getClusterNodes().split(":");

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisProperties.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getMaxWaitMillis());
        jedisPoolConfig.setMaxTotal(redisProperties.getMaxTotal());
        jedisPoolConfig.setMinIdle(redisProperties.getMinIdle());
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim()),
                redisProperties.getConnectionTimeout(), null);

        return jedisPool;
    }


}
