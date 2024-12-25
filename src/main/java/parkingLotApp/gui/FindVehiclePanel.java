package main.java.parkingLotApp.gui;

import main.java.parkingLotApp.utils.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindVehiclePanel extends JPanel {

    private final ParkingLotApp app;

    //done
    public FindVehiclePanel(ParkingLotApp app) {

        this.app = app;
        setLayout(null);


        JLabel plateLabel = new JLabel("License Plate Number:");
        plateLabel.setBounds(50, 50, 150, 30);
        this.add(plateLabel);

        JTextField plateField = new JTextField();
        plateField.setBounds(200, 50, 150, 30);
        this.add(plateField);

        JButton findButton = new JButton("Find");
        findButton.setBounds(400, 50, 100, 30);
        this.add(findButton);

        JLabel statusLabel = new JLabel();
        statusLabel.setBounds(50, 100, 400, 30);
        this.add(statusLabel);

        findButton.addActionListener(e -> {
            String plate = plateField.getText();

            // Action listener for the Find button
            findButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String plate = plateField.getText().trim();

                    if (plate.isEmpty()) {
                        statusLabel.setText("Please enter a valid license plate.");
                        return;
                    }

                    try {
                        Pair<String, Integer> spotInfo = app.autopark.queryPlate(plate);

                        // Format and display the result
                        statusLabel.setText("Vehicle found in " + spotInfo.getFirst() + " at spot index " + spotInfo.getSecond());
                    } catch (RuntimeException ex) {
                        statusLabel.setText(ex.getMessage());
                    }
                }
            });

        });

        JButton returnButton = app.createReturnHomeButton();
        returnButton.setBounds(200, 300, 150, 30);
        this.add(returnButton);
    }



}
