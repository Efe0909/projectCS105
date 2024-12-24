package main.java.parkingLotApp.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import main.java.parkingLotApp.parking.*;
import main.java.parkingLotApp.utils.Pair;
import main.java.parkingLotApp.vehicles.*;

public class AppGui extends JFrame {
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 600;

    private final Autopark autopark;
    private JPanel homePanel;

    public AppGui() {
        autopark = new Autopark();
        setTitle("Parking Lot Application");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initHomeScreen();
        setVisible(true);
    }

    private void initHomeScreen() {
        homePanel = new JPanel();
        homePanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        homePanel.setBackground(Color.WHITE);
        homePanel.setLayout(null);

        createMainButtons();
        add(homePanel);
    }

    private void createMainButtons() {
        createButton("Park", 50, 50, 200, 100, this::createParkFrame);
        createButton("Exit", 345, 50, 200, 100, this::createExitFrame);
        createButton("List Parked Vehicles", 20, 250, 180, 70, this::showParkedVehicles);
        createButton("Show Available Spots", 210, 250, 180, 70, this::showAvailableSpots);
        createButton("Find a Vehicle's Parking Spot", 400, 250, 180, 70, this::createQueryFrame);
    }

    private void createButton(String text, int x, int y, int width, int height, Runnable action) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(new Color(31, 161, 231));
        button.setForeground(Color.WHITE);
        button.addActionListener(e -> action.run());
        homePanel.add(button);
    }

    private void createParkFrame() {
        JFrame parkFrame = new JFrame("Park Vehicle");
        parkFrame.setSize(600, 400);
        parkFrame.setLayout(null);
        parkFrame.setLocationRelativeTo(null);

        JLabel vehicleTypeLabel = new JLabel("Vehicle Type:");
        vehicleTypeLabel.setBounds(80, 40, 100, 30);

        String[] vehicles = {"Car", "Electric Car", "Motorcycle"};
        JComboBox<String> vehicleTypeComboBox = new JComboBox<>(vehicles);
        vehicleTypeComboBox.setBounds(200, 40, 150, 30);

        JButton checkButton = new JButton("Check Availability");
        checkButton.setBounds(380, 40, 150, 30);

        JLabel spotLabel = new JLabel("Spot Number:");
        spotLabel.setBounds(80, 90, 100, 30);

        JLabel availableSpotLabel = new JLabel();
        availableSpotLabel.setBounds(200, 90, 150, 30);

        JLabel plateLabel = new JLabel("License Plate:");
        plateLabel.setBounds(80, 140, 100, 30);

        JTextField plateField = new JTextField();
        plateField.setBounds(200, 140, 150, 30);

        JButton parkButton = new JButton("Park Vehicle");
        parkButton.setBounds(200, 190, 150, 30);
        parkButton.setEnabled(false);

        JLabel statusLabel = new JLabel();
        statusLabel.setBounds(80, 240, 400, 30);

        checkButton.addActionListener(e -> {
            try {
                ArrayList<String> spots = autopark.getAvailableSpots(vehicleTypeComboBox.getSelectedItem().toString());
                if (!spots.isEmpty()) {
                    String randomSpot = spots.get(new Random().nextInt(spots.size()));
                    availableSpotLabel.setText(randomSpot);
                    parkButton.setEnabled(true);
                    statusLabel.setText("Spot available!");
                    statusLabel.setForeground(new Color(0, 150, 0));
                } else {
                    availableSpotLabel.setText("");
                    parkButton.setEnabled(false);
                    statusLabel.setText("No available spots for " + vehicleTypeComboBox.getSelectedItem());
                    statusLabel.setForeground(Color.RED);
                }
            } catch (Exception ex) {
                statusLabel.setText("Error: " + ex.getMessage());
                statusLabel.setForeground(Color.RED);
            }
        });

        parkButton.addActionListener(e -> {
            try {
                String type = (String) vehicleTypeComboBox.getSelectedItem();
                String plate = plateField.getText();
                Vehicle vehicle = switch (type) {
                    case "Car" -> new Car(plate);
                    case "Electric Car" -> new ElectricCar(plate);
                    case "Motorcycle" -> new Motorcycle(plate);
                    default -> throw new IllegalArgumentException("Invalid vehicle type");
                };

                autopark.parkVehicle(vehicle);
                statusLabel.setText("Vehicle parked successfully in spot " + availableSpotLabel.getText());
                statusLabel.setForeground(new Color(0, 150, 0));
                parkButton.setEnabled(false);
            } catch (Exception ex) {
                statusLabel.setText("Error: " + ex.getMessage());
                statusLabel.setForeground(Color.RED);
            }
        });

        JButton returnButton = new JButton("Return Home");
        returnButton.setBounds(200, 290, 150, 30);
        returnButton.addActionListener(e -> parkFrame.dispose());

        parkFrame.add(vehicleTypeLabel);
        parkFrame.add(vehicleTypeComboBox);
        parkFrame.add(checkButton);
        parkFrame.add(spotLabel);
        parkFrame.add(availableSpotLabel);
        parkFrame.add(plateLabel);
        parkFrame.add(plateField);
        parkFrame.add(parkButton);
        parkFrame.add(statusLabel);
        parkFrame.add(returnButton);

        parkFrame.setVisible(true);
    }

    private void showParkedVehicles() {
        JFrame listFrame = new JFrame("Parked Vehicles");
        listFrame.setSize(600, 400);
        listFrame.setLocationRelativeTo(null);

        String[] columnNames = {"Vehicle Type", "License Plate", "Spot Number"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ArrayList<ParkingSpot> parkedVehicles = autopark.getParkedVehicles();
        for (ParkingSpot spot : parkedVehicles) {
            String type = spot.getVehicle().getClass().getSimpleName();
            model.addRow(new Object[]{type, spot.getVehiclePlate(), spot.getId()});
        }

        JTable table = new JTable(model);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(table);

        JButton returnButton = new JButton("Return Home");
        returnButton.addActionListener(e -> listFrame.dispose());

        listFrame.setLayout(new BorderLayout());
        listFrame.add(scrollPane, BorderLayout.CENTER);
        listFrame.add(returnButton, BorderLayout.SOUTH);

        listFrame.setVisible(true);
    }

    private void showAvailableSpots() {
        JFrame spotsFrame = new JFrame("Available Spots");
        spotsFrame.setSize(600, 400);
        spotsFrame.setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        String[] types = {"Car", "Electric Car", "Motorcycle"};
        for (String type : types) {
            ArrayList<String> spots = autopark.getAvailableSpots(type);
            JLabel typeLabel = new JLabel(type + " Spots Available: " + spots.size());
            contentPanel.add(typeLabel);

            DefaultListModel<String> listModel = new DefaultListModel<>();
            spots.forEach(listModel::addElement);
            JList<String> spotsList = new JList<>(listModel);
            JScrollPane scrollPane = new JScrollPane(spotsList);
            contentPanel.add(scrollPane);
            contentPanel.add(Box.createVerticalStrut(10));
        }

        JButton returnButton = new JButton("Return Home");
        returnButton.addActionListener(e -> spotsFrame.dispose());

        spotsFrame.setLayout(new BorderLayout());
        spotsFrame.add(new JScrollPane(contentPanel), BorderLayout.CENTER);
        spotsFrame.add(returnButton, BorderLayout.SOUTH);

        spotsFrame.setVisible(true);
    }

    private void createExitFrame() {
        JFrame exitFrame = new JFrame("Exit Vehicle");
        exitFrame.setSize(400, 300);
        exitFrame.setLayout(null);
        exitFrame.setLocationRelativeTo(null);

        JLabel plateLabel = new JLabel("License Plate:");
        plateLabel.setBounds(50, 30, 100, 30);

        JTextField plateField = new JTextField();
        plateField.setBounds(150, 30, 150, 30);

        JButton findButton = new JButton("Find");
        findButton.setBounds(150, 80, 150, 30);

        JLabel spotLabel = new JLabel();
        spotLabel.setBounds(50, 120, 300, 30);

        JButton exitButton = new JButton("Exit Vehicle");
        exitButton.setBounds(150, 160, 150, 30);
        exitButton.setEnabled(false);

        JButton returnButton = new JButton("Return Home");
        returnButton.setBounds(150, 200, 150, 30);

        findButton.addActionListener(e -> {
            try {
                Pair<String, Integer> location = autopark.queryPlate(plateField.getText());
                spotLabel.setText("Vehicle found in spot: " + location.getFirst());
                spotLabel.setForeground(new Color(0, 150, 0));
                exitButton.setEnabled(true);
            } catch (Exception ex) {
                spotLabel.setText("Error: " + ex.getMessage());
                spotLabel.setForeground(Color.RED);
                exitButton.setEnabled(false);
            }
        });

        exitButton.addActionListener(e -> {
            try {
                autopark.quitVehicle(plateField.getText());
                spotLabel.setText("Vehicle removed successfully!");
                spotLabel.setForeground(new Color(0, 150, 0));
                exitButton.setEnabled(false);
            } catch (Exception ex) {
                spotLabel.setText("Error: " + ex.getMessage());
                spotLabel.setForeground(Color.RED);
            }
        });

        returnButton.addActionListener(e -> exitFrame.dispose());

        exitFrame.add(plateLabel);
        exitFrame.add(plateField);
        exitFrame.add(findButton);
        exitFrame.add(spotLabel);
        exitFrame.add(exitButton);
        exitFrame.add(returnButton);

        exitFrame.setVisible(true);
    }

    private void createQueryFrame() {
        JFrame queryFrame = new JFrame("Find Vehicle");
        queryFrame.setSize(400, 250);
        queryFrame.setLayout(null);
        queryFrame.setLocationRelativeTo(null);

        JLabel plateLabel = new JLabel("License Plate:");
        plateLabel.setBounds(50, 30, 100, 30);

        JTextField plateField = new JTextField();
        plateField.setBounds(150, 30, 150, 30);

        JButton findButton = new JButton("Find");
        findButton.setBounds(150, 80, 150, 30);

        JLabel resultLabel = new JLabel();
        resultLabel.setBounds(50, 120, 300, 30);

        JButton returnButton = new JButton("Return Home");
        returnButton.setBounds(150, 160, 150, 30);

        findButton.addActionListener(e -> {
            try {
                Pair<String, Integer> location = autopark.queryPlate(plateField.getText());
                resultLabel.setText("Vehicle found in spot: " + location.getFirst());
                resultLabel.setForeground(new Color(0, 150, 0));
            } catch (Exception ex) {
                resultLabel.setText("Error: " + ex.getMessage());
                resultLabel.setForeground(Color.RED);
            }
        });

        returnButton.addActionListener(e -> queryFrame.dispose());

        queryFrame.add(plateLabel);
        queryFrame.add(plateField);
        queryFrame.add(findButton);
        queryFrame.add(resultLabel);
        queryFrame.add(returnButton);

        queryFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppGui::new);
    }
}