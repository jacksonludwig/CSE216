package com.jackson.app;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("You typed: " + scanner.nextLine());
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
