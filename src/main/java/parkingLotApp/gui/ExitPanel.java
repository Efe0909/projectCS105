package main.java.parkingLotApp.gui;

import main.java.parkingLotApp.vehicles.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitPanel extends JPanel {

    private final ParkingLotApp app;
    // done

    public ExitPanel(ParkingLotApp app) {

        this.app = app;
        setLayout(null);


        JLabel plateLabel = new JLabel("License Plate:");
        plateLabel.setBounds(50, 50, 100, 30);
        this.add(plateLabel);

        JTextField plateField = new JTextField();
        plateField.setBounds(150, 50, 150, 30);
        this.add(plateField);

        JButton findButton = new JButton("Find");
        findButton.setBounds(320, 50, 100, 30);
        this.add(findButton);

        JLabel statusLabel = new JLabel();
        statusLabel.setBounds(50, 100, 400, 30);
        this.add(statusLabel);

        findButton.addActionListener(e -> {
            String plate = plateField.getText();

            if (plate.isEmpty()) {
                statusLabel.setText("Please enter a valid license plate.");
                return;
            }

            try {
                app.autopark.quitVehicle(plate);

                statusLabel.setText("Vehicle exited successfully. Spot released.");
            } catch (RuntimeException ex) {
                statusLabel.setText(ex.getMessage());


            }
        });

        JButton returnButton = app.createReturnHomeButton();
        returnButton.setBounds(200, 300, 150, 30);
        this.add(returnButton);


    }




}
