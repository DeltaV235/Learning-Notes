package com.deltav;


/**
 * @author DeltaV235
 * @version 1.0
 */
public class GenericMethod {
    public static void main(String[] args) {
        GenericMethod genericMethod = new GenericMethod();
        genericMethod.genericMethod("test");
    }

    public <T> void genericMethod(T t) {
        System.out.println("t.hashCode() = " + t.hashCode());
    }
}
