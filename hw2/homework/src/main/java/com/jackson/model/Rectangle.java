package com.jackson.model;

import java.util.List;

public class Rectangle extends Quadrilateral implements SymmetricTwoDShape {
    public Rectangle(List<TwoDPoint> vertices) {
        super(vertices);
    }

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
        // was todo

        // make sure it only has 4 sides
        if (vertices.size() != 4)
            return false;

        // make sure the points are all unique
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i != j) {
                    if (vertices.get(i).coordinates()[0] == vertices.get(j).coordinates()[0]
                            && vertices.get(i).coordinates()[1] == vertices.get(j).coordinates()[1])
                        return false;
                }
            }
        }

        // make sure there are two sets of equal sides
        double[] sides = getSideLengths();
        if (sides[0] != sides[2] || sides[1] != sides[3])
            return false;

        return true;
    }

    @Override
    public double area() {
        // was todo
        List<TwoDPoint> points = this.getPosition();

        TwoDPoint topLeft = points.get(1);
        TwoDPoint topRight = points.get(0);
        double length = Math.abs(topRight.getX() - topLeft.getX());

        TwoDPoint bottomLeft = points.get(2);
        double width = Math.abs(topLeft.getY() - bottomLeft.getY());

        return length * width;
    }

    // added by me
    /**
     * Get the smallest x-value in the list of rectangle vertices.
     */
    public double getSmallestXCoord() {
        List<TwoDPoint> vertices = this.getPosition();
        double smallest = vertices.get(0).getX();

        for (TwoDPoint p : vertices) {
            if (p.getX() < smallest)
                smallest = p.getX();
        }

        return smallest;
    }
}
