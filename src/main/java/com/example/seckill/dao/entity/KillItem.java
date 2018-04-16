package com.example.seckill.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 秒杀明细实体类
 * @author ibm
 * @since 0
 * @date 2018/3/22
 */
@Entity
@Table(name = "kill_item")
@NamedStoredProcedureQuery(name = "executeSeckill", procedureName = "execute_seckill", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_id", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_kill_product_id", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_mobile", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_kill_time", type = Date.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "r_result", type = Integer.class) })
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
