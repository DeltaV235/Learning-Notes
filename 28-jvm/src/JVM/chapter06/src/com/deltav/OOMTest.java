package com.deltav;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.util.concurrent.TimeUnit;

/**
 * Method area OOM test demo.
 * <p>
 * Generate byte code by using ClassWrite then load in JVM to simulate Out of Memory of Method Area (Metaspace in
 * JDK8 and PermGen Space in JDK7 and before).
 * <p>
 * set method area memory size to low value to occur the method area oom error more easily.
 * <p>
 * JVM Parameters:
 * <p>
 * JDK8:
 * -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 * <p>
 * JDK8 and before:
 * -XX:PermSize=10m -XX:MaxPermSize=10m
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/5/20 0:21
 */
public class OOMTest extends ClassLoader {
    public static void main(String[] args) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int j = 0;
        try {
            OOMTest oomTest = new OOMTest();
            for (int i = 0; i < 10000; i++) {
                // create ClassWrite to generate binary byte code
                ClassWriter classWriter = new ClassWriter(0);
                // specified version, class name, modifier, package name, super class name and interface name
                classWriter.visit(Opcodes.V1_8,
                        Opcodes.ACC_PUBLIC,
                        "Class" + i,
                        null,
                        "java/lang/Object",
                        null);
                // get byte code
                byte[] code = classWriter.toByteArray();
                // load class by super class's defineClass method
                oomTest.defineClass("Class" + i, code, 0, code.length);
                j++;
            }
        } finally {
            System.out.println(j);
        }
    }
}
