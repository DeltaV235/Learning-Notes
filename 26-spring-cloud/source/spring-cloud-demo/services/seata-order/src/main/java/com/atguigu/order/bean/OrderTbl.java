package com.atguigu.order.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName order_tbl
 */
@Data
public class OrderTbl implements Serializable {
    private Integer id;

    private String userId;

    private String commodityCode;

    private Integer count;

    private Integer money;

    private static final long serialVersionUID = 1L;
}