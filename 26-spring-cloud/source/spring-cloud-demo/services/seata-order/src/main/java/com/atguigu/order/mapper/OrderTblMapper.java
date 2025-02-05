package com.atguigu.order.mapper;

import com.atguigu.order.bean.OrderTbl;

/**
* @author lfy
* @description 针对表【order_tbl】的数据库操作Mapper
* @createDate 2025-01-08 18:34:18
* @Entity com.atguigu.order.bean.OrderTbl
*/
public interface OrderTblMapper {

    int deleteByPrimaryKey(Long id);

    int insert(OrderTbl record);

    int insertSelective(OrderTbl record);

    OrderTbl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderTbl record);

    int updateByPrimaryKey(OrderTbl record);

}
