package com.jackson.model;

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

    public double surfaceArea() {
        return 4 * Math.PI * (radius * radius);
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
