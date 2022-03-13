package com.deltav.instruction;

import org.junit.Test;

import java.io.File;

/**
 * 对象、数组的创建于访问指令
 *
 * @author DeltaV235
 * @version 1.0
 */
public class NewTest {
    /**
     * 1. 创建指令
     */
    public void newInstance() {
        new Object();

        File file = new File("test.mkv");
    }

    /**
     * 创建数组指令
     */
    public void newArray() {
        int[] intArray = new int[10];
        long[] longArray = new long[1];
        Object[] objArray = new Object[10];
        int[][] intMultiArray = new int[10][10];
        // bipush 10
        // anewarray #7 <[Ljava/lang/String;>
        String[][] strArray = new String[10][];
        // bipush 10
        // iconst_5
        // multianewarray #8 <[[Ljava/lang/String;> dim 2
        String[][] strArray2 = new String[10][5];
    }

    /**
     * 2. 字段访问指令
     */
    public void sayHello() {
        System.out.println("hello");
    }

    public void setOrderId() {
        Order order = new Order();
        order.id = 199;
        System.out.println(order.id);

        Order.name = "ORDER";
        System.out.println(Order.name);
    }

    /**
     * 3. 数组操作指令
     */
    public void setArray() {
        int[] intArray = new int[10];
        intArray[3] = 20;
        System.out.println(intArray[1]);

        boolean[] arr = new boolean[10];
        arr[1] = true;
    }

    @Test
    public void arrLength() {
        double[] arr = new double[10];
        System.out.println(arr.length);
    }

    /**
     * 4. 类型检查指令
     *
     * @param obj object
     * @return String
     */
    public String checkCast(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        } else {
            return null;
        }
    }

}

class Order {
    static String name;
    int id;
}
