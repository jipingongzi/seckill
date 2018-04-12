package com.example.seckill.dao.repository;

import com.example.seckill.dao.entity.KillItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author ibm
 * @since 0
 * @date 2018/3/22
 */
public interface KillItemJpaRepo extends JpaRepository<KillItem,String> {

    /**
     * 查看秒杀商品的秒杀记录
     * @param killProductId 秒杀商品Id
     * @return 秒杀记录详情
     */
    List<KillItem> findAllByKillProductIdOrderByKillTimeDesc(String killProductId);

    /**
     * 保存秒杀记录
     * @param id 预生成的主键
     * @param killProductId 秒杀商品id
     * @param mobile 执行秒杀用户手机号
     * @return 执行的行数
     */
    @Modifying
    @Query(value = "INSERT IGNORE INTO kill_item(id,kill_product_id,mobile) values(?1,?2,?3)",
            nativeQuery = true)
    int insertKillItem(String id,String killProductId,long mobile);
}
