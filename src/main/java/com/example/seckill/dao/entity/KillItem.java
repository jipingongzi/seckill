package com.example.seckill.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 秒杀明细实体类
 * @author ibm
 * @since 0
 * @date 2018/3/22
 */
@Entity
@Table(name = "kill_item")
@Data
public class KillItem {

    /**
     * 记录ID
     */
    @Id
    @Column(name = "id")
    private String id;
    /**
     * 秒杀产品id
     */
    @Column(name = "kill_product_id")
    private String killProductId;
    /**
     * 用户手机号码
     */
    @Column(name = "mobile")
    private String mobile;
    /**
     * 秒杀成功时间
     */
    @Column(name = "kill_time")
    private Date killTime;
}
