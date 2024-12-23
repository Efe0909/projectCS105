package main.java.parkingLotApp.vehicles;

public abstract class Vehicle {
    protected String licensePlate;

    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() { return licensePlate; }
}
