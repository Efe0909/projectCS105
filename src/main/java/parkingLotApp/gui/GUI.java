package main.java.parkingLotApp.gui;

import javax.swing.*;
import java.awt.*;
import main.java.parkingLotApp.parkingLot.*;

public class GUI {
    private JFrame frame;
    private ParkingLot parkingLot; // Use the correct class name

    public GUI() {
        parkingLot = new ParkingLot(); // Correct initialization
        frame = new JFrame("Parking Lot System");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showHomeScreen();
        frame.setVisible(true);
    }

    public void showHomeScreen() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridLayout(6, 1));
        frame.add(panel);

        JButton parkButton = new JButton("Park Vehicle");
        JButton exitButton = new JButton("Exit Vehicle");
        JButton listVehiclesButton = new JButton("List Parked Vehicles");
        JButton showSpotsButton = new JButton("Show Available Spots");
        JButton findVehicleButton = new JButton("Find Vehicle’s Parking Spot");

        parkButton.addActionListener(e -> showParkScreen());
        exitButton.addActionListener(e -> showExitScreen());
        listVehiclesButton.addActionListener(e -> showListVehiclesScreen());
        showSpotsButton.addActionListener(e -> showAvailableSpotsScreen());
        findVehicleButton.addActionListener(e -> showFindVehicleScreen());

        panel.add(parkButton);
        panel.add(exitButton);
        panel.add(listVehiclesButton);
        panel.add(showSpotsButton);
        panel.add(findVehicleButton);

        frame.revalidate();
        frame.repaint();
    }

    public void showParkScreen() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridLayout(4, 1));
        frame.add(panel);

        JLabel vehicleTypeLabel = new JLabel("Select Vehicle Type:");
        JComboBox<String> vehicleTypeDropdown = new JComboBox<>(new String[]{"Car", "Electric Car", "Motorcycle"});
        JLabel plateLabel = new JLabel("Enter License Plate:");
        JTextField plateField = new JTextField(10);
        JButton parkButton = new JButton("Park");

        parkButton.addActionListener(e -> {
            String vehicleType = (String) vehicleTypeDropdown.getSelectedItem();
            String licensePlate = plateField.getText();
            parkingLot.parkVehicle(vehicleType, licensePlate); // Use correct class method
            showHomeScreen();
        });

        panel.add(vehicleTypeLabel);
        panel.add(vehicleTypeDropdown);
        panel.add(plateLabel);
        panel.add(plateField);
        panel.add(parkButton);

        frame.revalidate();
        frame.repaint();
    }

    public void showExitScreen() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridLayout(3, 1));
        frame.add(panel);

        JLabel plateLabel = new JLabel("Enter License Plate:");
        JTextField plateField = new JTextField(10);
        JButton exitButton = new JButton("Exit");

        exitButton.addActionListener(e -> {
            String licensePlate = plateField.getText();
            parkingLot.exitVehicle(licensePlate); // Use correct class method
            showHomeScreen();
        });

        panel.add(plateLabel);
        panel.add(plateField);
        panel.add(exitButton);

        frame.revalidate();
        frame.repaint();
    }

    public void showListVehiclesScreen() {
        frame.getContentPane().removeAll();
        String[] columnNames = {"Vehicle Type", "License Plate", "Spot"};
        Object[][] data = parkingLot.getParkedVehiclesData(); // Ensure method exists

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();
    }

    public void showAvailableSpotsScreen() {
        String message = parkingLot.getAvailableSpotsInfo(); // Ensure method exists
        JOptionPane.showMessageDialog(frame, message);
    }

    public void showFindVehicleScreen() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new GridLayout(3, 1));
        frame.add(panel);

        JLabel plateLabel = new JLabel("Enter License Plate:");
        JTextField plateField = new JTextField(10);
        JButton findButton = new JButton("Find");

        findButton.addActionListener(e -> {
            String licensePlate = plateField.getText();
            String spotId = parkingLot.findVehicleSpot(licensePlate); // Ensure method exists
            JOptionPane.showMessageDialog(frame, spotId != null ? "Vehicle found at spot: " + spotId : "Vehicle not found.");
            showHomeScreen();
        });

        panel.add(plateLabel);
        panel.add(plateField);
        panel.add(findButton);

        frame.revalidate();
        frame.repaint();
    }
}
