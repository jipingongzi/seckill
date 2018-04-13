package com.example.seckill.queryService.impl;

import com.example.seckill.common.utils.Md5Util;
import com.example.seckill.configuration.cache.RedisCacheName;
import com.example.seckill.configuration.cache.RedisCustomKeyGenerator;
import com.example.seckill.dao.entity.KillProduct;
import com.example.seckill.dao.repository.KillProductJpaRepo;
import com.example.seckill.dto.Exposer;
import com.example.seckill.queryService.ISecKillQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = RedisCacheName.KILL_PRODUCT)
@Service
public class SecKillQueryServiceImpl implements ISecKillQueryService {

    @Autowired
    private KillProductJpaRepo killProductJpaRepo;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<KillProduct> getKillProductList() {
        return killProductJpaRepo.findAll();
    }

    @Override
    @Cacheable(keyGenerator = "keyGenerator")
    public Optional<KillProduct> getKillProductById(String killProductId) {
        return killProductJpaRepo.findById(killProductId);
    }

    @Override
    public Exposer exportSecKillUrl(String killProductId) {
        KillProduct killProduct;
        //通过redis缓存
        Object redisResult = redisTemplate.opsForValue().get(
                RedisCustomKeyGenerator.getKey4CacheName(RedisCacheName.KILL_PRODUCT) + killProductId);
        if(redisResult != null){
            killProduct = (KillProduct)redisResult;
        }else {
            Optional<KillProduct> killProductOptional = killProductJpaRepo.findById(killProductId);
            if(!killProductOptional.isPresent()){
                return new Exposer(false,killProductId);
            }else {
                killProduct = killProductOptional.get();
            }
        }
        Date startTime = killProduct.getStartTime();
        Date endTime = killProduct.getEndTime();
        Date now = new Date();
        if(now.getTime() < startTime.getTime() || now.getTime() > endTime.getTime()){
            return new Exposer(false,killProductId,now.getTime(),startTime.getTime(),endTime.getTime());
        }
        String md5 = Md5Util.getMd5(killProductId);
        return new Exposer(true,md5,killProductId);
    }
}
