package com.example.seckill.dto;

import com.example.seckill.common.status.KillStatus;
import com.example.seckill.dao.entity.KillItem;
import lombok.Data;

/**
 * 秒杀执行后的结果
 * @author ibm
 * @since 0
 * @date 2018-4-6
 */
@Data
public class Execution {
    /**
     * 秒杀商品id
     */
    private String killProductId;
    /**
     * 秒杀执行结果状态
     */
    private int status;
    /**
     * 秒杀执行结果状态描述
     */
    private String statusInfo;
    /**
     * 秒杀成功对象
     */
    private KillItem successKilled;

    public Execution(String killProductId, KillStatus status, KillItem successKilled) {
        this.killProductId = killProductId;
        this.status = status.getValue();
        this.statusInfo = status.getInfo();
        this.successKilled = successKilled;
    }

    public Execution(String killProductId,KillStatus status) {
        this.killProductId = killProductId;
        this.status = status.getValue();
        this.statusInfo = status.getInfo();
    }
}
