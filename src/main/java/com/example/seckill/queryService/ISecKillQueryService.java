package com.example.seckill.queryService;

import com.example.seckill.dao.entity.KillProduct;
import com.example.seckill.dto.Exposer;

import java.util.List;
import java.util.Optional;

/**
 * 秒杀相关查询服务
 * @author ibm
 * @since 0
 * @date 2018-4-6
 */
public interface ISecKillQueryService {

    /**
     * 查询所有的秒杀
     * @return
     */
    List<KillProduct> getKillProductList();

    /**
     * 根据id查询单个秒杀
     * @param killProductId 秒杀商品id
     * @return
     */
    Optional<KillProduct> getKillProductById(String killProductId);

    /**
     * 暴露秒杀活动url
     * 秒杀开启时输出秒杀活动url
     * 否则输出系统时间和秒杀时间
     * @param killProductId 秒杀商品id
     * @return
     */
    Exposer exportSecKillUrl(String killProductId);
}
