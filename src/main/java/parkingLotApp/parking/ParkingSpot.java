package main.java.parkingLotApp.parking;

import main.java.parkingLotApp.vehicles.Vehicle;

import java.util.Objects;

public class ParkingSpot {
    private String spotId;
    private boolean isOccupied;
    Vehicle vehicle;


    public ParkingSpot(String spotId, String type) {
        this.spotId = spotId;
        this.isOccupied = false;
        this.vehicle = null;
    }

    public void occupySpot(Vehicle vehicle) {
        this.isOccupied = true;
        this.vehicle = vehicle;
    }

    public void releaseSpot() {
        this.isOccupied = false;
        this.vehicle = null;
    }

    public boolean isAvailable() {
        return !isOccupied;
    }

    public String getSpotId() {
        return spotId;
    }

    public String getVehiclePlate() {
        if (vehicle == null) {
            return null;
        }
        return vehicle.getLicensePlate();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        ParkingSpot that = (ParkingSpot) obj;
        return isOccupied == that.isOccupied &&
                Objects.equals(spotId, that.spotId) &&
                Objects.equals(vehicle, that.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spotId, isOccupied, vehicle);
    }
}
