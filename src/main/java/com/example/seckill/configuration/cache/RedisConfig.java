package com.example.seckill.configuration.cache;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.lang.Nullable;

import java.time.Duration;

/**
 * redis缓存配置类
 * @author ibm
 * @since 0
 * @date 2018-4-12
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Override
    @Nullable
    @Bean
    public KeyGenerator keyGenerator() {
        return new RedisCustomKeyGenerator();
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        RedisCustomSerializer customSerializer = new RedisCustomSerializer();
        template.setValueSerializer(customSerializer);
        template.afterPropertiesSet();
        return template;
    }

    /**
     *  设置 redis 数据默认过期时间
     *  设置@cacheable 序列化方式
     * @return
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){
        RedisCustomSerializer customSerializer = new RedisCustomSerializer();
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer
        (customSerializer)).entryTtl(Duration.ofHours(1));
        return configuration;
    }
}
