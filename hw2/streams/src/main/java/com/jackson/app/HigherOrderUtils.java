package com.jackson.app;

import java.util.function.BiFunction;

public class HigherOrderUtils {
    interface NamedBiFunction<T, U, R> extends BiFunction<T, U, R> {
        String name();
    }

    public static NamedBiFunction<Double, Double, Double> add =
        new NamedBiFunction<Double, Double, Double>() {
            @Override
            public Double apply(Double x, Double y) {
                return x + y;
            }

            @Override
            public String name() {
                return "add";
            }
        };

    public static NamedBiFunction<Double, Double, Double> subtract =
        new NamedBiFunction<Double, Double, Double>() {
            @Override
            public Double apply(Double x, Double y) {
                return x - y;
            }

            @Override
            public String name() {
                return "diff";
            }
        };

    public static NamedBiFunction<Double, Double, Double> multiply =
        new NamedBiFunction<Double, Double, Double>() {
            @Override
            public Double apply(Double x, Double y) {
                return x * y;
            }

            @Override
            public String name() {
                return "mult";
            }
        };

    public static NamedBiFunction<Double, Double, Double> divide =
        new NamedBiFunction<Double, Double, Double>() {
            @Override
            public Double apply(Double x, Double y) {
                if (y == 0)
                    throw new ArithmeticException("Cannot divide by zero");
                return x / y;
            }

            @Override
            public String name() {
                return "div";
            }
        };
}
