package com.atguigu.account.mapper;

import com.atguigu.account.bean.AccountTbl;

/**
* @author lfy
* @description 针对表【account_tbl】的数据库操作Mapper
* @createDate 2025-01-08 18:32:50
* @Entity com.atguigu.account.bean.AccountTbl
*/
public interface AccountTblMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AccountTbl record);

    int insertSelective(AccountTbl record);

    AccountTbl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountTbl record);

    int updateByPrimaryKey(AccountTbl record);

    void debit(String userId, int money);
}
