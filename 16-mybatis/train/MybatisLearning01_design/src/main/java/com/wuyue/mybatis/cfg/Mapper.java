package com.wuyue.mybatis.cfg;

/**
 * @author DeltaV235
 * @version 1.0
 * @className Mapper
 * @description 一个类的一个方法和执行SQL、返回数据类型的映射关系对象
 * @date 2020/2/26 23:56
 */
public class Mapper {
    private String queryString;
    private String resultType;

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
