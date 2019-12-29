package com.wuyue.io.ioStream;

import java.io.*;

public class CopyDir {

    private static int copyDir(String src, String dest) {
        File srcFile = new File(src);
        File destFile = new File(dest);
        InputStream is = null;
        OutputStream os = null;
        int size = 0;
        if (!srcFile.exists())
            return 0;
        if (srcFile.isFile()) {
            try {
                is = new FileInputStream(srcFile);
                os = new FileOutputStream(destFile);
                byte[] flush = new byte[1024 * 4];
                int length = -1;
                while ((length = is.read(flush)) != -1) {
                    size += length;
                    os.write(flush, 0, length);
                }
                os.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (os != null)
                        os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    if (is != null)
                        is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (!destFile.mkdir()) {
                System.out.println("目标目录已存在，将覆盖其中的文件");
            }
            File[] nextFiles = srcFile.listFiles();
            for (File file : nextFiles) {
                size += copyDir(file.getPath(), destFile.getPath() + "/" + file.getName());
            }
        }
        return size;
    }

    private static int moveDir(String src, String dest) {
        File srcFile = new File(src);
        File destFile = new File(dest);
        InputStream is = null;
        OutputStream os = null;
        boolean isSuccess = false;
        int size = 0;
        if (!srcFile.exists())
            return 0;
        if (srcFile.isFile()) {
            try {
                is = new FileInputStream(srcFile);
                os = new FileOutputStream(destFile);
                byte[] flush = new byte[1024 * 4];
                int length = -1;
                while ((length = is.read(flush)) != -1) {
                    size += length;
                    os.write(flush, 0, length);
                }
                os.flush();
                isSuccess = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (os != null)
                        os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    if (is != null)
                        is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (isSuccess)
                srcFile.delete();
        } else {
            if (!destFile.mkdir()) {
                System.out.println("目标目录已存在，将覆盖其中的文件");
            }
            File[] nextFiles = srcFile.listFiles();
            for (File file : nextFiles) {
                size += moveDir(file.getPath(), destFile.getPath() + "/" + file.getName());
            }
            srcFile.delete();
        }
        return size;
    }

    public static void main(String[] args) {
//        System.out.println("Total Size :" + copyDir("testDir", "CopyTest"));
        System.out.println("Total Size :" + moveDir("CopyTest", "Test/CopyTest"));
    }
}
