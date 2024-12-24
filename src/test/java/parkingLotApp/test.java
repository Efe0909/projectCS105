package test.java.parkingLotApp;

import main.java.parkingLotApp.gui.*;
import main.java.parkingLotApp.parking.*;
import main.java.parkingLotApp.vehicles.*;


public class test {
    public static void main(String[] args) {
        new AppGui();

        Autopark a = new Autopark();

        Vehicle v = new Car("342345234");
        a.parkVehicle(v);


    }
}
