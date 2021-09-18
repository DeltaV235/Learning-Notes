package com.deltav;

/**
 * @author DeltaV235
 * @version 1.0
 */
public class GenericInterClass implements GenericInterface<String, Integer> {
    public static void main(String[] args) {
        GenericInterClass genericInterClass = new GenericInterClass();
        System.out.println("genericInterClass.print(2938) = " + genericInterClass.print(2938));
    }

    @Override
    public String print(Integer integer) {
        return integer.toString();
    }
}
