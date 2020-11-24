package com.jackson.app;

import java.util.HashMap;
import java.util.Map;

public class DensePolynomial implements Polynomial {
    private int[] values;

    public DensePolynomial(int[] values) { this.values = values; }

    public DensePolynomial(int size) { this.values = new int[size]; }

    // TODO finish
    public DensePolynomial(String poly) {
        this.values = tokenizeEquation(poly);
    }

    // TODO finish (exceptions?)
    private int[] tokenizeEquation(String poly) {
        String[] p = poly.replaceAll("\\s", "").split("[+]");

        int highestDegree = Integer.parseInt(p[0].substring(p[0].indexOf('^') + 1));
        int[] tokenized = new int[highestDegree + 1];

        for(int i = 0; i < p.length; i++) {
            int xLoc = p[i].indexOf('x');
            int carrot_loc = p[i].indexOf('^');
            String coeff;
            String degree;
            if (xLoc != -1) {
                coeff = p[i].substring(0, xLoc);
                if (coeff.equals(""))
                    coeff = "1";
                if (carrot_loc != -1)
                    degree = p[i].substring(carrot_loc + 1);
                else
                    degree = "1";
            } else {
                coeff = p[i];
                degree = "0";
            }
            tokenized[Integer.parseInt(degree)] = Integer.parseInt(coeff);
        }
        return tokenized;
    }

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

    // This adds two dense polys only
    private DensePolynomial addDense(DensePolynomial p, DensePolynomial p2) {
        Polynomial smallest = p.degree() <= p2.degree() ? p : p2;
        Polynomial largest = p.degree() > p2.degree() ? p : p2;
        int[] addedValues = new int[largest.degree() + 1];

        for (int i = largest.degree(); i != -1; i--) {
            addedValues[i] =
                largest.getCoefficient(i) + smallest.getCoefficient(i);
        }

        return new DensePolynomial(addedValues);
    }

    @Override
    public Polynomial add(Polynomial q) {
        // TODO finish second part

        // first, check if the other one is dense and add them that way.
        if (q.getClass() == this.getClass())
            return addDense(this, (DensePolynomial)q);
        else {
            SparsePolynomial s = this.toSparsePolynomial();
            return s.add(q);
        }
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

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }
}
