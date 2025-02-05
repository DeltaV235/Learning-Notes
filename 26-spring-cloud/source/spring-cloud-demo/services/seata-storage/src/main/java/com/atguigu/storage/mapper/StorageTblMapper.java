package com.atguigu.storage.mapper;

import com.atguigu.storage.bean.StorageTbl;

/**
* @author lfy
* @description 针对表【storage_tbl】的数据库操作Mapper
* @createDate 2025-01-08 18:35:07
* @Entity com.atguigu.storage.bean.StorageTbl
*/
public interface StorageTblMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StorageTbl record);

    int insertSelective(StorageTbl record);

    StorageTbl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StorageTbl record);

    int updateByPrimaryKey(StorageTbl record);

    void deduct(String commodityCode, int count);
}
