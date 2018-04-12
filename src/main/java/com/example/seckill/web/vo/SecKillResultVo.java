package com.example.seckill.web.vo;

import lombok.Data;

/**
 * 统一返回给前端的数据格式
 * @author ibm
 * @since 0
 * @date 2018-4-8
 * @param <T> 真正的返回数据
 */
@Data
public class SecKillResultVo<T> {
    /**
     * 请求是否成功
     */
    private boolean success;
    /**
     * 返回的业务对象
     */
    private T data;
    /**
     * 当success等于false的时候error用于描述错误信息
     */
    private String error;

    public SecKillResultVo(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SecKillResultVo(boolean success, String error) {
        this.success = success;
        this.error = error;
    }
}
