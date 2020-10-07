package com.jackson.app;

import java.util.List;

class Mechanic {
    public void serviceVehicles(List<Vehicle> vehicles) {
        for (Vehicle v : vehicles)
            v.service();
    }
}
