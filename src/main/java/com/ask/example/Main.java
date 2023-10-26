package com.ask.example;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
//        // Press ⌥⏎ with your caret at the highlighted text to see how
//        // IntelliJ IDEA suggests fixing it.
//        System.out.print("Hello and welcome!");
//
//        // Press ⌃R or click the green arrow button in the gutter to run the code.
//        for (int i = 1; i <= 5; i++) {
//
//            // Press ⌃D to start debugging your code. We have set one breakpoint
//            // for you, but you can always add more by pressing ⌘F8.
//            System.out.println("i = " + i);
//        }



//        nullableCheck();
//        setDefaultValue();
//        streamByArray();
//        streamByList();
//        streamToSet();
//        streamToCollection();
//        streamToMap();
//        streamAndMap();
        streamAndFlatMap();
//        getEnum();
//        getEnumFromParam();
//        getMultiValueMap();
    }




    static void nullableCheck() {
        String nullString = null;
        String emptyString = "";

        if (Objects.isNull(nullString))
            System.out.println("nullString is null");

        if (Objects.nonNull(emptyString))
            System.out.println("emptyString is not null");
    }

    static void setDefaultValue() {
        BigDecimal bd = null;

        BigDecimal defaultValue = Optional.ofNullable(bd).orElse(BigDecimal.ZERO);
        System.out.println(defaultValue);

        Optional.ofNullable(bd).orElseThrow(() -> new IllegalArgumentException("db is null"));
    }

    static void streamByArray() {
        String[] fruits = { "apple", "banana", "orange", "grape", "banana" };
        Stream stream = Arrays.stream(fruits);
        System.out.println(stream);
    }

    static void streamByList() {
        List<String> fruits = new ArrayList<String>(
                Arrays.asList("apple", "banana", "orange", "grape", "banana"));
        Stream stream = fruits.stream();
        System.out.println(stream);
    }

    static void streamToSet() {
        List<String> fruits = new ArrayList<String>(
                Arrays.asList("apple", "banana", "orange", "grape", "banana"));
        Set<String> newSet = fruits.stream()
                .collect(Collectors.toCollection(
                        HashSet::new
                ));
        newSet.forEach(System.out::println);
    }

    static void streamToCollection() {
        List<String> fruits = new ArrayList<String>(
                Arrays.asList("apple", "banana", "orange", "grape", "banana"));
        List<String> newList = fruits.stream()
                .collect(Collectors.toCollection(
                        Arrays::asList
                ));
        newList.forEach(System.out::println);
    }

    static void streamToMap() {
        List<String> fruits = new ArrayList<String>(
                Arrays.asList("apple", "banana", "orange", "grape", "banana"));
        Map<String, Integer> newMap = new HashSet<>(fruits).stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        String::length
                ));
        newMap.forEach((key, value) -> System.out.println(key + " " + value));
    }

    static void streamAndMap() {
        List<String> fruits = new ArrayList<String>(
                Arrays.asList("apple", "banana", "orange", "grape", "banana"));
        List<String> newList = fruits.stream()
                .map(String::toUpperCase)
                .toList();
        newList.forEach(System.out::println);
    }

    static void streamAndFlatMap() {
        List<List<String>> listInList = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("orange", "grape", "banana"));

//        List<String> newList = listInList.stream()
//                .flatMap(Collection::stream)
//                .distinct()
//                .toList();

        List<String> newList = listInList.stream()
                .flatMap( list -> {
                    for (String s: list) {
                        System.out.println(s);
                    }
                    System.out.println("------------------------");
                    return list.stream();
                })
                .distinct()
                .toList();

        newList.forEach(System.out::println);
    }

    static void getEnum() {
        System.out.println(Gender.MALE.getParam());
    }

    static void getEnumFromParam() {
        System.out.println(Gender.fromParam("male"));
    }

    static void getMultiValueMap() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("Accept-Encoding", "compress;q=0.5");
        map.add("Accept-Encoding", "gzip;q=1.0");

        map.forEach((key, value) -> System.out.println(key + " " + value));
    }
}

enum Gender {
    MALE("male"),
    FEMALE("female");

    private final String param;

    private static final Map<String, Gender> paramMap =
            Arrays.stream(Gender.values())
                    .collect(Collectors.toMap(
                            Gender::getParam,
                            Function.identity()
                    ));

    Gender(String param) {
        this.param = param;
    }

    static Gender fromParam(String param) {
        return Optional.ofNullable(param)
                .map(paramMap::get)
                .orElseThrow(() -> new IllegalArgumentException("param is not valid"));
    }

    String getParam() {
        return this.param;
    }
}

