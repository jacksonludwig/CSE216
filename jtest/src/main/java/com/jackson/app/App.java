package com.jackson.app;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

    public class A<B> {
        private final B b;

        public A(B b) {
            this.b = b;
        }

        public B getValue() {
            return this.b;
        }
    }
}
