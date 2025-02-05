package com.atguigu.account.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName account_tbl
 */
@Data
public class AccountTbl implements Serializable {

    private Integer id;

    private String userId;

    private Integer money;

}