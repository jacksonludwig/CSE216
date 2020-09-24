package com.jackson.app;

import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        doSomething("hey");
        System.out.println(testMethod());
        String[] strings = { "hi" };
        Object[] objects = strings;
        objects[0] = 5;
        System.out.println("hello");
        Thing thing = new Thing("hey", "37849658");
        System.out.println(thing);
        System.out.println("App.main()");
    }

    private static void doSomething(String thing) {
        System.out.println(thing);
    }

    private static int testMethod() {
        return 123;
    }

    @Override
    public String toString() {
        return "App []";
    }
}
