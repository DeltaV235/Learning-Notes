package com.wuyue.io;

import java.io.File;

public class FileDemo01 {
    public static void main(String[] args) {
        String path = "D:\\OneDrive - st.sdju.edu.cn\\Learning\\java-learning\\train\\SXT_Train\\LICENSE.test";
        File src = new File(path);
        System.out.println(src.length());
        src = new File("D:/OneDrive - st.sdju.edu.cn/Learning/java-learning/train/SXT_Train", "LICENSE.test");
        System.out.println(src.length());
        src = new File(new File("D:/OneDrive - st.sdju.edu.cn/Learning/java-learning/train/SXT_Train"), "LICENSE.test");
        System.out.println(src.length());
        System.out.println(System.getProperty("user.dir"));     //项目路径，相对位置
        src = new File("LICENSE.test");
        System.out.println(src.getAbsolutePath());
    }
}
