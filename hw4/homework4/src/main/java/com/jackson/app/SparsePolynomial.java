package com.jackson.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class SparsePolynomial implements Polynomial {
    private Map<Integer, Integer> values;

    private SparsePolynomial(Map<Integer, Integer> values) {
        this.values = values;
    }

    public SparsePolynomial(String poly) {
        this.values = tokenizeEquation(poly);
    }

    public Map<Integer, Integer> getValues() { return values; }

    public void setValues(Map<Integer, Integer> values) {
        this.values = values;
    }

    // sort by reverse exponent, AND account for negatives
    // private Comparator<Integer> generateComparator() {
    //     Comparator<Integer> c = new Comparator<Integer>() {
    //         @Override
    //         public int compare(Integer arg0, Integer arg1) {
    //             return Integer.compare(Math.abs(arg0), Math.abs(arg1));
    //         }
    //     };
    //     return c;
    // }

    // TODO use in arithmetic methods
    private SparsePolynomial toSparsePolynomial(DensePolynomial p) {
        return new SparsePolynomial(p.toString());
    }

    // TODO finish
    private Map<Integer, Integer> tokenizeEquation(String poly) {
        String[] p = poly.replaceAll("\\s", "").split("[+]");

        Map<Integer, Integer> m = new TreeMap<>(Collections.reverseOrder());

        for (int i = 0; i < p.length; i++) {
            int xLoc = p[i].indexOf('x');
            int carrot_loc = p[i].indexOf('^');
            String coeff;
            String degree;
            if (xLoc != -1) {
                coeff = p[i].substring(0, xLoc);
                if (coeff.equals(""))
                    coeff = "1";
                if (coeff.equals("-"))
                    coeff = "-1";
                if (carrot_loc != -1)
                    degree = p[i].substring(carrot_loc + 1);
                else
                    degree = "1";
            } else {
                coeff = p[i];
                degree = "0";
            }
            m.put(Integer.parseInt(degree), Integer.parseInt(coeff));
        }
        return m;
    }

    @Override
    public String toString() {
        String poly = "";

        boolean allZero = true;
        for (Map.Entry<Integer, Integer> entry : this.values.entrySet()) {
            if (entry.getValue() != 0)
                allZero = false;
        }
        if (allZero)
            return "0";

        for (Map.Entry<Integer, Integer> entry : this.values.entrySet()) {
            int expo = entry.getKey();
            String coeff = String.valueOf(entry.getValue());
            if (!coeff.equals("0")) {
                switch (expo) {
                case 0:
                    poly += coeff;
                    poly += " + ";
                    break;
                case 1:
                    if (coeff.equals("1"))
                        coeff = "";
                    if (coeff.equals("-1"))
                        coeff = "-";
                    poly += coeff + "x";
                    poly += " + ";
                    break;
                default:
                    if (coeff.equals("1"))
                        coeff = "";
                    if (coeff.equals("-1"))
                        coeff = "-";
                    poly += coeff + "x^" + expo;
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
     *
     * @return the largest exponent with a non-zero coefficient.  If all terms
     *     have zero exponents, it returns 0.
     */
    @Override
    public int degree() {
        // TODO Auto-generated method stub
        boolean first = true;
        int highestKey = 0;
        for (Map.Entry<Integer, Integer> e : this.values.entrySet()) {
            int expo = e.getKey();
            int coeff = e.getValue();
            if (first) {
                highestKey = expo;
                first = false;
            } else if (coeff != 0 && expo > highestKey)
                highestKey = expo;
        }
        return highestKey;
    }

    /**
     * Returns the coefficient corresponding to the given exponent.  Returns 0
     * if there is no term with that exponent in the polynomial.
     *
     * @param d the exponent whose coefficient is returned.
     * @return the coefficient of the term of whose exponent is d.
     */
    @Override
    public int getCoefficient(int d) {
        // TODO Auto-generated method stub
        Object coeff = this.values.get(d);
        if (coeff == null)
            return 0;
        return (Integer)coeff;
    }

    /**
     * @return true if the polynomial represents the zero constant
     */
    @Override
    public boolean isZero() {
        // TODO Auto-generated method stub
        for (Map.Entry<Integer, Integer> e : this.values.entrySet()) {
            if (e.getValue() != 0)
                return false;
        }
        return true;
    }

    // Helper
    private Polynomial addSparse(SparsePolynomial p1, SparsePolynomial p2) {
        Map<Integer, Integer> m1 = p1.getValues();
        Map<Integer, Integer> m2 = p2.getValues();

        Map<Integer, Integer> added = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry<Integer, Integer> entry : m2.entrySet()) {
            int largestExpo = entry.getKey();
            int largestCoeff = entry.getValue();
            added.put(largestExpo, largestCoeff);
        }
        for (Map.Entry<Integer, Integer> entry : m1.entrySet()) {
            int smallestExpo = entry.getKey();
            int smallestCoeff = entry.getValue();
            if (added.get(smallestExpo) != null)
                added.put(smallestExpo,
                          smallestCoeff + added.get(smallestExpo));
            else
                added.put(smallestExpo, smallestCoeff);
        }
        return new SparsePolynomial(added);
    }

    /**
     * Returns a polynomial by adding the parameter to the current instance.
     * Neither the current instance nor the parameter are modified.
     *
     * @param q the non-null polynomial to add to <code>this</code>
     * @return <code>this + </code>q
     * @throws NullPointerException if q is null
     */
    @Override
    public Polynomial add(Polynomial q) {
        // TODO Auto-generated method stub
        if (q == null)
            throw new NullPointerException("cannot add null polynomials");
        if (this.getClass() == q.getClass()) {
            return addSparse(this, (SparsePolynomial)q);
        }

        return addSparse(this, toSparsePolynomial((DensePolynomial)q));
    }

    // Helper
    private Polynomial multSparse(SparsePolynomial p1, SparsePolynomial p2) {
        Map<Integer, Integer> m1 = p1.getValues();
        Map<Integer, Integer> m2 = p2.getValues();

        ArrayList<Polynomial> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : m1.entrySet()) {
            Map<Integer, Integer> added =
                new TreeMap<>(Collections.reverseOrder());
            int expo = entry.getKey();
            int coeff = entry.getValue();
            for (Map.Entry<Integer, Integer> entry2 : m2.entrySet()) {
                int expo2 = entry2.getKey();
                int coeff2 = entry2.getValue();
                added.put(expo + expo2, coeff * coeff2);
            }
            list.add(new SparsePolynomial(added));
        }
        Polynomial sum = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            sum = sum.add(list.get(i));
        }
        System.out.println(list);
        return sum;
    }

    /**
     * Returns a polynomial by multiplying the parameter with the current
     * instance.  Neither the current instance nor the parameter are modified.
     *
     * @param q the polynomial to multiply with <code>this</code>
     * @return <code>this * </code>q
     * @throws NullPointerException if q is null
     */
    @Override
    public Polynomial multiply(Polynomial q) {
        // TODO Auto-generated method stub
        if (q == null)
            throw new NullPointerException("cannot multiply null polynomials");
        if (this.getClass() == q.getClass()) {
            return multSparse(this, (SparsePolynomial)q);
        }

        return multSparse(this, toSparsePolynomial((DensePolynomial)q));
    }

    // Helper
    private Polynomial subSparse(SparsePolynomial p1, SparsePolynomial p2) {
        Map<Integer, Integer> m1 = p1.getValues();
        Map<Integer, Integer> m2 = p2.getValues();

        Map<Integer, Integer> added = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry<Integer, Integer> entry : m2.entrySet()) {
            int expo = entry.getKey();
            int coeff = entry.getValue();
            added.put(expo, coeff);
        }
        for (Map.Entry<Integer, Integer> entry : m1.entrySet()) {
            int expo = entry.getKey();
            int coeff = entry.getValue();
            if (added.get(expo) != null)
                added.put(expo, coeff - added.get(expo));
            else
                added.put(expo, -1 * coeff);
        }
        return new SparsePolynomial(added);
    }

    /**
     * Returns a  polynomial by subtracting the parameter from the current
     * instance. Neither the current instance nor the parameter are modified.
     *
     * @param q the non-null polynomial to subtract from <code>this</code>
     * @return <code>this - </code>q
     * @throws NullPointerException if q is null
     */
    @Override
    public Polynomial subtract(Polynomial q) {
        // TODO Auto-generated method stub
        if (q == null)
            throw new NullPointerException("cannot subtract null polynomials");
        if (this.getClass() == q.getClass()) {
            return subSparse(this, (SparsePolynomial)q);
        }

        return subSparse(this, toSparsePolynomial((DensePolynomial)q));
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
        Map<Integer, Integer> minus = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry<Integer, Integer> entry : this.values.entrySet()) {
            int expo = entry.getKey();
            int coeff = entry.getValue();
            minus.put(expo, -1 * coeff);
        }
        return new SparsePolynomial(minus);
    }

    /**
     * Checks if the class invariant holds for the current instance.
     *
     * @return {@literal true} if the class invariant holds, and {@literal
     *     false} otherwise.
     */
    @Override
    public boolean wellFormed() {
        // TODO Auto-generated method stub
        return false;
    }
}
