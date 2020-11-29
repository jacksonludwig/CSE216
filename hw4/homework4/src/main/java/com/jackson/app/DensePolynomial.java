package com.jackson.app;

import java.util.Arrays;
import java.util.Map;

public class DensePolynomial implements Polynomial {
    private String inputPoly;
    private int[] values;

    // TODO finish
    public DensePolynomial(String poly) {
        inputPoly = poly;
        if (wellFormed())
            this.values = tokenizeEquation(poly);
        else
            throw new IllegalArgumentException("The inputted polynomial string was malformed");
    }

    private DensePolynomial(int[] arr) { this.values = arr; }

    // TODO finish (exceptions?)
    private int[] tokenizeEquation(String poly) {
        String[] p = poly.replaceAll("\\s", "").split("[+]");

        int highestDegree = 0;
        try {
            highestDegree =
                Integer.parseInt(p[0].substring(p[0].indexOf('^') + 1));
        } catch (NumberFormatException e) {
            highestDegree = 1;
        }
        int[] tokenized = new int[highestDegree + 1];

        for (int i = 0; i < p.length; i++) {
            int xLoc = p[i].indexOf('x');
            int carrotLoc = p[i].indexOf('^');
            String coeff;
            String degree;
            if (xLoc != -1) {
                coeff = p[i].substring(0, xLoc);
                if (coeff.equals(""))
                    coeff = "1";
                if (carrotLoc != -1)
                    degree = p[i].substring(carrotLoc + 1);
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

    @Override
    public String toString() {
        String poly = "";

        boolean allZero = true;
        for (int coeff : this.values) {
            if (coeff != 0)
                allZero = false;
        }
        if (allZero)
            return "0";

        for (int i = this.values.length - 1; i != -1; i--) {
            String coeff = String.valueOf(this.values[i]);
            if (!coeff.equals("0")) {
                switch (i) {
                case 0:
                    poly += coeff;
                    break;
                case 1:
                    if (coeff.equals("1"))
                        coeff = "";
                    poly += coeff + "x";
                    poly += " + ";
                    break;
                default:
                    if (coeff.equals("1"))
                        coeff = "";
                    poly += coeff + "x^" + i;
                    poly += " + ";
                    break;
                }
            }
        }

        // Remove extra plus at the end if there's no 0-degree coeff
        for (int i = poly.length() - 1; i != -1; i--) {
            if (Character.isDigit(poly.charAt(i)) ||
                Character.isLetter(poly.charAt(i)))
                break;
            if (poly.charAt(i) == '+')
                poly = poly.substring(0, i);
        }

        return poly.trim();
    }

    /**
     * Returns the degree of the polynomial.
     * @return the largest exponent with a non-zero coefficient.  If all terms
     *     have zero exponents, it returns 0.
     */
    @Override
    public int degree() {
        for (int i = values.length - 1; i != 0; i--)
            if (values[i] != 0)
                return i;

        return 0;
    }

    /**
     * Returns the coefficient corresponding to the given exponent.  Returns 0
     * if there is no term with that exponent in the polynomial.
     *
     * @param d the exponent whose coefficient is returned.
     * @return the coefficient of the term of whose exponent is d.
     * @throws IllegalArgumentException the inputted degree to access is less
     *     than zero.
     */
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

    /**
     * @return true if the polynomial represents the zero constant
     */
    @Override
    public boolean isZero() {
        // TODO Auto-generated method stub
        for (int coeff : this.values)
            if (coeff != 0)
                return false;
        return true;
    }

    // TODO use in arithmetic methods
    private DensePolynomial toDensePolynomial(Polynomial q) {
        return new DensePolynomial(q.toString());
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

    /**
     * Returns a polynomial by adding the parameter to the current instance.
     * Neither the current instance nor the parameter are modified.
     *
     * @param q the non-null polynomial to add to <code>this</code>
     * @return <code>this + </code>q
     * @throws NullPointerException if q is null
     * @throws IllegalArgumentException if q is a sparse polynomial with any
     *     negative exponents
     */
    @Override
    public Polynomial add(Polynomial q) {
        // TODO finish second part
        if (q == null)
            throw new NullPointerException("cannot add null polynomials");
        if (q.getClass() == SparsePolynomial.class) {
            Map<Integer, Integer> values = ((SparsePolynomial)q).getValues();
            for (int k : values.keySet())
                if (k < 0)
                    throw new IllegalArgumentException(
                        "Dense polynomials cannot be added to sparse polynomials with negative degrees");
        }
        return addDense(this, toDensePolynomial(q));
    }

    // this multiplies two dense polys only
    private DensePolynomial multiplyDense(DensePolynomial p,
                                          DensePolynomial p2) {
        Polynomial smallest = p.degree() <= p2.degree() ? p : p2; // 2x
        Polynomial largest = p.degree() > p2.degree() ? p : p2;   // 4x + 2
        int[] multipliedValues = new int[largest.degree() + 1];

        for (int i = smallest.degree(); i != -1; i--) {
            for (int j = largest.degree(); j != -1; j--) {
                int newExpo;
                int newCoeff = 0;
                int largestCoeff = largest.getCoefficient(j);
                int smallestCoeff = smallest.getCoefficient(i);

                if (largestCoeff == 0 && smallestCoeff == 0) {
                    newCoeff = 0;
                } else {
                    newCoeff = largestCoeff * smallestCoeff;
                }

                newExpo = i + j;
                multipliedValues =
                    (newExpo >= multipliedValues.length)
                        ? Arrays.copyOf(multipliedValues, newExpo + 1)
                        : multipliedValues;
                multipliedValues[newExpo] = newCoeff;
            }
        }

        return new DensePolynomial(multipliedValues);
    }

    /**
     * Returns a polynomial by multiplying the parameter with the current
     * instance.  Neither the current instance nor the parameter are modified.
     *
     * @param q the polynomial to multiply with <code>this</code>
     * @return <code>this * </code>q
     * @throws NullPointerException if q is null
     * @throws IllegalArgumentException if q is a sparse polynomial with any
     *     negative exponents
     */
    @Override
    public Polynomial multiply(Polynomial q) {
        // TODO Auto-generated method stub
        if (q == null)
            throw new NullPointerException("cannot multiply null polynomials");
        if (q.getClass() == SparsePolynomial.class) {
            Map<Integer, Integer> values = ((SparsePolynomial)q).getValues();
            for (int k : values.keySet())
                if (k < 0)
                    throw new IllegalArgumentException(
                        "Dense polynomials cannot be multiplied by sparse polynomials with negative degrees");
        }
        return multiplyDense(this, toDensePolynomial(q));
    }

    // This subtracts two dense polys only
    private DensePolynomial subtractDense(DensePolynomial p, DensePolynomial p2) {
        Polynomial smallest = p.degree() <= p2.degree() ? p : p2;
        Polynomial largest = p.degree() > p2.degree() ? p : p2;
        int[] addedValues = new int[largest.degree() + 1];

        for (int i = largest.degree(); i != -1; i--) {
            addedValues[i] =
                largest.getCoefficient(i) - smallest.getCoefficient(i);
        }

        return new DensePolynomial(addedValues);
    }

    /**
     * Returns a  polynomial by subtracting the parameter from the current
     * instance. Neither the current instance nor the parameter are modified.
     *
     * @param q the non-null polynomial to subtract from <code>this</code>
     * @return <code>this - </code>q
     * @throws NullPointerException if q is null
     * @throws IllegalArgumentException if q is a sparse polynomial with any
     *     negative exponents
     */
    @Override
    public Polynomial subtract(Polynomial q) {
        // TODO Auto-generated method stub
        if (q == null)
            throw new NullPointerException("cannot subtract null polynomials");
        if (q.getClass() == SparsePolynomial.class) {
            Map<Integer, Integer> values = ((SparsePolynomial)q).getValues();
            for (int k : values.keySet())
                if (k < 0)
                    throw new IllegalArgumentException(
                        "Dense polynomials cannot be subtracted with sparse polynomials with negative degrees");
        }
        return subtractDense(this, toDensePolynomial(q));
    }

    /**
     * Returns a polynomial by negating the current instance. The current
     * instance is not modified.
     *
     * @return -this
     */
    @Override
    public Polynomial minus() {
        // TODO Auto-generated method stub
        int[] v = new int[this.values.length];
        for (int i = 0; i < this.values.length; i++)
            v[i] = -1 * values[i];
        return new DensePolynomial(v);
    }

    /**
     * Checks if the class invariant holds for the current instance.
     * This method assures that the given string is not empty, is all integers, and does not contain empty input
     * like "0x".
     *
     * @return {@literal true} if the class invariant holds, and {@literal
     *     false} otherwise.
     */
    @Override
    public boolean wellFormed() {
        // TODO Auto-generated method stub
        if (inputPoly.equals(""))
            return false;
        if (inputPoly.contains("."))
            return false;
        if (inputPoly.contains(" 0x "))
            return false;
        return true;
    }

    public int[] getValues() { return values; }

    public void setValues(int[] values) { this.values = values; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DensePolynomial other = (DensePolynomial)obj;
        boolean initialTest = true;
        if (!Arrays.equals(values, other.values))
            initialTest = false;
        if(!initialTest)
            return this.toString().equals(other.toString());
        return true;
    }
}
