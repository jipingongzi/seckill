package com.example.seckill.configuration.cache;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

/**
 * 自定义的redis缓存key生成策略
 * @author ibm
 * @since 0
 * @date 201804013
 */
public class RedisCustomKeyGenerator implements KeyGenerator {

    /**
     * 简单的指定生成killProduct的缓存id，这里可以根据业务类型自定义所有的key生成策略
     * @param target   被调用方法的类实例
     * @param method  方法的名称
     * @param params 方法的参数
     * @return 缓存key
     */
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return params[0];
    }

    /**
     * 提供redisTemplate使用的key查询方法
     * @param cacheName 缓存名称
     * @return 缓存的key前缀
     */
    public static final String getKey4CacheName(String cacheName){
        //spring在生成key的时候会用cacheName::的前缀
        return cacheName + "::";
    }
}
