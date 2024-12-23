package main.java.parkingLotApp.parkingLot;

public class ParkingSpot {
    private String spotId;
    private boolean isOccupied;

    public ParkingSpot(String spotId) {
        this.spotId = spotId;
        this.isOccupied = false;
    }

    public void occupySpot() { this.isOccupied = true; }
    public void releaseSpot() { this.isOccupied = false; }
    public boolean isAvailable() { return !isOccupied; }
    public String getSpotId() { return spotId; }
}
