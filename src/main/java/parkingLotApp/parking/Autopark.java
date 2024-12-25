
package main.java.parkingLotApp.parking;

import java.lang.reflect.Field;
import java.util.ArrayList;

import main.java.parkingLotApp.vehicles.Vehicle;
import main.java.parkingLotApp.utils.*;

/**
 * The Autopark class manages parking spots for different types of vehicles.
 * It provides methods to park vehicles, locate vehicles by their license plates, remove vehicles,
 * retrieve a list of parked vehicles, and check available parking spots.
 */
public class Autopark {
    // Arrays to hold parking spots for different vehicle types
    private final ParkingSpot[] carSpots;
    private final ParkingSpot[] electricCarSpots;
    private final ParkingSpot[] motorcycleSpots;

    /**
     * Constructs an Autopark and initializes parking spots for different vehicle types.
     * This includes 200 spots for cars, 10 spots for electric cars, and 50 spots for motorcycles.
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
     * @throws RuntimeException if the vehicle is not found in the parking lot.
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
     * Frees up the parking spot for future use.
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
     * Retrieves a list of all currently occupied parking spots in the parking lot.
     * @return An ArrayList of ParkingSpot objects representing occupied spots.
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
     * Retrieves a sorted list of occupied parking spots based on a specified criterion.
     * Supports sorting by vehicle type, license plate, or spot number.
     * @param sortBy The criterion to sort the occupied parking spots.
     * @return An ArrayList of sorted ParkingSpot objects.
     * @throws RuntimeException if an invalid sort type is provided or if plates are duplicated.
     */
     public ArrayList<ParkingSpot> getParkedVehicles(SortType sortBy) {
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

         switch (sortBy) {
             case ByType:
                 return parkedVehicles;

             case ByPlate:
                 for (int i = 0; i < parkedVehicles.size() - 1; i++) {
                     for (int j = 0; j < parkedVehicles.size() - i - 1; j++) {
                         // Get and split plates for comparison
                         String[] plate1 = parkedVehicles.get(j).getVehiclePlate().trim().split(" ");
                         String[] plate2 = parkedVehicles.get(j + 1).getVehiclePlate().trim().split(" ");

                         int cityComp = plate1[0].compareTo(plate2[0]);
                         int letterComp = plate1[1].compareTo(plate2[1]);
                         int numberComp = plate1[2].compareTo(plate2[2]);


                         if (cityComp > 0 ||
                            (cityComp == 0 && letterComp > 0) ||
                            (cityComp == 0 && letterComp == 0 && numberComp > 0))
                         {
                             // Swap parkedVehicles[j] and parkedVehicles[j + 1]
                             ParkingSpot temp = parkedVehicles.get(j);
                             parkedVehicles.set(j, parkedVehicles.get(j + 1));
                             parkedVehicles.set(j + 1, temp);

                         } else if (cityComp == 0 && letterComp == 0 && numberComp == 0) {
                             throw new RuntimeException("Plates should not be the same");
                         }
                     }
                 }
                 return parkedVehicles;

             case BySpot:
                 for (int i = 0; i < parkedVehicles.size(); i++) {
                     for (int j = i + 1; j < parkedVehicles.size(); j++) {
                        if (parkedVehicles.get(j).getVehiclePlate().compareTo(
                                parkedVehicles.get(j+1).getVehiclePlate()) > 0) {
                            // Swap
                            ParkingSpot temp = parkedVehicles.get(j);
                            parkedVehicles.set(j, parkedVehicles.get(j + 1));
                            parkedVehicles.set(j + 1, temp);

                        }
                     }
                 }
                 return parkedVehicles;

             default:
                 throw new RuntimeException("Couldn't find specified sort type.");
         }
     }

    /**
     * Retrieves a list of all currently available parking spots for a specified vehicle type.
     * @param vehicleType The type of vehicle (Car, ElectricCar, Motorcycle).
     * @return An ArrayList of IDs for available parking spots of the specified type.
     * @throws IllegalArgumentException if the provided vehicle type is invalid.
     */
    public ArrayList<String> getAvailableSpots(VehicleType vehicleType) {
        ArrayList<String> availableSpots = new ArrayList<>();
        switch (vehicleType) {
            case Car:
                for (ParkingSpot spot : carSpots) {
                    if (spot.isAvailable()) {
                        availableSpots.add(spot.getId());
                    }
                }
                break;
            case ElectricCar:
                for (ParkingSpot spot : electricCarSpots) {
                    if (spot.isAvailable()) {
                        availableSpots.add(spot.getId());
                    }
                }
                break;
            case Motorcycle:
                for (ParkingSpot spot : motorcycleSpots) {
                    if (spot.isAvailable()) {
                        availableSpots.add(spot.getId());
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid vehicle type " + vehicleType.getName());
        }
        return availableSpots;
    }

    /**
     * Utility function to initialize parking spots for a given type of vehicle.
     * Each spot is assigned a unique ID based on the vehicle type and spot index.
     * @param spots The array of parking spots to initialize.
     * @param prefix The prefix for the parking spot IDs (e.g., 'P' for cars, 'E' for electric cars).
     * @param type The type of vehicles assigned to these parking spots.
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


