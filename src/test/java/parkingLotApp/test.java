package test.java.parkingLotApp;

import main.java.parkingLotApp.gui.*;
import main.java.parkingLotApp.parking.*;
import main.java.parkingLotApp.vehicles.*;

import javax.swing.*;


public class test {
    public static void main(String[] args) {
        ParkingLotApp a = new ParkingLotApp();

        Motorcycle m1 = new Motorcycle("34 ABC 123");
        Vehicle m2 = new Motorcycle("09 NN 750");
        Vehicle c1 = new Car("30 abc 31");
        Vehicle e1 = new ElectricCar("32 adc 32");

        a.autopark.parkVehicle(m1);
        a.autopark.parkVehicle(m2);
        a.autopark.parkVehicle(c1);
        a.autopark.parkVehicle(e1);
    }
}
