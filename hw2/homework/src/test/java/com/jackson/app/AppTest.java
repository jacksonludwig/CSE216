package com.jackson.app;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import com.jackson.model.Cuboid;
import com.jackson.model.ThreeDPoint;

import org.junit.Test;

public class AppTest {
    @Test
    public void testVolume() {
        ArrayList<ThreeDPoint> points = new ArrayList<>();
        points.add(new ThreeDPoint(1, 1, 0));
        points.add(new ThreeDPoint(0, 1, 0));
        points.add(new ThreeDPoint(0, 0, 0));
        points.add(new ThreeDPoint(1, 0, 0));
        points.add(new ThreeDPoint(1, 0, 1));
        points.add(new ThreeDPoint(1, 1, 1));
        points.add(new ThreeDPoint(0, 1, 1));
        points.add(new ThreeDPoint(0, 0, 1));
        Cuboid c = new Cuboid(points);
        ArrayList<ThreeDPoint> points2 = new ArrayList<>();
        points2.add(new ThreeDPoint(2, 2, 0));
        points2.add(new ThreeDPoint(0, 2, 0));
        points2.add(new ThreeDPoint(0, 0, 0));
        points2.add(new ThreeDPoint(2, 0, 0));
        points2.add(new ThreeDPoint(2, 0, 2));
        points2.add(new ThreeDPoint(2, 2, 2));
        points2.add(new ThreeDPoint(0, 2, 2));
        points2.add(new ThreeDPoint(0, 0, 2));
        Cuboid c2 = new Cuboid(points2);
        assertTrue(c.volume() < c2.volume());
    }
}
