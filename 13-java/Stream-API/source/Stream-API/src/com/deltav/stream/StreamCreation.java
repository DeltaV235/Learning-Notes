package com.deltav.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamCreation {
    public static void main(String[] args) {
        createStreamFromList();

        createStreamFromSet();

        createStreamFromMap();

        createStreamFromArray();

        createStreamFromStream();

        concatTwoStream();

        buildStreamViaBuilder();

        streamFromFile();

        intStream();

        doubleStream();

        rangeIntStream();

        rangeClosedIntStream();

        randomStream();

        boxedStreamFromIntStream();

        infiniteStreamFromGenerate();

        infiniteStreamFromIterate();

        parallelStream();
    }

    private static void parallelStream() {
        Stream<Integer> parallelStream = List.of(1, 2, 3).parallelStream();
        System.out.println("Parallel Stream");
        parallelStream.forEach(System.out::println);

        Stream<Integer> parallelStream2 = Stream.of(1, 2, 3).parallel();
        System.out.println("Parallel Stream 2");
        parallelStream2.forEach(System.out::println);
    }

    private static void infiniteStreamFromIterate() {
        System.out.println("infiniteStream from Stream.iterate");
        Stream.iterate(1, i -> i + 2).limit(5).forEach(System.out::println);
        System.out.println("infiniteStream from Stream.iterate with Predicate");
        Stream.iterate(0, integer -> integer <= 10, integer -> integer + 2).forEach(System.out::println);
    }

    private static void infiniteStreamFromGenerate() {
        System.out.println("infiniteStream from Stream.generate");
        Stream.generate(() -> "Aiden").limit(5).forEach(System.out::println);
        System.out.println("random double");
        Stream.generate(Math::random).limit(3).forEach(System.out::println);
    }

    private static void boxedStreamFromIntStream() {
        IntStream intStream = IntStream.of(1, 2, 3);
        Stream<Integer> boxedStream = intStream.boxed();
        System.out.println("Boxed Stream from IntStream");
        boxedStream.forEach(System.out::println);
    }

    private static void randomStream() {
        System.out.println("IntStream from Random.ints");
        new Random().ints(3).forEach(System.out::println);
    }

    private static void rangeClosedIntStream() {
        System.out.println("IntStream from IntStream.rangeClosed");
        IntStream.rangeClosed(1, 4).forEach(System.out::println);
    }

    private static void rangeIntStream() {
        System.out.println("IntStream from IntStream.range");
        IntStream.range(1, 4).forEach(System.out::println);
    }

    private static void doubleStream() {
        DoubleStream doubleStream = DoubleStream.of(1.2, 1.5, 100.99);
        System.out.println("DoubleStream from DoubleStream.of");
        doubleStream.forEach(System.out::println);
    }

    private static void intStream() {
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5);
        System.out.println("IntStream from IntStream.of");
        intStream.forEach(System.out::println);
    }

    private static void streamFromFile() {
        System.out.println("Stream from File");
        Path path = Paths.get("demo-file");
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void buildStreamViaBuilder() {
        Stream.Builder<String> streamBuilder = Stream.builder();
        streamBuilder.add("a").add("b");
        streamBuilder.accept("0");
        if (Math.random() > 0.5) {
            streamBuilder.add("c");
        }
        Stream<String> stream = streamBuilder.build();
//        streamBuilder.accept("exception");
        System.out.println("Stream from Stream Builder");
        stream.forEach(System.out::println);
    }

    private static void concatTwoStream() {
        // concat stream
        Stream<String> stream1 = Stream.of("a", "b", "c");
        Stream<String> stream2 = Stream.of("d", "e", "f");
        Stream<String> concatStream = Stream.concat(stream1, stream2);
        System.out.println("Concat Stream");
        concatStream.forEach(System.out::println);
    }

    private static void createStreamFromStream() {
        // Stream
        Stream<String> stream = Stream.of("a", "b", "c");
        System.out.println("Stream from Stream");
        stream.forEach(System.out::println);
    }

    private static void createStreamFromArray() {
        // Array
        String[] array = {"a", "b", "c"};
        Stream<String> streamFromArray = Arrays.stream(array);
        System.out.println("Stream from Array");
        streamFromArray.forEach(System.out::println);
    }

    private static void createStreamFromMap() {
        // Map
        Map<String, String> map = new HashMap<>();
        map.put("a", "d");
        map.put("b", "e");
        map.put("c", "f");
        Stream<String> streamKeySet = map.keySet().stream();
        Stream<String> streamValueSet = map.values().stream();
        System.out.println("Stream from Map");
        System.out.println("Key Set");
        streamKeySet.forEach(System.out::println);
        System.out.println("Value Set");
        streamValueSet.forEach(System.out::println);
    }

    private static void createStreamFromSet() {
        // Set
        Set<String> set = new HashSet<>(List.of("a", "b", "c"));
        Stream<String> streamFromSet = set.stream();
        System.out.println("Stream from Set");
        streamFromSet.forEach(System.out::println);
    }

    private static void createStreamFromList() {
        // List
        List<String> list = List.of("a", "b", "c");
        Stream<String> streamFromList = list.stream();
        System.out.println("Stream from List");
        streamFromList.forEach(System.out::println);
    }
}
