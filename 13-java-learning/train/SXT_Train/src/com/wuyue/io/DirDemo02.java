package com.wuyue.io;

import java.io.File;

/**
 * 遍历文件树，并输出一共有多少文件和目录文件，以及目录总大小
 *
 * @author DeltaV235
 */
public class DirDemo02 {
    private File src;
    private int fileSize;
    private int dirSize;
    private long usage;

    public DirDemo02(String path) {
        src = new File(path);
        fileSize = 0;
        dirSize = 0;
        usage = 0;
        count(src);
    }

    private void count(File src) {
        if (!src.exists() || src == null)
            return;
        else {
            if (src.isFile()) {
                fileSize++;
                usage += src.length();
            } else {
                dirSize++;
                File[] sonFiles = src.listFiles();
                for (File file : sonFiles) {
                    count(file);
                }
            }
        }
    }

    public String printCount() {
        StringBuilder sb = new StringBuilder();
        sb.append("FileSize is ").append(fileSize).append("\tDirSize is ").append(dirSize).append("\tTotal Usage is ").append(usage);
        return sb.toString();
    }

    public static void main(String[] args) {
        DirDemo02 demo = new DirDemo02("./testDir");
        System.out.println(demo.printCount());
    }
}
