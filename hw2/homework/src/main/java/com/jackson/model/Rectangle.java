package com.jackson.model;

import java.util.List;

public class Rectangle extends Quadrilateral implements SymmetricTwoDShape {

    /**
     * The center of a rectangle is calculated to be the point of intersection of
     * its diagonals.
     *
     * @return the center of this rectangle.
     */
    @Override
    public Point center() {
        // was todo
        List<TwoDPoint> points = this.getPosition();
        TwoDPoint topLeft = points.get(1);
        TwoDPoint bottomRight = points.get(3);
        return new TwoDPoint((topLeft.getX() + bottomRight.getX()) / 2, ((topLeft.getY() + bottomRight.getY()) / 2));
    }

    @Override
    public boolean isMember(List<? extends Point> vertices) {
        return false; // TODO
    }

    @Override
    public double area() {
        return 0d; // TODO
    }
}
