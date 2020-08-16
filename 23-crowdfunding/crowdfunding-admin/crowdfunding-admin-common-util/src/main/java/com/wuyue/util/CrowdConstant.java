package com.wuyue.util;

/**
 * @author DeltaV235
 * @version 1.0
 * @className CrowdConstant
 * @description
 * @date 2020/5/6 20:27
 */
public enum CrowdConstant {
    EXCEPTION("exception"),
    MESSAGE_STRING_INVALIDATE("字符串不合法"),
    MESSAGE_LOGIN_FAILED("用户名或密码不正确"),
    ATTR_NAME_LOGIN_ADMIN("loginAdmin"),
    MESSAGE_DATA_ERROR_LOGACCT_NOT_UNIQUE("数据错误,管理员登录账号不唯一"),
    MESSAGE_NOT_LOGIN("请先登录"),
    ATTR_ADMIN_PAGE_INFO("pageInfo"),
    MESSAGE_LOGIN_ACCOUNT_ALREADY_IN_USE("登录账号已被使用,请重新输入"),
    ATTR_ADMIN_INFO("admin");

    private final String strConstant;

    CrowdConstant(String strConstant) {
        this.strConstant = strConstant;
    }

    public String getStrConstant() {
        return strConstant;
    }

}
