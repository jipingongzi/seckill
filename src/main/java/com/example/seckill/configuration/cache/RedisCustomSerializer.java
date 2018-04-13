package com.example.seckill.configuration.cache;

import com.example.seckill.dao.entity.KillProduct;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;

/**
 * 自定义的redis序列化
 * @author ibm
 * @since 0
 * @date 2018-4-22
 */
public class RedisCustomSerializer implements RedisSerializer {

    private final RuntimeSchema<KillProduct> schema = RuntimeSchema.createFrom(KillProduct.class);

    @Nullable
    @Override
    public byte[] serialize(@Nullable Object o) throws SerializationException {
        KillProduct killProduct = (KillProduct)o;
        byte[] bytes = ProtostuffIOUtil.toByteArray(killProduct,schema,
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        return bytes;
    }

    @Nullable
    @Override
    public Object deserialize(@Nullable byte[] bytes) throws SerializationException {
        if(bytes != null){
            KillProduct killProduct = schema.newMessage();
            //反序列化
            ProtostuffIOUtil.mergeFrom(bytes,killProduct,schema);
            return killProduct;
        }else {
            return null;
        }
    }
}
