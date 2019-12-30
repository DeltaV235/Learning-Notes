package com.wuyue.io.commonsIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.*;

import java.io.File;
import java.util.Collection;

public class CIOTest {
    public static void main(String[] args) {
        long len = FileUtils.sizeOf(new File("Test"));
        System.out.println(len);
        len = FileUtils.sizeOf(new File("Test/CopyTest/test02/2.flv"));
        System.out.println(len);
        Collection<File> files = FileUtils.listFiles(new File("Test"), EmptyFileFilter.NOT_EMPTY, DirectoryFileFilter.INSTANCE);
        for (File file:files) {
            System.out.println(file.getPath());
        }

        System.out.println("-------------------------------------------");
        files = FileUtils.listFiles(new File("Test"), FileFilterUtils.or(new SuffixFileFilter("test"), new SuffixFileFilter("mp4")), DirectoryFileFilter.INSTANCE);
        for (File file:files) {
            System.out.println(file.getPath());
        }
    }
}
