package com.jackson.app;

import java.util.List;
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
                if (y == 0d)
                    throw new ArithmeticException("Cannot divide by zero");
                return x / y;
            }

            @Override
            public String name() {
                return "div";
            }
        };

    /**
    * Applies a given list of bifunctions -- functions that take two arguments
    of a certain type and produce
    * a single instance of that type -- to a list of arguments of that type. The
    functions are applied
    * iteratively, and the result of each function is stored in the list
    iteratively as well, to be
    * used by the next bifunction in the next iteration. E.g.,
    *
    List<Double> args = Arrays.asList(1d, 1d, 3d, 0d, 4d), and
    *
    List<NamedBiFunction<Double, Double, Double>> bfs = [add, multiply, add,
    divide],
    * <code>zip(args, bfs)</code> will proceed iteratively as follows:
    *
    - index 0: the result of add(1,1) is stored in args[1] to yield args =
    [1,2,3,0,4]
    *
    - index 1: the result of multiply(2,3) is stored in args[2] to yield args =
    [1,2,6,0,4]
    *
    - index 2: the result of add(6,0) is stored in args[3] to yield args =
    [1,2,6,6,4]
    *
    - index 3: the result of divide(6,4) is stored in args[4] to yield args =
    [1,2,6,6,1]
    *
    * @param args:
    the arguments over which <code>bifunctions</code> will be applied.
    * @param bifunctions: the list of bifunctions that will be applied on
    <code>args</code>.
    * @param <T>:
    the type parameter of the arguments (e.g., Integer, Double)
    * @return
    the item in the last index of <code>args</code>, which has the final
    *
    result of all the bifunctions being applied in sequence.
    */
    public static <T> T zip(List<T> args,
                            List<NamedBiFunction<T, T, T>> bifunctions) {
        for (int i = 1; i < args.size(); i++) {
            args.set(i, bifunctions.get(i - 1).apply(args.get(i - 1), args.get(i)));
        }

        return args.get(args.size() - 1);
    }
}
