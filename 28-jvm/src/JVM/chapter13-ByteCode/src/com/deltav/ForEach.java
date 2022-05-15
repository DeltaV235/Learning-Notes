package com.deltav;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 测试 for-each 语法糖
 *
 * @author DeltaV235
 * @version 1.0
 */
public class ForEach {

    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();
        Iterator iterator = ((List) collection).iterator();

        for (String s : collection) {
            System.out.println(s);
        }

        Object[] objects = new Object[10];
        System.out.println(objects.length);

        objects[0] = new Object();
        System.out.println(objects.length);
    }

    public static void test() {
        Collection<String> collection = new ArrayList<>();
        for (String str : collection) {
            System.out.println(str);
        }
    }
}
