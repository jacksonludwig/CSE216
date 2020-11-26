package com.jackson.app;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class SparsePolynomial implements Polynomial {
    private Map<Integer, Integer> values;

    public SparsePolynomial(Map<Integer, Integer> values) {
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
        Map<Integer, Integer> m = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < p.getValues().length; i++) {
            m.put(i, p.getValues()[i]);
        }
        return new SparsePolynomial(m);
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

    @Override
    public int getCoefficient(int d) {
        // TODO Auto-generated method stub
        Object coeff = this.values.get(d);
        if (coeff == null)
            return 0;
        return (Integer)coeff;
    }

    @Override
    public boolean isZero() {
        // TODO Auto-generated method stub
        return false;
    }

    // Helper
    private Polynomial addSparse(SparsePolynomial p1, SparsePolynomial p2) {
        Map<Integer, Integer> smallest =
            p1.getValues().size() <= p2.getValues().size() ? p1.getValues()
                                                           : p2.getValues();
        Map<Integer, Integer> largest =
            p1.getValues().size() > p2.getValues().size() ? p1.getValues()
                                                          : p2.getValues();
        Map<Integer, Integer> added = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry<Integer, Integer> entry : largest.entrySet()) {
            int largestExpo = entry.getKey();
            int largestCoeff = entry.getValue();
            added.put(largestExpo, largestCoeff);
        }
        for (Map.Entry<Integer, Integer> entry : smallest.entrySet()) {
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

    @Override
    public Polynomial add(Polynomial q) {
        // TODO Auto-generated method stub
        if (this.getClass() == q.getClass()) {
            return addSparse(this, (SparsePolynomial)q);
        }

        return addSparse(this, toSparsePolynomial((DensePolynomial)q));
    }

    @Override
    public Polynomial multiply(Polynomial q) {
        // TODO Auto-generated method stub
        return null;
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

    @Override
    public Polynomial subtract(Polynomial q) {
        // TODO Auto-generated method stub
        if (this.getClass() == q.getClass()) {
            return subSparse(this, (SparsePolynomial)q);
        }

        return subSparse(this, toSparsePolynomial((DensePolynomial)q));
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
