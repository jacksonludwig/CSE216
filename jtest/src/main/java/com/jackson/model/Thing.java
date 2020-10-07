package com.jackson.model;

public class Thing {
    private double size;

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Thing(double size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Thing [size=" + size + "]";
    }
}

