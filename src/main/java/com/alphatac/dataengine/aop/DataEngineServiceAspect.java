package com.alphatac.dataengine.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class DataEngineServiceAspect {
    private Logger logger = LoggerFactory.getLogger(DataEngineServiceAspect.class);

    @Before(value = "execution(* com.alphatac.dataengine.service.DataEngineService.*(..))")
    private void beforeLogger(JoinPoint joinPoint){
        logger.info("Begin to execute "+joinPoint.getSignature().getName());
    }

    @After(value = "execution(* com.alphatac.dataengine.service.DataEngineService.*(..))")
    private void afterLogger(JoinPoint joinPoint){
        logger.info("After executing "+joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "execution(* com.alphatac.dataengine.service.DataEngineService.*(..))",
        throwing = "e")
    private void afterThrowingLogger(JoinPoint joinPoint,Throwable e){
        String methodName = joinPoint.getSignature().getName();
        logger.error(methodName+"execute failed",e);
    }
}
