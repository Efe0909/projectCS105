package test.java.parkingLotApp;

import main.java.parkingLotApp.utils.Pair;
import main.java.parkingLotApp.vehicles.*;
import main.java.parkingLotApp.parking.*;

public class test {
    public static void main(String[] args) {
        Autopark autopark = new Autopark();

        Motorcycle m1 = new Motorcycle("34 hjk 123");
        autopark.parkVehicle(m1);

        autopark.queryPlate("34 hjk 123");
        autopark.quitVehicle("34 hjk 123");
    }
}
