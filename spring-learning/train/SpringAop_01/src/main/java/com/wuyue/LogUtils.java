package com.wuyue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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

    @Pointcut("execution(public int com.wuyue.CalculatorImpl.*(int,int))")
    public void point() {
    }

    @Before("point()")
    public static void methodStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        logger.info(name + " 方法被执行, 参数为 " + Arrays.toString(args));
//        int i = 1/0;
    }

    @AfterReturning(value = "point()", returning = "result")
    public static void methodReturn(JoinPoint joinPoint, Object result) {
        logger.info(joinPoint.getSignature().getName() + " 方法返回, 结果为 " + result);
//        int i = 1/0;
    }

    @AfterThrowing(value = "execution(public int com.wuyue.CalculatorImpl.*(int,int))", throwing = "e")
    public static void methodException(JoinPoint joinPoint, Exception e) {
        logger.warn(joinPoint.getSignature().getName() + " 方法异常, 异常为 " + e);
//        int i = 1/0;
    }

    @After("execution(public int com.wuyue.CalculatorImpl.*(int,int))")
    public static void methodEnd(JoinPoint joinPoint) {
        logger.info(joinPoint.getSignature().getName() + " 方法结束");
//        int i = 1/0;
    }


    @Around("point()")
    public Object aroundAdvise(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object retval = null;
        try {
            System.out.println("环绕前置通知: " + proceedingJoinPoint.getSignature().getName());
            Object[] args = proceedingJoinPoint.getArgs();
            retval = proceedingJoinPoint.proceed(args);
            System.out.println("环绕返回通知: " + retval);
        } catch (Exception e) {
            System.out.println("环绕异常通知: " + e);
            throw e;
        } finally {
            System.out.println("环绕后置通知");
        }
        if (retval == null)
            retval = (Object) 0;
        return retval;
    }
}














