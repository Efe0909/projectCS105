package main.java.parkingLotApp.parking;

import main.java.parkingLotApp.vehicles.Vehicle;

import java.util.Objects;

/**
 * Represents a parking spot in the parking lot.
 * Each spot has a unique ID and can be occupied by a single vehicle.
 */
public class ParkingSpot {
    private String spotId; // Unique identifier for the parking spot (e.g., "P001")
    private boolean isOccupied; // Indicates if the parking spot is currently occupied
    Vehicle vehicle; // Reference to the vehicle parked in this spot (null if unoccupied)

    /**
     * Constructor to initialize a parking spot.
     *
     * @param spotId Unique identifier for the parking spot.
     * @param type   Type of vehicle the spot is designated for (e.g., "Car", "Motorcycle").
     */
    public ParkingSpot(String spotId, String type) {
        this.spotId = spotId; // Unique identifier for the parking spot
        this.isOccupied = false; // Spot starts as unoccupied
        this.vehicle = null; // No vehicle is parked in the spot initially
    }

    /**
     * Marks the parking spot as occupied by a given vehicle.
     *
     * @param vehicle The vehicle to occupy the spot.
     */
    public void occupySpot(Vehicle vehicle) {
        this.isOccupied = true; // Mark the spot as occupied
        this.vehicle = vehicle; // Associate the vehicle with this spot
    }

    /**
     * Releases the parking spot, making it available for other vehicles.
     */
    public void releaseSpot() {
        this.isOccupied = false; // Mark the spot as unoccupied
        this.vehicle = null; // Remove the reference to the parked vehicle
    }

    /**
     * Checks if the parking spot is available for a new vehicle.
     *
     * @return true if the spot is not occupied; false otherwise.
     */
    public boolean isAvailable() {
        return !isOccupied; // A spot is available if it is not occupied
    }

    /**
     * Gets the unique ID of the parking spot.
     *
     * @return The spot ID as a string.
     */
    public String getId() {
        return spotId; // Return the unique identifier for this parking spot
    }

    /**
     * Gets the license plate of the vehicle parked in this spot.
     *
     * @return The license plate of the vehicle, or null if no vehicle is parked.
     */
    public String getVehiclePlate() {
        if (vehicle == null) { // If no vehicle is parked, return null
            return null;
        }
        return vehicle.getLicensePlate(); // Return the license plate of the parked vehicle
    }
}
