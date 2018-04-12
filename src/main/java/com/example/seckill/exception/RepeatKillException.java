package com.example.seckill.exception;

/**
 * 重复秒杀异常(运行时异常)
 * @author ibm
 * @since 0
 * @date 2018-4-6
 */
public class RepeatKillException extends SecKillException{

    public RepeatKillException(String message) {
        super(message);
    }
}
