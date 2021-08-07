package com.deltav;


/**
 * Interview question for String.
 * <p>
 * result:
 * jkd6: false / false / false / true
 * jdk7/8: false / true / true / true
 * 测试类 与 直接运行的 结果不一致
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/8/2 20:45
 */
public class StringIntern {
    //    @Test
    public static void main(String[] args) {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();

        String s4 = "11";
        System.out.println(s3 == s4);

        String s5 = new String(new char[]{'2', '2'});
        String s6 = s5.intern();
        System.out.println(s5 == s6);

        String s7 = "22";
        System.out.println(s7 == s6);
    }
}
