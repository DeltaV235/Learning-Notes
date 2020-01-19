package com.wuyue.server.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@SuppressWarnings("all")
public class UserTest {
    public static void main(String[] args) {
        try {
            Class<User> clz = (Class<User>) Class.forName("com.wuyue.server.reflect.User");
            Field[] fields = clz.getDeclaredFields();
            Constructor constructor = clz.getConstructor(int.class, String.class);
            User user = (User) constructor.newInstance(19, "wuyue");
            for (Field field : fields) {
                field.setAccessible(true);
                System.out.println("Field Name:" + field.getName() + "---" + field.get(user));
            }

            System.out.println("\n");
            Method[] methods = clz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println("Method Name:" + method.toString());
            }

            Method staticMethod = clz.getMethod("setId", int.class);
            staticMethod.invoke(null, 2000);
//            System.out.println(clz.getMethod("getId", null).invoke(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
