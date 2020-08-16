package com.wuyue.web.request.login;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className BeanUtilTest
 * @description BeanUtil中的方法
 * @date 2020/2/15 18:10
 */
public class BeanUtilTest {
    User user = new User();

    @Test
    public void test1() {
        try {
            BeanUtils.setProperty(user, "username", 101);
            System.out.println("user.getUsername() = " + user.getUsername());
            System.out.println("getProperty() = " + BeanUtils.getProperty(user, "username"));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
