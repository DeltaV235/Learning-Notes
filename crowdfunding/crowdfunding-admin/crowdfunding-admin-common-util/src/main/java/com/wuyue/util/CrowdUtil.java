package com.wuyue.util;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className CrowdUtil
 * @description 工具类
 * @date 2020/5/6 16:32
 */
public class CrowdUtil {

    /**
     * 对传入的明文字符串进行加密
     *
     * @param source 明文字符串
     * @return 加密后的结果, 用16进制字符串表示
     */
    public static String md5(String source) {
        if (null == source || source.equals(""))
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE.getStrConstant());
        try {
            String algorithm = "md5";
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            byte[] input = source.getBytes();
            byte[] output = messageDigest.digest(input);
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);
            int radix = 16;
            return bigInteger.toString(radix).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断当前请求是否是Ajax请求
     *
     * @param request 请求对象
     * @return 若是ajax请求则返回true, 反之false
     */
    public static boolean judgeRequestType(HttpServletRequest request) {
        String xRequestHeader = request.getHeader("X-Requested-With");
        return null != xRequestHeader && xRequestHeader.equals("XMLHttpRequest");
    }
}
