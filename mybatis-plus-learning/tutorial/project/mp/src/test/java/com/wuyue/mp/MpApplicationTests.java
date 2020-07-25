package com.wuyue.mp;

import com.wuyue.mp.entities.User;
import com.wuyue.mp.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class MpApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void select() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
}
