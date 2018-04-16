package com.example.seckill.applicationService;

import com.example.seckill.dto.Execution;
import com.example.seckill.exception.KillClosedException;
import com.example.seckill.exception.RepeatKillException;
import com.example.seckill.exception.SecKillException;

/**
 * 秒杀业务逻辑
 * @author ibm
 * @since 0
 * @date 2018/3/22
 */
public interface ISecKillApplicationService {

    /**
     * 执行秒杀
     * @param killProductId 秒杀商品id
     * @param mobile 用户手机号码
     * @param md5 秒杀活动md5
     */
    Execution executeSecKill(String killProductId, long mobile, String md5)
        throws SecKillException,RepeatKillException,KillClosedException;

    /**
     * 在高并发的情况下，使用存储过程减少行级锁持有的时间
     * @param killProductId 秒杀商品id
     * @param mobile 用户手机号码
     * @param md5 秒杀活动md5
     */
    Execution executeSecKillProcedure(String killProductId, long mobile, String md5);

}
