package com.jackson.app;

import java.util.Arrays;
import java.util.Collection;

public class App {
    public static void main(String[] args) {
        Collection<String> strings = Arrays.asList("Hey", "Yesldkfjgd", "no", "test1", "test2", "Hey2dkssds");
        String onlyCaps = StreamUtils.longest(strings, true);
        System.out.println(onlyCaps);
    }
}
