package com.deltav;

/**
 * @author DeltaV235
 * @version 1.0
 */
public class GenericClass<T> {
    private T key;

    public GenericClass(T key) {
        this.key = key;
    }

    public static void main(String[] args) {
        GenericClass<String> stringGenericClass = new GenericClass<>("test");
        System.out.println("stringGenericClass.getKey() = " + stringGenericClass.getKey());
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }
}
