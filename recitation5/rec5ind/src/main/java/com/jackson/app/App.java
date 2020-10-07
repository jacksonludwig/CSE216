package com.jackson.app;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        ArrayList<String> thing = new ArrayList<>();
    }

    interface Shape {
        double getPosX();
    }

    class Circle implements Shape {
        private double center_x;

        public Circle(double center_x) {
            this.center_x = center_x;
        }

        @Override
        public double getPosX() {
            return center_x;
        }
    }

    class Square implements Shape {
        private double side;

        public Square(double side) {
            this.side = side;
        }

        @Override
        public double getPosX() {
            return side;
        }
    }

    class Vehicle {
        String name;

        public Vehicle(String name) {
            this.name = name;
        }

        public void start() {
            System.out.println("Starting vehicle " + this.name + ".");
        }

        public void drive() {
            System.out.println("Driving vehicle " + this.name + ".");
        }

        public void stop() {
            System.out.println("Stopping vehicle " + this.name + ".");
        }
    }

    class ElectricVehicle extends Vehicle {
        double charge = 0.0; // percentage of battery charged, between 0 and 100

        public ElectricVehicle(String name) {
            super(name);
            this.charge = 100; // new instances are fully charged
        }

        public void start() {
            System.out.println("Starting electric vehicle " + name + ".");
        }

        public void drive() {
            System.out.println("Driving electric vehicle " + name + ".");
        }

        public void stop() {
            System.out.println("Stopping electric vehicle " + name + ".");
        }

        public double getCharge() {
            return this.charge;
        }
    }
}
