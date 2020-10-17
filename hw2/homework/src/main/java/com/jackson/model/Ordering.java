package com.jackson.model;

import java.util.*;

public class Ordering {

    static class XLocationComparator implements Comparator<TwoDShape> {
        @Override
        public int compare(TwoDShape o1, TwoDShape o2) {
            // was todo
            double o1xlocation = 0;
            double o2xlocation = 0;

            o1xlocation = ((Rectangle) o1).center().coordinates()[0];
            o2xlocation = ((Rectangle) o2).center().coordinates()[0];

            return (int) (o1xlocation - o2xlocation);
        }
    }

    static class AreaComparator implements Comparator<SymmetricTwoDShape> {
        @Override
        public int compare(SymmetricTwoDShape o1, SymmetricTwoDShape o2) {
            // was todo

            return (int) (o1.area() - o2.area());
        }
    }

    static class SurfaceAreaComparator implements Comparator<ThreeDShape> {
        // was todo

        @Override
        public int compare(ThreeDShape o1, ThreeDShape o2) {
            double o1SurfaceArea = 0;
            double o2SurfaceArea = 0;

            if (o1 instanceof Cuboid)
                o1SurfaceArea = ((Cuboid) o1).surfaceArea();
            else
                o1SurfaceArea = ((Sphere) o1).surfaceArea();

            if (o2 instanceof Cuboid)
                o2SurfaceArea = ((Cuboid) o2).surfaceArea();
            else
                o2SurfaceArea = ((Sphere) o2).surfaceArea();

            return (int) (o1SurfaceArea - o2SurfaceArea);
        }
    }

    // TODO: there's a lot wrong with this method. correct it so that it can work
    // properly with generics.
    static void copy(List<TwoDShape> source, List<TwoDShape> destination) {
        destination.addAll(source);
    }

    public static void main(String[] args) {
        List<TwoDShape> shapes = new ArrayList<>();
        List<SymmetricTwoDShape> symmetricshapes = new ArrayList<>();
        List<ThreeDShape> threedshapes = new ArrayList<>();

        /*
         * uncomment the following block and fill in the "..." constructors to create
         * actual instances. If your implementations are correct, then the code should
         * compile and yield the expected results of the various shapes being ordered by
         * their smallest x-coordinate, area, volume, surface area, etc.
         */

        ArrayList<TwoDPoint> points = new ArrayList<>();
        points.add(new TwoDPoint(1, 1));
        points.add(new TwoDPoint(0, 1));
        points.add(new TwoDPoint(0, 0));
        points.add(new TwoDPoint(1, 0));
        Rectangle r = new Rectangle(points);

        ArrayList<TwoDPoint> points2 = new ArrayList<>();
        points.add(new TwoDPoint(2, 2));
        points.add(new TwoDPoint(0, 2));
        points.add(new TwoDPoint(0, 0));
        points.add(new TwoDPoint(2, 0));
        Square s = new Square(points2);

        Circle c = new Circle(0d, 0d, 2d);

        symmetricshapes.add(r);
        symmetricshapes.add(s);
        symmetricshapes.add(c);
        /*
         * copy(symmetricshapes, shapes); // note-1 // shapes.add(new Quadrilateral(new
         * ArrayList<>()));
         */

        // sorting 2d shapes according to various criteria
        shapes.sort(new XLocationComparator());
        symmetricshapes.sort(new XLocationComparator());
        symmetricshapes.sort(new AreaComparator());

        // sorting 3d shapes according to various criteria
        Collections.sort(threedshapes);
        threedshapes.sort(new SurfaceAreaComparator());

        for (Object o : symmetricshapes) {
            System.out.println(o.getClass().getName());
        }

        /*
         * if your changes to copy() are correct, uncommenting the following block will
         * also work as expected note that copy() should work for the line commented
         * with 'note-1' while at the same time also working with the lines commented
         * with 'note-2' and 'note-3'.
         */

        /*
         * List<Number> numbers = new ArrayList<>(); List<Double> doubles = new
         * ArrayList<>(); Set<Square> squares = new HashSet<>(); Set<Quadrilateral>
         * quads = new LinkedHashSet<>();
         * 
         * copy(doubles, numbers); // note-2 // copy(squares, quads); // note-3 //
         */
    }
}
