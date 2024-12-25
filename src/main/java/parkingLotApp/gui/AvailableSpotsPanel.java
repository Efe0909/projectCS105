package main.java.parkingLotApp.gui;

import main.java.parkingLotApp.utils.VehicleType;

import javax.swing.*;

public class AvailableSpotsPanel extends JPanel {
    private final ParkingLotApp app;

    private final JLabel carCountLabel;
    private final JLabel electricCarCountLabel;
    private final JLabel motorcycleCountLabel;

    public AvailableSpotsPanel(ParkingLotApp app) {

        this.app = app;
        setLayout(null);

        JLabel carLabel = new JLabel("Available Car Spots:");
        carLabel.setBounds(50, 50, 200, 30);
        this.add(carLabel);

        carCountLabel = new JLabel();
        carCountLabel.setBounds(250, 50, 100, 30);
        this.add(carCountLabel);

        JLabel electricCarLabel = new JLabel("Available Electric Car Spots:");
        electricCarLabel.setBounds(50, 100, 200, 30);
        this.add(electricCarLabel);

        electricCarCountLabel = new JLabel();
        electricCarCountLabel.setBounds(250, 100, 100, 30);
        this.add(electricCarCountLabel);

        JLabel motorcycleLabel = new JLabel("Available Motorcycle Spots:");
        motorcycleLabel.setBounds(50, 150, 200, 30);
        this.add(motorcycleLabel);

        motorcycleCountLabel = new JLabel();
        motorcycleCountLabel.setBounds(250, 150, 100, 30);
        this.add(motorcycleCountLabel);

        JButton returnButton = app.createReturnHomeButton();
        returnButton.setBounds(200, 300, 150, 30);
        this.add(returnButton);

    }
    public void updateState() {
        carCountLabel.setText(String.valueOf(app.autopark.getAvailableSpots(VehicleType.Car).size()));
        electricCarCountLabel.setText(String.valueOf(app.autopark.getAvailableSpots(VehicleType.ElectricCar).size()));
        motorcycleCountLabel.setText(String.valueOf(app.autopark.getAvailableSpots(VehicleType.Motorcycle).size()));
    }

}
