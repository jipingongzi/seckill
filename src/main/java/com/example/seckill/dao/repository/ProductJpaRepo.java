package com.example.seckill.dao.repository;

import com.example.seckill.dao.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author ibm
 * @since 0
 * @date 2018/3/22
 */
public interface ProductJpaRepo extends JpaRepository<Product,String>{
}
