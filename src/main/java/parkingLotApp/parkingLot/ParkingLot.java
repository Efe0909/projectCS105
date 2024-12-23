package main.java.parkingLotApp.parkingLot;

public class ParkingLot {
    private final ParkingSpot[] carSpots;
    private final ParkingSpot[] electricCarSpots;
    private final ParkingSpot[] motorcycleSpots;

    public ParkingLot() {
        carSpots = new ParkingSpot[200];
        electricCarSpots = new ParkingSpot[10];
        motorcycleSpots = new ParkingSpot[50];

        initSpots(carSpots, 'P', "car");
        initSpots(electricCarSpots, 'E', "electric");
        initSpots(motorcycleSpots, 'M', "motorcycle");
    }
    
    public query(String licansePlate) {

    }

    private void initSpots(ParkingSpot[] spots, char prefix, String type) {
        for (int i = 0; i < spots.length; i++) {
            String id = prefix + String.format("%03d", i); // e.g., P001, E002, etc.
            spots[i] = new ParkingSpot(id, type); // Initialize each parking spot
        }
    }
}
