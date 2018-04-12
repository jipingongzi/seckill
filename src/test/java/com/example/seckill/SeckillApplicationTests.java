package com.example.seckill;

import com.example.seckill.applicationService.ISecKillApplicationService;
import com.example.seckill.dao.entity.KillProduct;
import com.example.seckill.dto.Execution;
import com.example.seckill.dto.Exposer;
import com.example.seckill.queryService.ISecKillQueryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillApplicationTests {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ISecKillApplicationService seckillApplication;
	@Autowired
	private ISecKillQueryService secKillQueryService;

	@Test
	public void findAll(){
		List<KillProduct> list = secKillQueryService.getKillProductList();
		logger.info("list:{}",list);
	}

	@Test
	public void findOne(){
		KillProduct one = secKillQueryService.getKillProductById("test").get();
		logger.info("one:{}",one);
	}

	@Test
	public void expose(){
		Exposer exposer = secKillQueryService.exportSecKillUrl("test");
		logger.info("exposer:{}",exposer);
	}
	@Test
	public void execution() {
		Execution execution = seckillApplication.executeSecKill("test",13668105960L,
		"6528b551f25146f6f9756540d74cca5b");
		logger.info("execution:{}",execution);
	}

}
