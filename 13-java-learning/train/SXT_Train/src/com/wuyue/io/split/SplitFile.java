package com.wuyue.io.split;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 将文件裂开
 *
 * @author DeltaV235
 */
public class SplitFile {
    private File src;
    private long blockSize;
    private int number;
    private List<File> destPaths;
    private File dest;
    private BufferedOutputStream bufferedOutputStream = null;

    public SplitFile(String srcPath, String destPath, long blockSize) {
        this.src = new File(srcPath);
        this.dest = new File(destPath);
        this.blockSize = blockSize;
        init();
    }

    private void init() {
        this.number = (int) Math.ceil(this.src.length() * 1.0 / this.blockSize);
        this.destPaths = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            this.destPaths.add(new File(dest + "/" + i + "-" + this.src.getName()));
        }
    }

    public void split() {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(src, "r");
        ) {
            int beginPos = 0;
            int length = 0;
            byte[] temp = new byte[(int) blockSize];
            for (int i = 0; i < number; i++) {
                if (i != (number - 1)) {
                    randomAccessFile.seek(beginPos);
                    randomAccessFile.read(temp);
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(destPaths.get(i)));
                    bufferedOutputStream.write(temp);
                    beginPos += blockSize;
                } else {
                    randomAccessFile.seek(beginPos);
                    length = randomAccessFile.read(temp);
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(destPaths.get(i)));
                    bufferedOutputStream.write(temp, 0, length);
                }
                bufferedOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedOutputStream != null)
                    bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void merge(String mergePath) {
        BufferedInputStream bufferedInputStream = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(mergePath, true));
            byte[] temp = new byte[(int) blockSize];
            int length;
            for (int i = 0; i < number; i++) {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(destPaths.get(i)));
                length = bufferedInputStream.read(temp);
                bufferedOutputStream.write(temp, 0, length);
                bufferedInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (bufferedInputStream != null)
                    bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        SplitFile sp = new SplitFile("Test/CopyTest/copy-copy.test", "Test/CopyTest/splitTest", 512);
        sp.split();
        sp.merge("Test/CopyTest/splitTest/merge.test");
    }
}
