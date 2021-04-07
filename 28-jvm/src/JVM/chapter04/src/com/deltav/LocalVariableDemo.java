package com.deltav;

/**
 * 查看字节码中的 Local Variable Table
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/4/6 23:40
 */
public class LocalVariableDemo {
    public static void main(String[] args) {
        int testInteger = 0;
        LocalVariableDemo localVariableDemo = new LocalVariableDemo();
    }

    public void instanceMethod(int parameter) {
        String testString = "test";
        StackOverFlowDemo stackOverFlowDemo = new StackOverFlowDemo();
    }

    public String slotReuseTestMethod() {
        int i = 1;
        {
            int j = 3;
            System.out.println(i);
        }
        int k = i++;
        return "";
    }
}
