package com.wuyue.mybatis.io;

import java.io.InputStream;

/**
 * @author DeltaV235
 * @version 1.0
 * @className Resources
 * @description 返回自定义mybatis主配置文件的字节输入流
 * @date 2020/2/26 20:53
 */
public class Resources {
    public static InputStream getResourceAsStream(String configPath) {
        return Resources.class.getClassLoader().getResourceAsStream(configPath);
    }
}
