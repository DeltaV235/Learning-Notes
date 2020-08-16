package com.wuyue.domain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author DeltaV235
 * @version 1.0
 * @className MyBeanPostProcessor
 * @description
 * @date 2020/3/12 16:22
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Before后置处理器 beanName:" + beanName + "  bean:" + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("After beanName:" + beanName + "  bean:" + bean);
        return bean;
    }
}














