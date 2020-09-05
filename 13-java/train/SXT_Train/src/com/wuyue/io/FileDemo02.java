package com.wuyue.io;

import java.io.File;

public class FileDemo02 {
    public static void main(String[] args) {
        File src = new File("LICENSE.test");
        System.out.println("文件名 " + src.getName());
        System.out.println("路径名(即File()的形参) " + src.getPath());
        System.out.println("绝对路径 " + src.getAbsolutePath());
        src = new File("D:\\OneDrive - st.sdju.edu.cn\\Learning\\java-learning\\train\\SXT_Train\\LICENSE.test");
        System.out.println("父路径 " + src.getParent());
        System.out.println("父对象文件名 " + src.getParentFile().getName());
        System.out.println("路径名(即File()的形参) " + src.getPath());
        File parent = src.getParentFile();
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("是否存在 " + parent.exists());
        System.out.println("是否是目录 " + parent.isDirectory());
        System.out.println("目录名 " + parent.getPath());
        System.out.println("目录大小 " + parent.length());
    }
}
