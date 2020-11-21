package com.wuyue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * 输出 List 中 id 为偶数、age > 24、按 userName 倒数排列的第一个元素的 userName 大写
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2020/11/21 11:28
 */
public class FilterDemo {
    public static void main(String[] args) {
        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 23);
        User u3 = new User(13, "c", 23);
        User u4 = new User(14, "d", 23);
        User u5 = new User(16, "e", 26);

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        TreeSet<User> map = new TreeSet<>();
        for (User user : list) {
            if (user.getId() % 2 == 0 && user.age > 24) {
                user.setUserName(user.getUserName().toUpperCase());
                map.add(user);
            }
        }
        User first = map.first();
        System.out.println(first.getUserName());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class User implements Comparable<User> {
        private Integer id;
        private String userName;
        private int age;

        @Override
        public int compareTo(User user) {
            return this.userName.charAt(0) < user.getUserName().charAt(0) ? 1 : -1;
        }
    }
}
