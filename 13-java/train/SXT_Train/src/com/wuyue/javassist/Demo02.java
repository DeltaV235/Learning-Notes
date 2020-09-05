package com.wuyue.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class Demo02 {
    public static void test01() throws Exception {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.wuyue.javassist.User");

        CtMethod cm = CtMethod.make("public String HelloWorld(String name){return (\"Hello World! \"+name);}", cc);
        CtMethod cm2 = new CtMethod(cp.get("java.lang.String"), "HelloWorld",
                new CtClass[]{cp.get("java.lang.String")}, cc);
        cm2.setBody("{return \"不欢迎，滚\"+$1;}");
        cc.addMethod(cm2);

        Class<?> clz = cc.toClass();
        Object user = clz.getConstructor(int.class, String.class).newInstance(1000, "wuyue");
        String returnStr = (String) clz.getMethod("HelloWorld", String.class).invoke(user, "GoLand");
        System.out.println(returnStr);
    }

    public static void test02() throws Exception {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.wuyue.javassist.User");
        CtMethod method = cc.getDeclaredMethod("getName", new CtClass[]{});
        method.insertAfter("System.out.println(\"testString\");");

        Class<?> clz = cc.toClass();
        Object user = clz.getConstructor(int.class, String.class).newInstance(1000, "wuyue");
//        String returnStr = (String) clz.getMethod("getName", null).invoke(user, null);
//        System.out.println(returnStr);
    }

    public static void main(String[] args) throws Exception {
        test02();
    }
}
