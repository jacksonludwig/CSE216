package com.jackson.app;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        BiFunction<Boolean, Boolean, Boolean> xor =
            (x, y) -> (!x && y) || (x && !y);
        System.out.println(xor.apply(false, true));

        BiFunction<Boolean, Boolean, Boolean> nand = (x, y) -> !(x && y);
        System.out.println(nand.apply(true, true));

        Pass p = new Pass();
        int i = 3;
        Pass.accept_reference(p);
        Pass.accept_primitive(i);
        int a = p.get() + i;
        System.out.println(a);

        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println(product(nums, 0, 4));

        List<String> words = List.of("apple", "ant", "bet");
        aMethod(words.get(0), words.get(1));
        for (String w : words)
            System.out.println(w);
    }

    static void aMethod(String s, String t) {
        t += s;
        s += "a";
    }

    public static int product(List<Integer> nums, int start, int end) {
        return nums.stream()
            .skip(start)
            .limit(end - start)
            .reduce(1, (x, y) -> x * y);
    }

    public static List<String> concatAll(Collection<List<String>> lists) {
        return lists.stream().flatMap(Collection::stream).collect(Collectors.toList()));
    }
}
