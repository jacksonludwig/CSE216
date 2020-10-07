package com.jackson.app;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        doSomething("hey");
        System.out.println(testMethod());
        System.out.println(listThing());
        System.out.println(mult(5, 3));
        System.out.println(pow(5, 3));
        Scanner scanner = new Scanner(System.in);
        String len = scanner.nextLine();
        System.out.println("You typed: " + len);
        scanner.close();
        System.out.println("The length of what you typed was: " + len.length());
    }

    private static void doSomething(String thing) {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        System.out.println(thing);
        if (thing != null) {
            System.out.println("hey");
        }
    }

    private static int testMethod() {
        return 123;
    }

    private static ArrayList<String> listThing() {
        ArrayList<String> stuff = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            stuff.add(String.valueOf(i));
        }
        return stuff;
    }

    private static int mult(int x, int y) {
        if (y == 0)
            return 0;
        return x + mult(x, y - 1);
    }

    private static int pow(int x, int y) {
        if (y == 0)
            return 1;
        return x * pow(x, y - 1);
    }

    public static String returnString(String string) {
        return string;
    }
}
