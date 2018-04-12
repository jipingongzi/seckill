package com.example.seckill.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * web访问切面，统计request耗时长度
 * @author ibm
 * @since 0
 * @date 2018/3/25
 */
@Component
@Aspect
public class RequestAspect {

    private static final Logger logger = LoggerFactory.getLogger(RequestAspect.class);

    @Pointcut("execution(public * com.example.seckill.web..*(..)) ")
    public void restPointCut() {

    }

    @Around(value = "restPointCut()")
    public Object doAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        Object[] targetArgs = joinPoint.getArgs();
        Object restResult = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        StringBuilder restInfo = new StringBuilder(targetMethod.getDeclaringClass().getName() + ".");
        restInfo.append(targetMethod.getName());
        restInfo.append("(" + Arrays.toString(targetArgs) + ")");
        restInfo.append(" 耗时 ：");
        restInfo.append(duration);
        restInfo.append("毫秒");
        logger.info(restInfo.toString());
        return restResult;
    }
}
