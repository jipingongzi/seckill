package com.example.seckill.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 秒杀产品实体类
 * @author ibm
 * @since 0
 * @date 2018/3/22
 */
@Entity
@Table(name = "kill_product")
@Data
public class KillProduct {

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    private String id;
    /**
     * 产品ID
     */
    @Column(name = "product_id")
    private String productId;
    /**
     * 秒杀描述信息
     */
    @Column(name = "kill_description")
    private String killDescription;
    /**
     * 库存数量
     */
    @Column(name = "number")
    private String number;
    /**
     * 秒杀开始时间
     */
    @Column(name = "start_time")
    private Date startTime;
    /**
     * 秒杀结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

}
