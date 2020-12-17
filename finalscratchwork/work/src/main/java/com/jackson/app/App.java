package com.jackson.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        List<Subject> values = new ArrayList<>();
        for (int i = 1; i < 15; i++) {
            values.add(new Subject(String.valueOf(i)));
        }
        int numberOfObjectsPerThread = 6;
        int cursor = 0;
        List<Subject> subjectList = new ArrayList<>();
        for (Subject v : values) {
            cursor++;
            subjectList.add(v);
            if (cursor < numberOfObjectsPerThread)
                continue;
            final List<Subject> subjectList2 = new ArrayList<>(subjectList);
            Task task = new Task() {
                public void run() {
                    for (Subject o : subjectList2)
                        o.print();
                }
            };
            init(task);
            subjectList.clear();
            cursor = 0;
        }
        while (process()) {continue;}
        System.out.println("Done.");
    }

    static class Subject {
        String s;
        public Subject(String s) { this.s = s; }
        public void print() { System.out.println(s); }
    }
    static class Task extends Thread {
        public void run() {}
    }
    static Set<Task> tasks = new HashSet<>();
    static void init(Task task) {
        task.start();
        tasks.add(task);
    }
    static boolean process() {
        boolean processing = false;
        for (Task task : tasks) {
            if (task.isAlive())
                processing = true;
        }
        return processing;
    }

    static class A {
        void m(A x) { System.out.println("AA"); }
        void m(B x) { System.out.println("AB"); }
    }
    static class B extends A {
        void m(A x) { System.out.println("BA"); }
        void m(B x) { System.out.println("BB"); }
    }
    static class C extends B {
        void m(A x) { System.out.println("CA"); }
        void m(B x) { System.out.println("CB"); }
    }

    class BasicMath {
        float add(float a, float b) { return a + b; }
        int divide(int a, int b) { return a / b; }
    }
}
