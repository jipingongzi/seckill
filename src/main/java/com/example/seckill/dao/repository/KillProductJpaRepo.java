package com.example.seckill.dao.repository;

import com.example.seckill.dao.entity.KillProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @author ibm
 * @since 0
 * @date 2018/3/22
 */
public interface KillProductJpaRepo extends JpaRepository<KillProduct,String>{

    /**
     * 查看可以开始秒杀商品
     * @param now 开始时间点
     * @return 秒杀商品明细
     */
    List<KillProduct> findAllByStartTimeAfter(Date now);

    /**
     * 减少库存,库存等于0就不再减少
     * @param id 秒杀商品id
     * @param time 执行秒杀的时间
     * @return 执行的行数
     */
    @Modifying
    @Query(value = "UPDATE kill_product SET number = number - 1 WHERE id = ?1 AND number >= 1 AND end_time > ?2",
    nativeQuery = true)
    int reduceNumber(String id,Date time);
}
