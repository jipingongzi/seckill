package com.example.seckill.common.status;

/**
 * 秒杀状态的常量
 * @author ibm
 * @since 0
 * @date 2018-4-6
 */
public enum KillStatus {

    END(0,"秒杀结束"),
    SUCCESS(1,"秒杀成功"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"服务器内部异常"),
    REWRITE(-3,"数据篡改");

    private int value;
    private String info;

    public static KillStatus statusOf(int index){
        for (KillStatus status : values()){
            if(status.getValue() == index){
                return status;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    KillStatus(int value, String info){
        this.value = value;
        this.info = info;
    }
}
