package com.jackson.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// was todo: implement compareTo
public class Cuboid implements ThreeDShape {

    private final ThreeDPoint[] vertices = new ThreeDPoint[8];

    /**
     * Creates a cuboid out of the list of vertices. It is expected that the
     * vertices are provided in the order as shown in the figure given in the
     * homework document (from v0 to v7).
     * 
     * @param vertices the specified list of vertices in three-dimensional space.
     */
    public Cuboid(List<ThreeDPoint> vertices) {
        if (vertices.size() != 8)
            throw new IllegalArgumentException(
                    String.format("Invalid set of vertices specified for %s", this.getClass().getName()));
        int n = 0;
        for (ThreeDPoint p : vertices)
            this.vertices[n++] = p;
    }

    @Override
    public double volume() {
        // was todo

        // part 1b below
        // V = L * W * H
        double length = Math.abs(vertices[0].coordinates()[0] - vertices[1].coordinates()[0]);
        double height = Math.abs(vertices[1].coordinates()[1] - vertices[2].coordinates()[1]);
        double width = Math.abs(vertices[4].coordinates()[2] - vertices[3].coordinates()[2]);

        return length * width * height;
    }

    @Override
    public ThreeDPoint center() {
        // To get center of cuboid, add all X and divide by 8 to get center X. Repeat
        // for Y and Z.
        double totalX = 0;
        double totalY = 0;
        double totalZ = 0;
        for (ThreeDPoint point : vertices) {
            double[] coords = point.coordinates();
            totalX += coords[0];
            totalY += coords[1];
            totalZ += coords[2];
        }
        return new ThreeDPoint(totalX / 8, totalY / 8, totalZ / 8); // was todo
    }

    @Override
    public int compareTo(ThreeDShape shape) {
        return (int) (this.volume() - shape.volume());
    }

    // added by me
    private static double getRandomDoubleInRange() {
        double min = -100;
        double max = 100.0;
        return min + (max - min) * new Random().nextDouble();
    }

    // was todo
    public static Cuboid random() {
        List<ThreeDPoint> points = new ArrayList<>();

        double randLength = getRandomDoubleInRange();
        double randWidth = getRandomDoubleInRange();
        double randHeight = getRandomDoubleInRange();

        points.add(new ThreeDPoint(0, 0, 0));
        points.add(new ThreeDPoint(randLength, 0, 0));
        points.add(new ThreeDPoint(randLength, randWidth, 0));
        points.add(new ThreeDPoint(0, randWidth, 0));

        points.add(new ThreeDPoint(0, 0, 5));
        points.add(new ThreeDPoint(randLength, 0, randHeight));
        points.add(new ThreeDPoint(randLength, randWidth, randHeight));
        points.add(new ThreeDPoint(0, randWidth, randHeight));

        return new Cuboid(points);
    }

    public double surfaceArea() {
        double length = Math.abs(vertices[0].coordinates()[0] - vertices[1].coordinates()[0]);
        double height = Math.abs(vertices[1].coordinates()[1] - vertices[2].coordinates()[1]);
        double width = Math.abs(vertices[4].coordinates()[2] - vertices[3].coordinates()[2]);

        return (2 * length * width) + (2 * length * height) + (2 * height * width);
    }

}
