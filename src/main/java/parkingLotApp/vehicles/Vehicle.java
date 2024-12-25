package main.java.parkingLotApp.vehicles;
import main.java.parkingLotApp.utils.*;
public abstract class Vehicle {
    private String licensePlate;

    public Vehicle(String licensePlate) {
        if (!Validators.isPlateValid(licensePlate)) {
            throw new IllegalArgumentException("Invalid license plate");
        }
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
}
