package com.example.seckill.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 产品实体类
 * @author ibm
 * @since 0
 * @date 2018/3/22
 */
@Entity
@Table(name = "product")
@Data
public class Product {

    /**
     * 产品ID
     */
    @Id
    @Column(name = "id")
    private String id;
    /**
     * 产品名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 产品描述
     */
    @Column(name = "description")
    private String description;
    /**
     * 产品价格
     */
    @Column(name = "price")
    private int price;
}
