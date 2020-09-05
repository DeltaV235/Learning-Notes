package com.wuyue.javassist;

import javassist.*;

public class Demo01 {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("com.wuyue.javassist.bean");

        // 创建属性
        CtField f1 = CtField.make("private int id;", cc);
        CtField f2 = CtField.make("private String name;", cc);
        cc.addField(f1);
        cc.addField(f2);

        // 创建方法
        CtMethod m1 = CtMethod.make("public int getId(){return this.id;}", cc);
        CtMethod m2 = CtMethod.make("public void setId(int id){this.id=id;}", cc);
        cc.addMethod(m1);
        cc.addMethod(m2);

        // 创建构造方法
        CtConstructor constructor = new CtConstructor(new CtClass[]{CtClass.intType, pool.get("java.lang.String")}, cc);
        constructor.setBody("{this.id=$1;this.name=$2;}");
        cc.addConstructor(constructor);

        cc.writeFile("src");
    }
}
