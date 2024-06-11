package com.deltav.stream.intermediate;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;


public class Mapping {
    private static final List<Person> personList;
    private static final List<List<Person>> nestedPersonList;

    static {
        personList = List.of(
                new Person("john", 30, "usa"),
                new Person("sarah", 25, "usa"),
                new Person("alice", 35, "uk"),
                new Person("bob", 40, "uk"),
                new Person("tom", 45, "usa"),
                new Person("tim", 50, "usa"),
                new Person("sam", 55, "uk"),
                new Person("kim", 60, "uk"),
                new Person("jim", 65, "usa")
        );

        nestedPersonList = List.of(
                List.of(
                        new Person("John", 30, "USA"),
                        new Person("Sarah", 25, "USA"),
                        new Person("Alice", 35, "UK")
                ),
                List.of(
                        new Person("Bob", 40, "UK"),
                        new Person("Tom", 45, "USA"),
                        new Person("Tim", 50, "USA")
                ),
                List.of(
                        new Person("Sam", 55, "UK"),
                        new Person("Kim", 60, "UK"),
                        new Person("Jim", 65, "USA")
                )
        );

    }

    public static void main(String[] args) {
        map();

        System.out.println();
        flatMap();

        System.out.println();
        flatMap2();

        System.out.println();
        flatMap3();
    }

    private static void map() {
        System.out.println("Map");
        personList.stream()
                .map(Person::getName)
                .forEach(System.out::println);
    }

    private static void flatMap() {
        System.out.println("FlatMap");
        List<List<String>> list = List.of(
                List.of("a", "b"),
                List.of("c", "d"),
                List.of("e", "f")
        );
        list.stream()
                .flatMap(List::stream)
                .forEach(System.out::println);
    }

    private static void flatMap2() {
        System.out.println("FlatMap for Object");

        Stream<List<Person>> personListStream = nestedPersonList.stream();
        Stream<Person> personStream = personListStream.flatMap(Collection::stream);
        Stream<String> nameStream = personStream.map(Person::getName);
        nameStream.forEach(System.out::println);
    }

    private static void flatMap3() {
        System.out.println("FlatMap for Object - 2nd approach");

        nestedPersonList.stream()
                .flatMap(people -> people.stream().map(Person::getName))
                .forEach(System.out::println);
    }

}
