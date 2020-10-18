package com.jackson.app;

import java.util.Arrays;
import java.util.Collection;

public class App {
    public static void main(String[] args) {
        Collection<String> strings = Arrays.asList("Hey", "Yes", "no", "test", "test2", "Hey2");
        Collection<String> onlyCaps = StreamUtils.capitalized(strings);
        System.out.println(onlyCaps);
    }
}
