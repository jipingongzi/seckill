package com.example.seckill.dao.cache;

import com.example.seckill.dao.entity.KillProduct;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 通过redis进行缓存管理
 * 通过 进行序列化处理
 * @author ibm
 * @since 0
 * @date 2018-4-12
 */
@Repository
public class RedisDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JedisPool jedisPool;
    private final String BASE_KEY = "killProduct:";

    public RedisDao(@Value("${redis.ip}")String ip,@Value("${redis.port}")int port){
        jedisPool = new JedisPool(ip,port);
    }

    private RuntimeSchema<KillProduct> schema = RuntimeSchema.createFrom(KillProduct.class);

    /**
     * 通过redis获取一个缓存对象
     * @param killProductId 秒杀商品id
     * @return 秒杀商品
     */
    public KillProduct getKillProduct(String killProductId){
        try {
            try(Jedis jedis = jedisPool.getResource();){
                String key = BASE_KEY + killProductId;
                byte[] bytes = jedis.get(key.getBytes());
                if(bytes != null){
                    KillProduct killProduct = schema.newMessage();
                    //反序列化
                    ProtostuffIOUtil.mergeFrom(bytes,killProduct,schema);
                    return killProduct;
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 存储一个对象到redis
     * @param killProduct 需要存储的秒杀商品
     * @return 成功的话redis返回：OK
     */
    public String putKillProduct(KillProduct killProduct){
        try{
            try (Jedis jedis = jedisPool.getResource()){
                String key = BASE_KEY + killProduct.getId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(killProduct,schema,
                    LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //缓存一小时
                int timeout = 60 * 60;
                String result = jedis.setex(key.getBytes(),timeout,bytes);
                return result;
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }
}
