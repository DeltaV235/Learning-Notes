package com.wuyue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author DeltaV235
 * @version 1.0
 * @className LogUtils
 * @description 测试通知方法的切入
 * @date 2020/3/14 17:59
 */
@Aspect
@Component
public class LogUtils {
    private static Logger logger = LogManager.getLogger(LogUtils.class);

    @Before("execution(public int com.wuyue.CalculatorImpl.*(int,int))")
    public static void methodStart() {
        logger.info("方法被执行, 参数为");
    }

    @AfterReturning("execution(public int com.wuyue.CalculatorImpl.*(int,int))")
    public static void methodReturn() {
        logger.info("方法返回, 结果为");
    }

    @AfterThrowing("execution(public int com.wuyue.CalculatorImpl.*(int,int))")
    public static void methodException() {
        logger.warn("方法异常, 异常为");
    }

    @After("execution(public int com.wuyue.CalculatorImpl.*(int,int))")
    public static void methodEnd() {
        logger.info("方法结束");
    }
}














