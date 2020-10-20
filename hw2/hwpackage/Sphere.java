package com.jackson.model;

import java.util.Random;

public class Sphere implements ThreeDShape {

    private ThreeDPoint center;
    private double radius;

    public Sphere(double centerx, double centery, double centerz, double radius) {
        this.center = new ThreeDPoint(centerx, centery, centerz);
        this.radius = radius;
    }

    @Override
    public int compareTo(ThreeDShape shape) {
        return (int) (this.volume() - shape.volume());
    }

    @Override
    public Point center() {
        return this.center;
    }

    @Override
    public double volume() {
        return (4 / 3) * Math.PI * Math.pow(radius, 3);
    }

    // added by me
    public double surfaceArea() {
        return 4 * Math.PI * (radius * radius);
    }

    // added by me
    private static double getRandomDoubleInRange() {
        double min = -100;
        double max = 100.0;
        return min + (max - min) * new Random().nextDouble();
    }

    // was todo
    public static Sphere random() {
        return new Sphere(getRandomDoubleInRange(), getRandomDoubleInRange(), getRandomDoubleInRange(),
                getRandomDoubleInRange());
    }

    public void setCenter(ThreeDPoint center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
