package com.jackson.app;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        doSomething("hey");
        System.out.println(testMethod());
        String[] strings = {"hi"};
        Object[] objects = strings;
        objects[0] = new Integer(5);
    }

    private static void doSomething(String thing) {
        System.out.println(thing);
    }

    private static int testMethod() {
        return 123;
    }
}
