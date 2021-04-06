package com.deltav;

/**
 * Stack Over Flow 测试类
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/4/6 23:00
 */
public class StackOverFlowDemo {
    private static int count = 0;

    public static void main(String[] args) {
        try {
            String testParameter = "test";
            int testInteger = 0;
            StackOverFlowDemo stackOverFlowDemo = new StackOverFlowDemo();
            System.out.println(++count);
            main(null);
        } catch (StackOverflowError error) {
            System.out.println("Stack Over Flow occurred!\n");
        }
    }
}
