package com.example.seckill.exception;

/**
 * 秒杀关闭异常
 * @author ibm
 * @since 0
 * @date 2018-4-6
 */
public class KillClosedException extends SecKillException{

    public KillClosedException(String message) {
        super(message);
    }
}
