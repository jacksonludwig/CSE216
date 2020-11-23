package com.jackson.app;

import java.util.Map;

public class SparsePolynomial implements Polynomial {
    private Map<Integer, Integer> values;

    public SparsePolynomial(Map<Integer, Integer> values) {
        this.values = values;
    }

    public Map<Integer, Integer> getValues() { return values; }

    public void setValues(Map<Integer, Integer> values) {
        this.values = values;
    }

	@Override
	public int degree() {
		// TODO Auto-generated method stub
		return 0;
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
