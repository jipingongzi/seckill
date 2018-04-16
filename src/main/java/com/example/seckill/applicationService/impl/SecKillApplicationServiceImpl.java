package com.example.seckill.applicationService.impl;

import com.example.seckill.applicationService.ISecKillApplicationService;
import com.example.seckill.common.status.KillStatus;
import com.example.seckill.common.utils.IdUtil;
import com.example.seckill.common.utils.Md5Util;
import com.example.seckill.configuration.cache.RedisCacheName;
import com.example.seckill.dao.entity.KillItem;
import com.example.seckill.dao.repository.KillItemJpaRepo;
import com.example.seckill.dao.repository.KillProductJpaRepo;
import com.example.seckill.dto.Execution;
import com.example.seckill.exception.KillClosedException;
import com.example.seckill.exception.RepeatKillException;
import com.example.seckill.exception.SecKillException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;


/**
 * @author ibm
 */
@CacheConfig(cacheNames = RedisCacheName.KILL_PRODUCT)
@Service
public class SecKillApplicationServiceImpl implements ISecKillApplicationService{

    @Autowired
    private KillProductJpaRepo killProductJpaRepo;

    @Autowired
    private KillItemJpaRepo killItemJpaRepo;

    @Override
    @CacheEvict(keyGenerator = "keyGenerator")
    @Transactional(rollbackFor = RuntimeException.class)
    public Execution executeSecKill(String killProductId, long mobile, String md5) throws SecKillException, RepeatKillException, KillClosedException {
        if(StringUtils.isEmpty(md5) || !md5.equals(Md5Util.getMd5(killProductId))){
            throw new SecKillException(KillStatus.REWRITE.getInfo());
        }
        //执行秒杀逻辑：减库存 + 插入秒杀明细
        try{
            Date now = new Date();
            int updateCount = killProductJpaRepo.reduceNumber(killProductId,now);
            if(updateCount <= 0){
                throw new KillClosedException(KillStatus.END.getInfo());
            }else {
                //记录秒杀明细
                String itemId = IdUtil.getObjectId();
                int insertCount = killItemJpaRepo.insertKillItem(itemId,killProductId,mobile);
                if(insertCount <= 0){
                    throw new RepeatKillException(KillStatus.REPEAT_KILL.getInfo());
                }else {
                    KillItem killItem = killItemJpaRepo.findById(itemId).get();
                    return new Execution(killProductId, KillStatus.SUCCESS,killItem);
                }
            }
        }catch (RepeatKillException e1){
            throw e1;
        }catch (KillClosedException e2){
            throw e2;
        }catch (Exception e){
            throw new SecKillException(KillStatus.INNER_ERROR.getInfo());
        }
    }

    @Override
    public Execution executeSecKillProcedure(String killProductId, long mobile, String md5){
        if(StringUtils.isEmpty(md5) || !md5.equals(Md5Util.getMd5(killProductId))){
            throw new SecKillException(KillStatus.REWRITE.getInfo());
        }
        String itemId = IdUtil.getObjectId();
        int reuslt = killItemJpaRepo.executeProcedure(itemId,killProductId,mobile,new Date());
        if(KillStatus.SUCCESS.getValue() == reuslt){
            KillItem killItem = killItemJpaRepo.findById(itemId).get();
            return new Execution(killProductId, KillStatus.SUCCESS,killItem);
        }else if(KillStatus.REPEAT_KILL.getValue() == reuslt){
            throw new RepeatKillException(KillStatus.REPEAT_KILL.getInfo());
        }else if(KillStatus.END.getValue() == reuslt){
            throw new KillClosedException(KillStatus.END.getInfo());
        }else {
            throw new SecKillException(KillStatus.INNER_ERROR.getInfo());
        }
    }
}
