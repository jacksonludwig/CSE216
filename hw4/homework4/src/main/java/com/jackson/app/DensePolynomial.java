package com.jackson.app;

public class DensePolynomial implements Polynomial {
    private int[] values;

    public DensePolynomial(int[] values) { this.values = values; }

    /**
     * Throws an IllegalArgumentException if the length of the array is zero.
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

    @Override
    public int getCoefficient(int d) {
        if(values.length - 1 >= d) {
            return values[d];
        }
        return 0;
    }

    @Override
    public boolean isZero() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Polynomial add(Polynomial q) {
        // TODO Auto-generated method stub
        return null;
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
