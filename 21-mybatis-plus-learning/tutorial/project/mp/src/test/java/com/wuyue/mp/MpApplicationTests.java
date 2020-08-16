package com.wuyue.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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

    /**
     * DEBUG==>  Preparing: SELECT user_id,name AS realName,age,email,manager_id,create_time FROM mp_user
     * WHERE (age IN (?,?,?,?)) limit 1
     * DEBUG==> Parameters: 24(Integer), 31(Integer), 34(Integer), 35(Integer)
     */
    @Test
    public void testSelectByWrapperLimit() {
        QueryWrapper<User> query = Wrappers.query();
        query.in("age", Arrays.asList(24, 31, 34, 35)).last("limit 1");
        List<User> userList = userMapper.selectList(query);
        userList.forEach(System.out::println);
    }

    /**
     * DEBUG==>  Preparing: SELECT user_id,name AS realName,age,email,manager_id,create_time FROM mp_user
     * WHERE (age IN (?,?,?))
     * DEBUG==> Parameters: 31(Integer), 34(Integer), 35(Integer)
     */
    @Test
    public void testSelectByWrapperIn() {
        QueryWrapper<User> query = Wrappers.query();
        query.in("age", Arrays.asList(31, 34, 35));
        List<User> userList = userMapper.selectList(query);
        userList.forEach(System.out::println);
    }

    /**
     * DEBUG==>  Preparing: SELECT user_id,name AS realName,age,email,manager_id,create_time FROM mp_user
     * WHERE (( (age < ? OR email IS NOT NULL) ) AND name LIKE ?)
     * DEBUG==> Parameters: 40(Integer), 王%(String)
     */
    @Test
    public void testSelectByWrapperWithNested() {
        QueryWrapper<User> query = Wrappers.query();
        query.nested(wq -> wq.lt("age", 40).or().isNotNull("email"))
                .likeRight("name", "王");
        // query.lt("age", 40).or().isNotNull("email").likeRight("name", "王");
        List<User> userList = userMapper.selectList(query);
        userList.forEach(System.out::println);
    }

    /**
     * 嵌套查询
     * DEBUG==>  Preparing: SELECT user_id,name AS realName,age,email,manager_id,create_time FROM mp_user
     * WHERE (name LIKE ? OR ( (age > ? AND age < ? AND email IS NOT NULL) ))
     * DEBUG==> Parameters: 王%(String), 20(Integer), 40(Integer)
     */
    @Test
    public void testSelectByWrapperWithOr() {
        QueryWrapper<User> query = Wrappers.query();
        query.likeRight("name", "王")
                .or(qw -> qw.gt("age", 20).lt("age", 40).isNotNull("email"));
        List<User> userList = userMapper.selectList(query);
        userList.forEach(System.out::println);
    }

    /**
     * 嵌套查询
     * DEBUG==>  Preparing: SELECT user_id,name AS realName,age,email,manager_id,create_time FROM mp_user
     * WHERE (name LIKE ? AND ( (age < ? OR email IS NOT NULL) ))
     * DEBUG==> Parameters: %王%(String), 40(Integer)
     */
    @Test
    public void testSelectByWrapperWithAnd() {
        QueryWrapper<User> query = Wrappers.query();
        query.like("name", "王")
                .and(queryWrapper -> queryWrapper.lt("age", 40).or().isNotNull("email"));
        List<User> userList = userMapper.selectList(query);
        userList.forEach(System.out::println);
    }

    /**
     * MySQL函数调用、子查询
     * DEBUG==>  Preparing: SELECT user_id,name AS realName,age,email,manager_id,create_time FROM mp_user
     * WHERE (date_format(create_time, '%Y-%m-%d') = ? AND manager_id IN (select user_id from mp_user where name like '王%'))
     * DEBUG==> Parameters: 2019-02-14(String)
     */
    @Test
    public void testSelectByWrapperWithSubSQL() {
        QueryWrapper<User> query = Wrappers.query();
        query.apply("date_format(create_time, '%Y-%m-%d') = {0}", "2019-02-14")
                .inSql("manager_id", "select user_id from mp_user where name like '王%'");
        List<User> userList = userMapper.selectList(query);
        userList.forEach(System.out::println);
    }

    /**
     * DEBUG==>  Preparing: SELECT user_id,name AS realName,age,email,manager_id,create_time FROM mp_user
     * WHERE (name LIKE ? OR age >= ?) ORDER BY age DESC , user_id ASC
     * DEBUG==> Parameters: 王%(String), 40(Integer)
     */
    @Test
    public void testSelectByWrapper3() {
        QueryWrapper<User> query = Wrappers.query();
        query.likeRight("name", "王").or().ge("age", 40).like("email", "com")
                .orderByDesc("age").orderByAsc("user_id");
        List<User> userList = userMapper.selectList(query);
        userList.forEach(System.out::println);
    }

    /**
     * DEBUG==>  Preparing: SELECT user_id,name AS realName,age,email,manager_id,create_time FROM mp_user
     * WHERE (name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
     * DEBUG==> Parameters: %雨%(String), 20(Integer), 40(Integer)
     */
    @Test
    public void testSelectByWrapper2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "雨").between("age", 20, 40).isNotNull("email");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * DEBUG==>  Preparing: SELECT user_id,name AS realName,age,email,manager_id,create_time FROM mp_user
     * WHERE (name LIKE ? AND age < ?)
     * DEBUG==> Parameters: %雨%(String), 40(Integer)
     */
    @Test
    public void testSelectByWrapper() {
//        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.like("name", "雨").lt("age", 40);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

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
