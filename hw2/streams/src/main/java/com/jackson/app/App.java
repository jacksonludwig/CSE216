package com.jackson.app;

import com.jackson.app.HigherOrderUtils.NamedBiFunction;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Collection<String> strings = Arrays.asList(
            "hey", "Yesldkfjgd", "no", "test1", "test2", "Hey2dkssds");
        String onlyCaps = StreamUtils.longest(strings, true);
        System.out.println(onlyCaps);

        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "test");
        map.put(1, "hey");
        map.put(2, "this is the third thing");

        System.out.println(StreamUtils.flatten(map));

        System.out.println(HigherOrderUtils.divide.apply(5.5, 0.1));

        List<Double> nums = Arrays.asList(1d, 1d, 3d, 0d, 4d);
        List<NamedBiFunction<Double, Double, Double>> bfs =
            Arrays.asList(HigherOrderUtils.add, HigherOrderUtils.multiply,
                          HigherOrderUtils.add, HigherOrderUtils.divide);
        System.out.println(HigherOrderUtils.zip(nums, bfs));
    }
}
