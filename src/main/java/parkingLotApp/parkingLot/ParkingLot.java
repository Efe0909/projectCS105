package main.java.parkingLotApp.parkingLot;

import java.util.ArrayList;
import main.java.parkingLotApp.vehicles.*;

public class ParkingLot {
    private ArrayList<ParkingSpot> carSpots;
    private ArrayList<ParkingSpot> electricCarSpots;
    private ArrayList<ParkingSpot> motorcycleSpots;
    private ArrayList<VehicleSpotPair> parkedVehicles;

    public ParkingLot() {
        carSpots = new ArrayList<>();
        electricCarSpots = new ArrayList<>();
        motorcycleSpots = new ArrayList<>();
        parkedVehicles = new ArrayList<>();

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

    public Object[][] getParkedVehiclesData() {
        Object[][] data = new Object[parkedVehicles.size()][3]; // 3 columns: Vehicle Type, License Plate, Spot

        for (int i = 0; i < parkedVehicles.size(); i++) {
            VehicleSpotPair pair = parkedVehicles.get(i);
            data[i][0] = pair.getVehicle().getClass().getSimpleName(); // Vehicle Type
            data[i][1] = pair.getVehicle().getLicensePlate();          // License Plate
            data[i][2] = pair.getParkingSpot().getSpotId();            // Spot ID
        }

        return data;
    }

    public String getAvailableSpotsInfo() {
        long availableCarSpots = carSpots.stream().filter(ParkingSpot::isAvailable).count();
        long availableElectricCarSpots = electricCarSpots.stream().filter(ParkingSpot::isAvailable).count();
        long availableMotorcycleSpots = motorcycleSpots.stream().filter(ParkingSpot::isAvailable).count();

        return "Available Spots:\n" +
                "Cars: " + availableCarSpots + "\n" +
                "Electric Cars: " + availableElectricCarSpots + "\n" +
                "Motorcycles: " + availableMotorcycleSpots;
    }

    public String findVehicleSpot(String licensePlate) {
        VehicleSpotPair pair = parkedVehicles.stream()
                .filter(p -> p.getVehicle().getLicensePlate().equals(licensePlate))
                .findFirst()
                .orElse(null);

        if (pair == null) {
            return null; // Vehicle not found
        }
        return pair.getParkingSpot().getSpotId();
    }

    public void exitVehicle(String licensePlate) {
        VehicleSpotPair pairToRemove = parkedVehicles.stream()
                .filter(pair -> pair.getVehicle().getLicensePlate().equals(licensePlate))
                .findFirst()
                .orElse(null);

        if (pairToRemove == null) {
            System.out.println("Vehicle with plate " + licensePlate + " not found.");
            return;
        }

        pairToRemove.getParkingSpot().releaseSpot();
        parkedVehicles.remove(pairToRemove);

        System.out.println("Vehicle with plate " + licensePlate + " has exited.");
    }
}
