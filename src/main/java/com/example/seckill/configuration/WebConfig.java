package com.example.seckill.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * web配置类
 * @author ibm
 * @since 0
 * @date 2018-4-11
 */
@Configuration
public class WebConfig {

    /**
     * 重写json视图，自定义输出结构
     * @return 自定义的json视图转化器
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
        return new ResponseConverter();
    }
}
