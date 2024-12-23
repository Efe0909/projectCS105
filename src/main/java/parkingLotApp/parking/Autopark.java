package main.java.parkingLotApp.parking;

import main.java.parkingLotApp.vehicles.Vehicle;

public class Autopark {
    private final ParkingSpot[] carSpots;
    private final ParkingSpot[] electricCarSpots;
    private final ParkingSpot[] motorcycleSpots;

    public Autopark() {
        carSpots = new ParkingSpot[200];
        electricCarSpots = new ParkingSpot[10];
        motorcycleSpots = new ParkingSpot[50];

        initSpots(carSpots, 'P', "car");
        initSpots(electricCarSpots, 'E', "electric");
        initSpots(motorcycleSpots, 'M', "motorcycle");
    }

    public void ParkVehicle(Vehicle vehicle) {
        boolean found = false;
        switch (vehicle.getClass().getSimpleName()) {
            case "Car":
                for (ParkingSpot spot : carSpots) {
                    if (spot.isAvailable()) {
                        spot.occupySpot(vehicle);
                        found = true;
                    }
                    if (!found) {
                        throw new RuntimeException("couldn't find available parking spot");
                    }
                }
                break;
            case "ElectricCar":
                for (ParkingSpot spot : electricCarSpots) {
                    if (spot.isAvailable()) {
                        spot.occupySpot(vehicle);
                        found = true;
                    }
                    if (!found) {
                        throw new RuntimeException("couldn't find available parking spot");
                    }
                }
                break;
            case "Motorcycle":
                for (ParkingSpot spot: motorcycleSpots) {
                    if (spot.isAvailable()) {
                        spot.occupySpot(vehicle);
                        found = true;
                    }
                    if (!found) {
                        throw new RuntimeException("couldn't find available parking spot");
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid vehicle class " + vehicle.getClass().getSimpleName());
        }
    }

    private void initSpots(ParkingSpot[] spots, char prefix, String type) {
        for (int i = 0; i < spots.length; i++) {
            String id = prefix + String.format("%03d", i); // e.g., P001, E002, etc.
            spots[i] = new ParkingSpot(id, type); // Initialize each parking spot
        }
    }
}
