package main.java.parkingLotApp.gui;


import main.java.parkingLotApp.vehicles.*;
import javax.swing.*;

public class ParkPanel extends JPanel {

    private final ParkingLotApp app;

    // done
    public ParkPanel(ParkingLotApp app) {
        this.app = app;

        setLayout(null);

        JLabel vehicleTypeLabel = new JLabel("Vehicle Type:");
        vehicleTypeLabel.setBounds(50, 50, 100, 30);
        this.add(vehicleTypeLabel);

        JComboBox<String> vehicleTypeComboBox = new JComboBox<>(new String[]{"Car", "Electric Car", "Motorcycle"});
        vehicleTypeComboBox.setBounds(150, 50, 150, 30);
        this.add(vehicleTypeComboBox);

        JLabel plateLabel = new JLabel("License Plate:");
        plateLabel.setBounds(50, 100, 100, 30);
        this.add(plateLabel);

        JTextField plateField = new JTextField();
        plateField.setBounds(150, 100, 150, 30);
        this.add(plateField);

        JButton checkButton = new JButton("Check Availability");
        checkButton.setBounds(320, 50, 200, 30);
        JLabel statusLabel = new JLabel();
        statusLabel.setBounds(50, 150, 400, 30);
        this.add(statusLabel);
        this.add(checkButton);

        checkButton.addActionListener(e -> {
            String type = (String) vehicleTypeComboBox.getSelectedItem();
            String plate = plateField.getText();

            if (plate.isEmpty()) {
                statusLabel.setText("Please enter a valid license plate.");
                return;
            }

             try {
                Vehicle vehicle;
                switch (type) {
                    case "Car":
                        vehicle = new Car(plate);
                        break;
                    case "Electric Car":
                        vehicle = new ElectricCar(plate);
                        break;
                    case "Motorcycle":
                        vehicle = new Motorcycle(plate);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid vehicle type selected.");
                }

                app.autopark.parkVehicle(vehicle);
                statusLabel.setText("Vehicle parked successfully at an available spot.");
            } catch (RuntimeException ex) {
                statusLabel.setText(ex.getMessage());
            }

        });

        JButton returnButton = app.createReturnHomeButton();
        returnButton.setBounds(200, 300, 150, 30);
        this.add(returnButton);
    }
}
