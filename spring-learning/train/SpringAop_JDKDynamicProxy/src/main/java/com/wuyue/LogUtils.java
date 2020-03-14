package com.wuyue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author DeltaV235
 * @version 1.0
 * @className LogUtils
 * @description
 * @date 2020/3/14 17:59
 */
public class LogUtils {
    private static Logger logger = LogManager.getLogger(LogUtils.class);

    public static void methodStart(Method method, Object... args) {
        logger.info(method.getName() + "方法被执行, 参数为" + Arrays.toString(args));
    }

    public static void methodEnd(Method method, Object result) {
        logger.info(method.getName() + "方法结束, 结果为" + result);
    }

    public static void methodException(Method method, Exception e) {
        logger.warn(method.getName() + "方法异常, 异常为" + e.getCause());
    }
}














