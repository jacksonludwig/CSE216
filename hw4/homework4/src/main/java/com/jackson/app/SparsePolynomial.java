package com.jackson.app;

import java.util.Map;

public class SparsePolynomial {
    private Map<Integer, Integer> values;

    public SparsePolynomial(Map<Integer, Integer> values) {
        values = this.values;
    }

    public Map<Integer, Integer> getValues() { return values; }

    public void setValues(Map<Integer, Integer> values) {
        this.values = values;
    }
}
