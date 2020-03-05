package com.wuyue.mybatis.dao;

import com.wuyue.mybatis.domain.Account;
import com.wuyue.mybatis.domain.AccountUser;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className AccountDao
 * @description
 * @date 2020/3/1 18:45
 */
public interface AccountDao {
    /**
     * @return
     * @author DeltaV235
     * @date 2020/3/1 20:38
     * @description 查询所有记录
     */
    List<Account> findAll();

    /**
     * @return
     * @author DeltaV235
     * @date 2020/3/1 20:57
     * @description 查找所有account记录和对应的user记录中的username和address
     */
    List<AccountUser> findAllAccountUser();
}
