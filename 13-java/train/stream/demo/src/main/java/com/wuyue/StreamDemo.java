package com.wuyue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 输出 List 中 id 为偶数、age > 24、按 userName 倒数排列的第一个元素的 userName 大写
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2020/11/21 11:28
 */
public class StreamDemo {
    public static void main(String[] args) {
        User u1 = new User(11, "a", 22);
        User u2 = new User(12, "b", 23);
        User u3 = new User(13, "c", 24);
        User u4 = new User(14, "d", 25);
        User u5 = new User(16, "e", 26);

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        List<String> collect = list.stream()
                .filter(user -> user.getId() % 2 == 0 && user.getAge() > 24)
                .map(user -> user.getUserName().toUpperCase())
                .sorted(Comparator.reverseOrder())
                .limit(1).collect(Collectors.toList());
        System.out.println("collect = " + collect);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class User {
        private Integer id;
        private String userName;
        private int age;
    }
}
