package com.wuyue;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Functional Interface test
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2020/11/21 13:50
 */
public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        Function<Integer, String> function = input -> input + "-demo";
        System.out.println("function.apply(20201121) = " + function.apply(20201121));

        Predicate<Integer> predicate = integer -> integer > 5;
        System.out.println("predicate.test(6) = " + predicate.test(6));

        Consumer<String> consumer = s -> System.out.println("s = " + s);
        consumer.accept("Hello World!");

        Supplier<String> supplier = () -> "Apple Silicon";
        System.out.println("supplier.get() = " + supplier.get());
    }
}
