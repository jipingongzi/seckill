package com.example.seckill.exception;

/**
 * 所有的秒杀异常父类
 * @author ibm
 * @since 0
 * @date 2018-4-6
 */
public class SecKillException extends RuntimeException {

    public SecKillException(String message) {
        super(message);
    }
}
