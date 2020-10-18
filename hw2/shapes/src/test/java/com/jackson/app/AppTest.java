package com.jackson.app;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import com.jackson.model.Cuboid;
import com.jackson.model.Rectangle;
import com.jackson.model.ThreeDPoint;
import com.jackson.model.TwoDPoint;

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

    @Test
    public void testRectMember() {
        ArrayList<TwoDPoint> points = new ArrayList<>();
        points.add(new TwoDPoint(1, 1));
        points.add(new TwoDPoint(0, 1));
        points.add(new TwoDPoint(0, 0));
        points.add(new TwoDPoint(1, 0));
        Rectangle r = new Rectangle(points);

        ArrayList<TwoDPoint> points2 = new ArrayList<>();
        points2.add(new TwoDPoint(2, 2));
        points2.add(new TwoDPoint(0, 2));
        points2.add(new TwoDPoint(0, 0));
        points2.add(new TwoDPoint(2, 0));
        Rectangle r2 = new Rectangle(points2);

        assertTrue(r.isMember(points2));
    }

    @Test
    public void testRectArea() {
        ArrayList<TwoDPoint> points = new ArrayList<>();
        points.add(new TwoDPoint(1, 1));
        points.add(new TwoDPoint(0, 1));
        points.add(new TwoDPoint(0, 0));
        points.add(new TwoDPoint(1, 0));
        Rectangle r = new Rectangle(points);

        ArrayList<TwoDPoint> points2 = new ArrayList<>();
        points2.add(new TwoDPoint(2, 2));
        points2.add(new TwoDPoint(0, 2));
        points2.add(new TwoDPoint(0, 0));
        points2.add(new TwoDPoint(2, 0));
        Rectangle r2 = new Rectangle(points2);

        assertTrue(r.area() != r2.area());
    }
}
