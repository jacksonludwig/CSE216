package com.jackson.app;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        Collection<String> strings = Arrays.asList("hey", "Yesldkfjgd", "no", "test1", "test2", "Hey2dkssds");
        String onlyCaps = StreamUtils.longest(strings, true);
        System.out.println(onlyCaps);

        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "test");
        map.put(1, "hey");
        map.put(2, "this is the third thing");

        System.out.println(StreamUtils.flatten(map));

        System.out.println(HigherOrderUtils.add.apply(5.5, 2.0));
    }
}
