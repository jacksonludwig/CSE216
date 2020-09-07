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
    }

    private static void doSomething(String thing) {
        System.out.println(thing);
    }

    private static int testMethod() {
        return 123;
    }
}
