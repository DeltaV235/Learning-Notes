package com.wuyue.mp.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author DeltaV235
 * @version 1.0
 * @className User
 * @description mp.User表的实体类
 * @date 2020/7/21 1:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("mp_user")
public class User {
    /**
     * 主键
     */
    @TableId
    private Long userId;

    /**
     * 姓名
     */
    @TableField("name")
    private String realName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 直属上级
     */
    private Long managerId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 备注
     */
    @TableField(exist = false)
    private String remark;
}
