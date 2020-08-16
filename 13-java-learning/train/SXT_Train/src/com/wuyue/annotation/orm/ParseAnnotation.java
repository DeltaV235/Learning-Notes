package com.wuyue.annotation.orm;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ParseAnnotation {
    public static void main(String[] args) {
        try {
            Class clz = Class.forName("com.wuyue.annotation.orm.Student");
            Annotation annotation = clz.getAnnotation(Table.class);
            System.out.println(annotation.annotationType() + "---" + ((Table) annotation).value());
            Field[] fields = clz.getDeclaredFields();
            for (Field field : fields) {
                com.wuyue.annotation.orm.Field annotation2 = field.getDeclaredAnnotation(com.wuyue.annotation.orm.Field.class);
                System.out.println("--------------------------------------");
                System.out.println(field.getName());
                System.out.println("field:" + annotation2.field());
                System.out.println("type:" + annotation2.type());
                System.out.println("length:" + annotation2.length());
                System.out.println("isNull:" + annotation2.isNull());
                System.out.println("isPri:" + annotation2.isPri());
                System.out.println("--------------------------------------");
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
