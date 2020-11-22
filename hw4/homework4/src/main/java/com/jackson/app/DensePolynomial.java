package com.jackson.app;

import java.util.HashMap;
import java.util.Map;

public class DensePolynomial implements Polynomial {
    private int[] values;

    public DensePolynomial(int[] values) { this.values = values; }

    public DensePolynomial(int size) { this.values = new int[size]; }

    /**
     * @throws IllegalArgumentException if the length of the array is zero.
     * */
    @Override
    public int degree() {
        if (values.length == 0)
            throw new IllegalArgumentException(
                "A polynomial must have at least one value in order to record it's degree");

        for (int i = values.length - 1; i != 0; i--)
            if (values[i] != 0)
                return i;

        return 0;
    }

    /**
     * @throws IllegalArgumentException the inputted degree to access is less
     *     than zero.
     * */
    @Override
    public int getCoefficient(int d) {
        if (d < 0)
            throw new IllegalArgumentException(
                "Dense polynomials must only have zero or positive exponents");

        if (values.length - 1 >= d) {
            return values[d];
        }

        return 0;
    }

    @Override
    public boolean isZero() {
        // TODO Auto-generated method stub
        return false;
    }

    // TODO use in arithmetic methods
    private SparsePolynomial toSparsePolynomial() {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            map.put(i, values[i]);
        }
        return new SparsePolynomial(map);
    }

    @Override
    public Polynomial add(Polynomial q) {
        // TODO Auto-generated method stub
        // Note that this must be able to add two Polys of map or array backend,
        // but only map allows negatives. Therefore, things must be
        // intelligently converted.

        Polynomial smallest = this.degree() <= q.degree() ? this : q;
        Polynomial largest = this.degree() > q.degree() ? this : q;
        int[] addedValues = new int[largest.degree() + 1];

        for (int i = largest.degree(); i != -1; i--) {
            addedValues[i] = largest.getCoefficient(i) + smallest.getCoefficient(i);
        }

        return new DensePolynomial(addedValues);
    }

    @Override
    public Polynomial multiply(Polynomial q) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Polynomial subtract(Polynomial q) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Polynomial minus() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean wellFormed() {
        // TODO Auto-generated method stub
        return false;
    }
}
