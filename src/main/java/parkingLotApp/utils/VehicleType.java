package main.java.parkingLotApp.utils;

public enum VehicleType {
    Car("Car"),
    ElectricCar("ElectricCar"),
    Motorcycle("Motorcycle");

    private final String name;

    VehicleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

