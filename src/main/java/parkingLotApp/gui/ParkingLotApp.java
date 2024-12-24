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


public class ParkingLotApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Autopark autopark;

    public ParkingLotApp() {
        autopark = new Autopark();

        setTitle("Parking Lot Application");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createHomePanel(), "Home");
        mainPanel.add(createParkPanel(), "Park");
        mainPanel.add(createExitPanel(), "Exit");
        mainPanel.add(createListParkedVehiclesPanel(), "ListVehicles");
        mainPanel.add(createAvailableSpotsPanel(), "AvailableSpots");
        mainPanel.add(createFindVehiclePanel(), "FindVehicle");

        add(mainPanel);
        cardLayout.show(mainPanel, "Home");
        setVisible(true);
    }


    //homepanel done
    private JPanel createHomePanel() {
        JPanel homePanel = new JPanel(null);

        JButton parkButton = new JButton("Park");
        parkButton.setBounds(50, 50, 200, 100);
        parkButton.addActionListener(e -> cardLayout.show(mainPanel, "Park"));
        homePanel.add(parkButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(345, 50, 200, 100);
        exitButton.addActionListener(e -> cardLayout.show(mainPanel, "Exit"));
        homePanel.add(exitButton);

        JButton listVehiclesButton = new JButton("List Parked Vehicles");
        listVehiclesButton.setBounds(20, 250, 180, 70);
        listVehiclesButton.addActionListener(e -> cardLayout.show(mainPanel, "ListVehicles"));
        homePanel.add(listVehiclesButton);

        JButton availableSpotsButton = new JButton("Show Available Spots");
        availableSpotsButton.setBounds(210, 250, 180, 70);
        availableSpotsButton.addActionListener(e -> cardLayout.show(mainPanel, "AvailableSpots"));
        homePanel.add(availableSpotsButton);

        JButton findVehicleButton = new JButton("Find a Vehicle's Parking Spot");
        findVehicleButton.setBounds(400, 250, 180, 70);
        findVehicleButton.addActionListener(e -> cardLayout.show(mainPanel, "FindVehicle"));
        homePanel.add(findVehicleButton);

        return homePanel;
    }

    //not done
    private JPanel createParkPanel() {
        JPanel parkPanel = new JPanel(null);

        JLabel vehicleTypeLabel = new JLabel("Vehicle Type:");
        vehicleTypeLabel.setBounds(50, 50, 100, 30);
        parkPanel.add(vehicleTypeLabel);

        JComboBox<String> vehicleTypeComboBox = new JComboBox<>(new String[]{"Car", "Electric Car", "Motorcycle"});
        vehicleTypeComboBox.setBounds(150, 50, 150, 30);
        parkPanel.add(vehicleTypeComboBox);

        JLabel plateLabel = new JLabel("License Plate:");
        plateLabel.setBounds(50, 100, 100, 30);
        parkPanel.add(plateLabel);

        JTextField plateField = new JTextField();
        plateField.setBounds(150, 100, 150, 30);
        parkPanel.add(plateField);

        JButton checkButton = new JButton("Check Availability");
        checkButton.setBounds(320, 50, 200, 30);
        JLabel statusLabel = new JLabel();
        statusLabel.setBounds(50, 150, 400, 30);
        parkPanel.add(statusLabel);
        parkPanel.add(checkButton);

        checkButton.addActionListener(e -> {
            String type = (String) vehicleTypeComboBox.getSelectedItem();









        });

        JButton returnButton = createReturnHomeButton();
        returnButton.setBounds(200, 300, 150, 30);
        parkPanel.add(returnButton);

        return parkPanel;
    }



    //not done
    private JPanel createExitPanel() {
        JPanel exitPanel = new JPanel(null);

        JLabel plateLabel = new JLabel("License Plate:");
        plateLabel.setBounds(50, 50, 100, 30);
        exitPanel.add(plateLabel);

        JTextField plateField = new JTextField();
        plateField.setBounds(150, 50, 150, 30);
        exitPanel.add(plateField);

        JButton findButton = new JButton("Find");
        findButton.setBounds(320, 50, 100, 30);
        exitPanel.add(findButton);

        JLabel statusLabel = new JLabel();
        statusLabel.setBounds(50, 100, 400, 30);
        exitPanel.add(statusLabel);

        findButton.addActionListener(e -> {
            String plate = plateField.getText();

                                                // implementation NEEDED
        });

        JButton returnButton = createReturnHomeButton();
        returnButton.setBounds(200, 300, 150, 30);
        exitPanel.add(returnButton);

        return exitPanel;
    }

    //not done
    private JPanel createListParkedVehiclesPanel() {
        JPanel listPanel = new JPanel(new BorderLayout());

        String[] columnNames = {"Type", "Plate Number", "Spot Number"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

                                                                  //not done

        return listPanel;
    }

    //not done
    private JPanel createAvailableSpotsPanel() {
        JPanel spotsPanel = new JPanel(null);

        //not complete

        JButton returnButton = createReturnHomeButton();
        returnButton.setBounds(200, 300, 150, 30);
        spotsPanel.add(returnButton);

        return spotsPanel;
    }

    //needs implementation
    private JPanel createFindVehiclePanel() {
        JPanel findPanel = new JPanel(null);

        JLabel plateLabel = new JLabel("License Plate Number:");
        plateLabel.setBounds(50, 50, 150, 30);
        findPanel.add(plateLabel);

        JTextField plateField = new JTextField();
        plateField.setBounds(200, 50, 150, 30);
        findPanel.add(plateField);

        JButton findButton = new JButton("Find");
        findButton.setBounds(400, 50, 100, 30);
        findPanel.add(findButton);

        JLabel statusLabel = new JLabel();
        statusLabel.setBounds(50, 100, 400, 30);
        findPanel.add(statusLabel);

        findButton.addActionListener(e -> {
            String plate = plateField.getText();

            //implementation
        });

        JButton returnButton = createReturnHomeButton();
        returnButton.setBounds(200, 300, 150, 30);
        findPanel.add(returnButton);

        return findPanel;
    }

    //RETURN BUTTON DONE
    private JButton createReturnHomeButton() {
        JButton returnButton = new JButton("Return Home");
        returnButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        returnButton.setBackground(new Color(31, 161, 231));
        returnButton.setForeground(Color.WHITE);
        return returnButton;
    }
}
