package com.wuyue.mp.entities;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author DeltaV235
 * @version 1.0
 * @className User
 * @description mp.User表的实体类
 * @date 2020/7/21 1:02
 */
@Data
public class User {
    // 主键
    private Long id;

    // 姓名
    private String name;

    // 年龄
    private Integer age;

    // 邮箱
    private String email;

    // 直属上级
    private Long managerId;

    // 创建时间
    private LocalDateTime createTime;
}
