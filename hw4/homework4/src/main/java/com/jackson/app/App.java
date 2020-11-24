package com.jackson.app;

public class App {
    public static void main(String[] args) {
        int[] v = {-5, 2, 0, 4};
        DensePolynomial p = new DensePolynomial(v);
        // System.out.println(p.degree());
        // System.out.println(p.getCoefficient(1));
        // int[] v2 = { 1 };
        // DensePolynomial p2 = new DensePolynomial(v2);
        // System.out.println(p2.degree());
        // System.out.println(p2.getCoefficient(0));

        // System.out.println("----");
        // System.out.println(p.add(p2).degree());
        // System.out.println("----");
        // System.out.println(p.getCoefficient(0));
        // System.out.println(p.add(p2).getCoefficient(0));
        int[] test = new DensePolynomial("1x^4 + 5x^2 + -2x + -5").getValues();
        int[] test2 = {-5, -2, 5, 0, 1};
        for (int i = 0; i < 5; i++) {
            System.out.println("test: " + test[i]);
            System.out.println("test2: " + test2[i]);
        }
    }
}
