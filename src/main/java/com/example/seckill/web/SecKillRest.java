package com.example.seckill.web;

import com.example.seckill.applicationService.ISecKillApplicationService;
import com.example.seckill.dao.entity.KillProduct;
import com.example.seckill.dto.Execution;
import com.example.seckill.dto.Exposer;
import com.example.seckill.exception.SecKillException;
import com.example.seckill.queryService.ISecKillQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 秒杀相关web接口
 * @author ibm
 * @since 0
 * @date 2018/3/22
 */
@Controller
@RequestMapping("/secKill")
public class SecKillRest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ISecKillQueryService secKillQueryService;
    private final ISecKillApplicationService secKillApplicationService;
    
    @Autowired
    public SecKillRest(ISecKillQueryService secKillQueryService,ISecKillApplicationService secKillApplicationService){
        this.secKillQueryService = secKillQueryService;
        this.secKillApplicationService = secKillApplicationService;
    }

    /**
     * 秒杀列表页
     * @param model 封装返回对象使用
     * @return 列表页视图
     */
    @GetMapping("/list")
    public String getList(Model model){
        List<KillProduct> list = secKillQueryService.getKillProductList();
        model.addAttribute("list",list);
        return "/list";
    }
    /**
     * 秒杀详情页
     * @param killProductId 秒杀商品Id
     * @param model 封装返回对象使用
     * @return 详情页视图
     */
    @GetMapping("/{killProductId}/detail")
    public String getDetail(@PathVariable("killProductId")String killProductId, Model model){
        if(StringUtils.isEmpty(killProductId)){
            return "redirect:/secKill/list";
        }
        Optional<KillProduct> killProductOptional = secKillQueryService.getKillProductById(killProductId);
        if(!killProductOptional.isPresent()){
            return "forward:/secKill/list";
        }
        KillProduct killProduct = killProductOptional.get();
        model.addAttribute("killProduct",killProduct);
        return "detail";
    }
    /**
     * 查看秒杀商品是否暴露
     * @param killProductId 秒杀商品Id
     * @return 是否暴露
     */
    @PostMapping("/{killProductId}/expose")
    @ResponseBody
    public Exposer expose(@PathVariable("killProductId") String killProductId){
        return secKillQueryService.exportSecKillUrl(killProductId);
    }
    /**
     * 执行秒杀
     * @param killProductId 秒杀商品Id
     * @param md5 加密值
     * @param mobile 用户登陆手机号
     * @return 秒杀结果
     */
    @PostMapping("/{killProductId}/{md5}/execute")
    @ResponseBody
    public Execution execute(@PathVariable("killProductId") String killProductId,
                                               @PathVariable("md5")String md5,
                                               @CookieValue("killPhone") Long mobile){
        if(mobile == null){
            throw new SecKillException("用户未登录");
        }
        return secKillApplicationService.executeSecKillProcedure(killProductId,mobile,md5);
    }
    /**
     * 获取当前系统时间
     * @return
     */
    @GetMapping("/time/now")
    @ResponseBody
    public Long time(){
        return System.currentTimeMillis();
    }

    @ResponseBody
    @PostMapping("/test")
    public String test(@RequestBody List<String> data){
        data.forEach(System.out::println);
        return "";
    }

}
