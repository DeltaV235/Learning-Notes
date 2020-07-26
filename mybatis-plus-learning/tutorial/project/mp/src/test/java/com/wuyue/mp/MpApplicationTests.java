package com.wuyue.mp;

import com.wuyue.mp.entities.User;
import com.wuyue.mp.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
class MpApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "test001");
        columnMap.put("age", 30);
        List<User> users = userMapper.selectByMap(columnMap);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectBatchIds() {
        Collection<? extends Serializable> idList = Arrays.asList(1087982257332887553L,
                1094590409767661570L,
                1094592041087729666L);
        List<User> users = userMapper.selectBatchIds(idList);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1087982257332887553L);
        System.out.println(user);
    }

    @Test
    public void select() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
        System.out.println("----------------------------------------------");
        Stream<User> sorted = users.stream().sorted((item, next) -> item.getAge() > next.getAge() ? 1 : -1);
        sorted.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setRealName("test030");
        user.setAge(20);
        user.setManagerId(1087982257332887553L);
        user.setCreateTime(LocalDateTime.now());
        user.setRemark("Remark Test Content");
        int rows = userMapper.insert(user);
        System.out.println("affect rows: " + rows);
    }
}
