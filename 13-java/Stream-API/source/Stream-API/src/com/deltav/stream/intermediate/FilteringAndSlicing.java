package com.deltav.stream.intermediate;

import java.util.List;

public class FilteringAndSlicing {
    private static final List<Person> personList;

    static {
        personList = List.of(
                new Person("John", 30, "USA"),
                new Person("Sarah", 25, "USA"),
                new Person("Alice", 35, "UK"),
                new Person("Bob", 40, "UK"),
                new Person("Tom", 45, "USA"),
                new Person("Tim", 50, "USA"),
                new Person("Sam", 55, "UK"),
                new Person("Kim", 60, "UK"),
                new Person("Kim", 60, "UK"),
                new Person("Jim", 65, "USA"),
                new Person("Jim", 65, "USA")
        );
    }

    public static void main(String[] args) {
        filter();
        distinct();
        distinctForObject();
        limit();
        skip();

        sorted();
    }

    private static void distinctForObject() {
        System.out.println("Distinct for Object");
        personList.stream()
                .distinct()
                .forEach(System.out::println);
    }

    private static void filter() {
        System.out.println("Filter");
        personList.stream()
                .filter(person -> person.getNationality().equals("USA"))
                .forEach(person -> System.out.println(person.getName()));
    }

    private static void distinct() {
        System.out.println("Distinct");
        personList.stream()
                .map(Person::getNationality)
                .distinct()
                .forEach(System.out::println);
    }

    private static void limit() {
        System.out.println("Limit");
        personList.stream()
                .limit(3)
                .forEach(person -> System.out.println(person.getName()));
    }

    private static void skip() {
        System.out.println("Skip");
        personList.stream()
                .skip(3)
                .forEach(person -> System.out.println(person.getName()));
    }

    private static void sorted() {
        System.out.println("Sorted");
        personList.stream()
                .sorted((person1, person2) -> person1.getName().compareTo(person2.getName()))
                .forEach(person -> System.out.println(person.getName()));
    }
}
