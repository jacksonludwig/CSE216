package com.jackson.app;

public class DensePolynomial implements Polynomial {
    private int[] values;

    public DensePolynomial(int[] values) { this.values = values; }

    /**
     * Throws an IllegalArgumentException if the length of the array is zero.
     * */
    @Override
    public int degree() {
        // TODO account of zero coefficients and finish doc
        if (values.length == 0)
            throw new IllegalArgumentException(
                "A polynomial must have at least one value in order to record it's degree");
        return values.length - 1;
    }

    @Override
    public int getCoefficient(int d) {
        // TODO Auto-generated method stub
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
