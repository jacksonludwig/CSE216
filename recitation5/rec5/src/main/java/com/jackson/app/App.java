package com.jackson.app;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // what kind of polymorphism do we see here? try to find the variable and
        // the different types it exhibits.
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(new Vehicle());
        vehicles.add(new Vehicle());
        List<Bike> bikes = new ArrayList<Bike>();
        bikes.add(new Bike());
        bikes.add(new Bike());
        List<Truck> trucks = new ArrayList<Truck>();
        trucks.add(new Truck());
        trucks.add(new Truck());
        // will the following code work? why or why not? try to understand what
        // works in the following lines and what doesn’t.
        // Mechanic mechanic = new Mechanic(); mechanic.serviceVehicles(vehicles);
        // mechanic.serviceVehicles(bikes); mechanic.serviceVehicles(cars); }
    }
}
