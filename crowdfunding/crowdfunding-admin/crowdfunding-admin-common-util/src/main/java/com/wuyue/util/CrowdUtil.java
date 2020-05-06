package com.wuyue.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DeltaV235
 * @version 1.0
 * @className CrowdUtil
 * @description 工具类
 * @date 2020/5/6 16:32
 */
public class CrowdUtil {

    /**
     * 判断当前请求是否是Ajax请求
     * @param request 请求对象
     * @return 若是ajax请求则返回true,反之false
     */
    public static boolean judgeRequestType(HttpServletRequest request) {
        String xRequestHeader = request.getHeader("X-Requested-With");
        return null != xRequestHeader && xRequestHeader.equals("XMLHttpRequest");
    }
}
