
package main.java.parkingLotApp.parking;

import java.lang.reflect.Field;
import java.util.ArrayList;

import main.java.parkingLotApp.vehicles.Vehicle;
import main.java.parkingLotApp.utils.*;

/**
 * The Autopark class manages parking spots for different types of vehicles.
 * It provides methods to park vehicles, locate vehicles by their plates, remove vehicles, and retrieve a list of parked vehicles.
 */
public class Autopark {
    // Arrays to hold parking spots for different vehicle types
    private final ParkingSpot[] carSpots;
    private final ParkingSpot[] electricCarSpots;
    private final ParkingSpot[] motorcycleSpots;

    /**
     * Constructs an Autopark and initializes parking spots for different vehicle types.
     */
    public Autopark() {
        // Initialize parking spots for different vehicle types
        carSpots = new ParkingSpot[200];
        electricCarSpots = new ParkingSpot[10];
        motorcycleSpots = new ParkingSpot[50];

        // Initialize spots for each type of vehicle
        initSpots(carSpots, 'P', "Car");
        //System.out.println("Car Spots are created.");
        initSpots(electricCarSpots, 'E', "ElectricCar");
       // System.out.println("ElectricCar Spots are created.");
        initSpots(motorcycleSpots, 'M', "Motorcycle");
       // System.out.println("Motorcycle Spots are created.");
    }

    /**
     * Parks a vehicle in the first available spot of its type.
     * @param vehicle The vehicle to be parked.
     * @throws RuntimeException if no available parking spot is found for the vehicle type.
     */
    public void parkVehicle(Vehicle vehicle) {
        boolean found = false;
        System.out.println("Attempting to park vehicle: " + vehicle.getClass().getSimpleName());
        switch (vehicle.getClass().getSimpleName()) {
            case "Car":
                for (ParkingSpot spot : carSpots) {
                    if (spot.isAvailable()) {
                        spot.occupySpot(vehicle); // Assign the spot to the vehicle
                        System.out.println("Vehicle parked in Car Spot: " + spot.getId());
                        found = true;
                        break; // Exit the loop once a spot is assigned
                    }
                }
                if (!found) {
                    throw new RuntimeException("Couldn't find available parking spot for Car");
                }
                break;
            case "ElectricCar":
                for (ParkingSpot spot : electricCarSpots) {
                    if (spot.isAvailable()) {
                        spot.occupySpot(vehicle);
                        System.out.println("Vehicle parked in ElectricCar Spot: " + spot.getId());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    throw new RuntimeException("Couldn't find available parking spot for ElectricCar");
                }
                break;
            case "Motorcycle":
                for (ParkingSpot spot : motorcycleSpots) {
                    if (spot.isAvailable()) {
                        spot.occupySpot(vehicle);
                        System.out.println("Vehicle parked in Motorcycle Spot: " + spot.getId());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    throw new RuntimeException("Couldn't find available parking spot for Motorcycle");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid vehicle class " + vehicle.getClass().getSimpleName());
        }
    }

    /**
     * Finds the parking spot of a vehicle based on its license plate.
     * @param plate The license plate of the vehicle.
     * @return A Pair containing the array name and index of the parking spot.
     * @throws RuntimeException if the vehicle is not found.
     */
    public Pair<String, Integer> queryPlate(String plate) {
        System.out.println("Searching for vehicle with plate: " + plate);
        for (int i = 0; i < 10; i++) {
            ParkingSpot spot = electricCarSpots[i];
            if (!spot.isAvailable() && spot.getVehiclePlate().equals(plate)) {
                System.out.println("Vehicle found in ElectricCar Spot: " + spot.getId());
                return new Pair<>("electricCarSpots", i);
            }
        }

        for (int i = 0; i < 50; i++) {
            ParkingSpot spot = motorcycleSpots[i];
            if (!spot.isAvailable() && spot.getVehiclePlate().equals(plate)) {
                System.out.println("Vehicle found in Motorcycle Spot: " + spot.getId());
                return new Pair<>("motorcycleSpots", i);
            }
        }

        for (int i = 0; i < 200; i++) {
            ParkingSpot spot = carSpots[i];
            if (!spot.isAvailable() && spot.getVehiclePlate().equals(plate)) {
                System.out.println("Vehicle found in Car Spot: " + spot.getId());
                return new Pair<>("carSpots", i);
            }
        }

        throw new RuntimeException("Couldn't find specified vehicle plate: " + plate);
    }

    /**
     * Removes a vehicle from its assigned parking spot based on its license plate.
     * @param plate The license plate of the vehicle to be removed.
     */
    public void quitVehicle(String plate) {
        System.out.println("Attempting to remove vehicle with plate: " + plate);
        Pair<String, Integer> place = queryPlate(plate);

        try {
            Field field = Autopark.class.getDeclaredField(place.getFirst());  // accessing the attribute by name in runtime. (similar to 'getattr()' method in python)
            field.setAccessible(true);
            ParkingSpot[] spots = (ParkingSpot[]) field.get(this);
            int index = place.getSecond();
            System.out.println("Releasing spot: " + spots[index].getId());
            spots[index].releaseSpot();

        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Error accessing field: " + e.getMessage());
        }
    }

    /**
     * Retrieves a list of all currently occupied parking spots.
     * @return An ArrayList of occupied ParkingSpot objects.
     */
    public ArrayList<ParkingSpot> getParkedVehicles() {
        ArrayList<ParkingSpot> parkedVehicles = new ArrayList<>();
        for (ParkingSpot spot : carSpots) {
            if (!spot.isAvailable()) {
                parkedVehicles.add(spot);
            }
        }
        for (ParkingSpot spot : electricCarSpots) {
            if (!spot.isAvailable()) {
                parkedVehicles.add(spot);
            }
        }
        for (ParkingSpot spot : motorcycleSpots) {
            if (!spot.isAvailable()) {
                parkedVehicles.add(spot);
            }
        }

        return parkedVehicles;
    }

    /**
     * Retrieves a list of all currently available parking spots.
     * @return An ArrayList of available ParkingSpot places.
     */
    public ArrayList<String> getAvailableSpots(String type) {
        ArrayList<String> availableSpots = new ArrayList<>();
        for (ParkingSpot spot : carSpots) {
            if (spot.isAvailable()) {
                availableSpots.add(spot.getId());
            }
        }
        for (ParkingSpot spot : electricCarSpots) {
            if (spot.isAvailable()) {
                availableSpots.add(spot.getId());
            }
        }
        for (ParkingSpot spot : motorcycleSpots) {
            if (spot.isAvailable()) {
                availableSpots.add(spot.getId());
            }
        }
        return availableSpots;
    }

        /**
         * Utility function to Initialize parking spots for a given type of vehicle.
         * @param spots The array of parking spots to initialize.
         * @param prefix The prefix for the parking spot IDs.
         * @param type The type of vehicles for these parking spots.
         */
    private void initSpots(ParkingSpot[] spots, char prefix, String type) {
        // spot id's use zero indexing; e.g., P000 ,P199, E000, E009 etc.

        System.out.println("Initializing spots for: " + type);

        for (int i = 0; i < spots.length; i++) {
            String id = prefix + String.format("%03d", i);
            spots[i] = new ParkingSpot(id, type); // Initialize each parking spot
        }

        System.out.println(type + " spots initialized: " + spots.length);
    }
}
