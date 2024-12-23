package main.java.parkingLotApp.parkingLot;

import main.java.parkingLotApp.vehicles.*;

public class VehicleSpotPair {
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;

    public VehicleSpotPair(Vehicle vehicle, ParkingSpot parkingSpot) {
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
    }

    public Vehicle getVehicle() { return vehicle; }
    public ParkingSpot getParkingSpot() { return parkingSpot; }
}
