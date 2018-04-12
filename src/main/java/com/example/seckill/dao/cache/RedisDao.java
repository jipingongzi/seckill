package com.example.seckill.dao.cache;

import com.example.seckill.dao.entity.KillProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 通过redis进行缓存管理
 * @author ibm
 * @since 0
 * @date 2018-4-12
 */
@Repository
public class RedisDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JedisPool jedisPool;

    public RedisDao(@Value("${redis.ip}")String ip,@Value("${redis.port}")int port){
        jedisPool = new JedisPool(ip,port);
    }

    public KillProduct getKillProduct(long killProductId){
        try {
            Jedis jedis = jedisPool.getResource();
            try{
                String key = "killProduct:" + killProductId;

            }finally {
                jedis.close();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    public String putKillProduct(KillProduct killProduct){
        return null;
    }
}
