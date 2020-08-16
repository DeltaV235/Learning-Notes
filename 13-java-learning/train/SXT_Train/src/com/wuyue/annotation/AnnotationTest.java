package com.wuyue.annotation;

@MyAnnotation("haha")
public class AnnotationTest {

    public static void main(String[] args) {
        test001();
    }

    @Override
    @MyAnnotation("haha")
    public String toString() {
        return "";
    }

    @Deprecated
    public static void test001() {
    }
}
