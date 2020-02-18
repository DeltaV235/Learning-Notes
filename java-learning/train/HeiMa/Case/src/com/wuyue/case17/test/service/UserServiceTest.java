package com.wuyue.case17.test.service;

import com.wuyue.case17.entities.User;
import com.wuyue.case17.service.UserService;
import com.wuyue.case17.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UserServiceTest
 * @description 测试UserService
 * @date 2020/2/18 18:20
 */
public class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void testFindAll() {
        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
