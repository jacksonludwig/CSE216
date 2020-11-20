package com.jackson.app;

public class App {
    public static void main(String[] args) {
        int[] v = {-5, 2, 0, 4};
        DensePolynomial p = new DensePolynomial(v);
        System.out.println(p.degree());
        System.out.println(p.getCoefficient(1));
        int[] v2 = { 1 };
        DensePolynomial p2 = new DensePolynomial(v2);
        System.out.println(p2.degree());
        System.out.println(p2.getCoefficient(0));
        // SparsePolynomial s = p.toMapPolynomial();
        // System.out.println(s.getValues());
    }
}
