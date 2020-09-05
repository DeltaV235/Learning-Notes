package com.wuyue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

import java.util.Arrays;

/**
 * @author DeltaV235
 * @version 1.0
 * @className LogUtils
 * @description 测试通知方法的切入
 * @date 2020/3/14 17:59
 */
public class AspectTest {
    private static Logger logger = LogManager.getLogger(AspectTest.class);

    public static void methodStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("B " + name + " 方法被执行, 参数为 " + Arrays.toString(args));
    }

    public static void methodReturn(JoinPoint joinPoint, Object result) {
        System.out.println("B " + joinPoint.getSignature().getName() + " 方法返回, 结果为 " + result);
    }

    public static void methodException(JoinPoint joinPoint, Exception e) {
        System.out.println("B " + joinPoint.getSignature().getName() + " 方法异常, 异常为 " + e);
    }

    public static void methodEnd(JoinPoint joinPoint) {
        System.out.println("B " + joinPoint.getSignature().getName() + " 方法结束");
    }

    public Object aroundAdvise(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object retval = null;
        try {
            System.out.println("B 环绕前置通知: " + proceedingJoinPoint.getSignature().getName());
            Object[] args = proceedingJoinPoint.getArgs();
            retval = proceedingJoinPoint.proceed(args);
            System.out.println("B 环绕返回通知: " + retval);
        } catch (Exception e) {
            System.out.println("B 环绕异常通知: " + e);
            throw e;
        } finally {
            System.out.println("B 环绕后置通知");
        }
        if (retval == null)
            retval = (Object) 0;
        return retval;
    }
}














