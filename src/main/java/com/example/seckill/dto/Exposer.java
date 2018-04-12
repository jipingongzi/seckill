package com.example.seckill.dto;

import lombok.Data;

/**
 * 暴露秒杀地址dto
 * @author ibm
 * @since 0
 * @date 2018-4-6
 */
@Data
public class Exposer {
    /**
     * 是否开启秒杀
     */
    private boolean exposed;
    /**
     * 加密措施
     */
    private String md5;
    /**
     * 秒杀商品id
     */
    private String killProductId;
    /**
     * 系统当前时间戳
     */
    private long now;
    /**
     * 秒杀开启时间
     */
    private long start;
    /**
     * 秒杀结束时间
     */
    private long end;

    public Exposer(boolean exposed, String md5, String killProductId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.killProductId = killProductId;
    }

    public Exposer(boolean exposed,String killProductId ,long now, long start, long end) {
        this.exposed = exposed;
        this.killProductId = killProductId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposed, String killProductId) {
        this.exposed = exposed;
        this.killProductId = killProductId;
    }
}
