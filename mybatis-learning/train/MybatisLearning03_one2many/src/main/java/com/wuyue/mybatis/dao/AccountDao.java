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


    /**
     * @param aid
     * @return
     * @author DeltaV235
     * @date 2020/3/7 14:25
     * @description 通过account的id查询account和user信息
     */
    Account findAccountAndUserByAid(Integer aid);

    /**
     * @param id
     * @return
     * @author DeltaV235
     * @date 2020/3/7 14:43
     * @description 通过id查找账户信息
     */
    Account findById(Integer id);



}
