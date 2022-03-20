package com.deltav.loadcase;

import org.junit.Test;

import java.io.*;

/**
 * 类的主动加载
 * <p>
 * 1. 当创建一个类的实例时，比如使用 new 关键字，或者通过反射、克隆、反序列化
 * 2. 当调用类的静态方法时，即当使用了字节码 `invokestatic` 指令
 *
 * @author DeltaV235
 * @version 1.0
 */
public class ActiveLoad1 {
    public static void main(String[] args) {
        Order order = new Order();
    }

    @Test
    public void serializationObject() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("order.dat"))) {
            oos.writeObject(new Order());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deserializationObject() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("order.dat"))) {
            Order order = (Order) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void invokeStaticMethod() {
        Order.method();
    }

    @Test
    public void test() {
        System.out.println("User.num1 = " + User.num1);
    }
}

class Order implements Serializable {
    public static final String str = "test";

    static {
        System.out.println("Order class initialization processing...");
    }

    public static void method() {
        System.out.println("Order static method...");
    }
}
