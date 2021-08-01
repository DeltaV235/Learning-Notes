package com.deltav;

import org.junit.Test;

/**
 * @author DeltaV235
 * @version 1.0
 * @date 2021/8/1 12:32
 */
public class StringSpliceTest {
    /**
     * Verify if the compiler will join multi standalone string literal to a big one.
     */
    @Test
    public void test1() {
        String s1 = "a" + "b" + "c";
        String s2 = "abc";

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
    }

    /**
     * Before and after splicing, as long as one of them is a variable, the result is in the heap
     */
    @Test
    public void test2() {
        String s1 = "javaEE";
        String s2 = "hadoop";

        String s3 = "javaEEhadoop";
        String s4 = "javaEE" + "hadoop";//编译期优化
        //如果拼接符号的前后出现了变量，则相当于在堆空间中new String()，具体的内容为拼接的结果：javaEEhadoop
        String s5 = s1 + "hadoop";
        String s6 = "javaEE" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4);//true
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//false
        System.out.println(s3 == s7);//false
        System.out.println(s5 == s6);//false
        System.out.println(s5 == s7);//false
        System.out.println(s6 == s7);//false
        //intern():判断字符串常量池中是否存在javaEEhadoop值，如果存在，则返回常量池中javaEEhadoop的地址；
        //如果字符串常量池中不存在javaEEhadoop，则在常量池中加载一份javaEEhadoop，并返回次对象的地址。
        String s8 = s6.intern();
        System.out.println(s3 == s8);//true
    }

    @Test
    public void test3() {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";

        /*
        如下的s1 + s2 的执行细节：(变量s是我临时定义的）
        ① StringBuilder s = new StringBuilder();
        ② s.append("a")
        ③ s.append("b")
        ④ s.toString()  --> 约等于 new String("ab")，但不等价

        补充：在jdk5.0之后使用的是StringBuilder,在jdk5.0之前使用的是StringBuffer
        */
        String s4 = s1 + s2;
        System.out.println(s3 == s4);
    }

    /**
     * 1. 字符串拼接操作不一定使用的是StringBuilder!
     * 如果拼接符号左右两边都是字符串常量或常量引用，则仍然使用编译期优化，即非StringBuilder的方式。
     * 2. 针对于final修饰类、方法、基本数据类型、引用数据类型的量的结构时，能使用上final的时候建议使用上。
     */
    @Test
    public void test4() {
        final String s1 = "a";
        final String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;
        System.out.println(s3 == s4);//true
    }

    /**
     * Test efficiency of "+" operator and StringBuilder
     * "*" operator: 221857 ms
     * StringBuilder: 14 ms
     */
    @Test
    public void test6() {
        long startTime = System.currentTimeMillis();
        final int highLevel = 1000000;
        method1(highLevel);
//        method2(highLevel);
        long endTime = System.currentTimeMillis();

        System.out.println("time cost = " + (endTime - startTime) + " ms");
    }

    private void method1(int highLevel) {
        String src = "";
        for (int i = 0; i < highLevel; i++) {
            src = src + "a";
        }
    }

    private void method2(int highLevel) {
        StringBuilder src = new StringBuilder(highLevel);
        for (int i = 0; i < highLevel; i++) {
            src.append("a");
        }
    }

}
