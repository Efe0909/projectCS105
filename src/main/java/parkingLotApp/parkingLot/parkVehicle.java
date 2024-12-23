package main.java.parkingLotApp.parkingLot;

import java.util.ArrayList;
import main.java.parkingLotApp.vehicles.*;

public class parkVehicle {
    private ArrayList<ParkingSpot> carSpots;
    private ArrayList<ParkingSpot> electricCarSpots;
    private ArrayList<ParkingSpot> motorcycleSpots;
    private ArrayList<VehicleSpotPair> parkedVehicles;

    public parkVehicle() {
        carSpots = new ArrayList<>();
        electricCarSpots = new ArrayList<>();
        motorcycleSpots = new ArrayList<>();
        parkedVehicles = new ArrayList<>();

        // Initialize parking spots
        for (int i = 1; i <= 200; i++) carSpots.add(new ParkingSpot("P" + String.format("%03d", i)));
        for (int i = 1; i <= 10; i++) electricCarSpots.add(new ParkingSpot("E" + String.format("%03d", i)));
        for (int i = 1; i <= 50; i++) motorcycleSpots.add(new ParkingSpot("M" + String.format("%03d", i)));
    }

    public void parkVehicle(String vehicleType, String licensePlate) {
        ArrayList<ParkingSpot> spots;
        Vehicle vehicle;

        switch (vehicleType) {
            case "Car":
                spots = carSpots;
                vehicle = new Car(licensePlate);
                break;
            case "Electric Car":
                spots = electricCarSpots;
                vehicle = new ElectricCar(licensePlate);
                break;
            case "Motorcycle":
                spots = motorcycleSpots;
                vehicle = new Motorcycle(licensePlate);
                break;
            default:
                throw new IllegalArgumentException("Invalid vehicle type");
        }

        ParkingSpot availableSpot = spots.stream()
                .filter(ParkingSpot::isAvailable)
                .findFirst()
                .orElse(null);

        if (availableSpot == null) {
            System.out.println("No available spots for " + vehicleType);
            return;
        }

        availableSpot.occupySpot();
        VehicleSpotPair pair = new VehicleSpotPair(vehicle, availableSpot);
        parkedVehicles.add(pair);

        System.out.println(vehicleType + " with plate " + licensePlate +
                " parked at " + availableSpot.getSpotId());
    }
}
