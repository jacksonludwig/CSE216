package com.jackson.app;

public class App {
    public static void main(String[] args) {
        int[] v = {-5, 2, 0, 4};
        DensePolynomial p = new DensePolynomial(v);
        System.out.println(p.degree());
    }
}
