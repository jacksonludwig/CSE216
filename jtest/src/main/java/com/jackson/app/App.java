package com.jackson.app;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("hey");
        System.out.println("test");
        for (int i = 0; i < 10; i++) {
            System.out.println("test3");
        }
    }

    public class Inner {
        private int num;

        public Inner(int num) {
            this.setNum(num);
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "Inner [num=" + num + "]";
        }
    }
}
