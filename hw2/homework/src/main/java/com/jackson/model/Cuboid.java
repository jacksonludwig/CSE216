package com.jackson.model;

import java.util.List;

// TODO : a missing interface method must be implemented in this class to make it compile. This must be in terms of volume().
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
        double length = vertices[1].coordinates()[0] - vertices[0].coordinates()[0];
        double height = vertices[1].coordinates()[1] - vertices[2].coordinates()[1];
        double width = vertices[3].coordinates()[2] - vertices[4].coordinates()[2];
        return length * width * height;
    }

    @Override
    public ThreeDPoint center() {
        return null; // TODO
    }

}
