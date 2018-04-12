package com.example.seckill.configuration;

import java.io.IOException;
import java.lang.reflect.Type;

import com.example.seckill.web.vo.SecKillResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
/**
 * 自定义的json视图转化器：统一返回json格式
 * @author ibm
 * @since 0
 * @date 2018-4-11
 */
public class ResponseConverter extends MappingJackson2HttpMessageConverter {

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        if(object instanceof SecKillResultVo){
            super.writeInternal(object, type, outputMessage);
            return;
        }
        SecKillResultVo restResponse = new SecKillResultVo(true,object);
        super.writeInternal(restResponse, type, outputMessage);
    }
}
