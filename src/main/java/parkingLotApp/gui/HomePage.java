package main.java.parkingLotApp.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.parkingLotApp.vehicles.*;
import main.java.parkingLotApp.parking.*;
import main.java.parkingLotApp.utils.Pair;

public class HomePage {

     public static void crateParkButton(JFrame frame, JPanel panel) {

        JButton parkButton = new JButton("Park");
         parkButton.setBounds(50, 50, 200, 100);
         parkButton.setBackground(new Color(31, 161, 231));

        parkButton.setForeground(new Color(255, 255, 255));

        parkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Park button clicked");

                frame.dispose();//to clear the main frame


                JFrame parkframe = new JFrame("Parking Lot Application");
                JLabel vehicleType = new JLabel("Vehicle Type");
                parkframe.add(vehicleType);
                vehicleType.setBounds(80,40,100,40);

                JLabel licensePlate = new JLabel("License Plate");
                parkframe.add(licensePlate);
                licensePlate.setBounds(80,80,100,40);
                JLabel Number = new JLabel("Number");
                parkframe.add(Number);
                Number.setBounds(98,92,100,40);

                String arr[] = {"Car","Electric Car","Motorcycle"};
                JComboBox vehicleTypeComboBox = new JComboBox(arr);
                vehicleTypeComboBox.setBounds(200,40,150,30);
                parkframe.add(vehicleTypeComboBox);

                JButton checkButton = new JButton("Check Occupancy");
                parkframe.add(checkButton);
                checkButton.setBounds(400,45,130,40);

                JLabel foundSpot = new JLabel();
                parkframe.add(foundSpot);
                foundSpot.setBounds(400,80,130,40);

                JLabel numOfFoundSpots = new JLabel();
                parkframe.add(numOfFoundSpots);

                JTextField writeLicensePlate = new JTextField();
                parkframe.add(writeLicensePlate);

                //checkButton.addActionListener(new ActionListener() {
                //  Vehicle vehicle;

                //  if(((String)vehicleTypeComboBox.get()))


            }
        });
        panel.add(parkButton);
    }


    public static void createExitButton(JPanel panel) {

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(345, 50, 200, 100);
        exitButton.setBackground(new Color(31, 161, 231));
        exitButton.setForeground(new Color(255, 255, 255));

        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("exit clicked");


            }
        });
        panel.add(exitButton);
    }


    public static void createListParkedVehiclesButton(JPanel panel) {
        JButton listParkedVehiclesButton = new JButton("List Parked Vehicles");
        listParkedVehiclesButton.setBounds(20, 250, 180, 70);
        listParkedVehiclesButton.setBackground(new Color(31, 161, 231));
        listParkedVehiclesButton.setForeground(new Color(255, 255, 255));

        listParkedVehiclesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("listParkedVehicles clicked");
                // expects implementation
            }
        });
        panel.add(listParkedVehiclesButton);
    }

    public static void createShowAvailableSpotsButton(JPanel panel) {
        JButton showAvailableSpotsButton = new JButton("Show Available Spots");
        showAvailableSpotsButton.setForeground(new Color(255, 255, 255));
        showAvailableSpotsButton.setBounds(210, 250, 180, 70);
        showAvailableSpotsButton.setBackground(new Color(31, 161, 231));

        showAvailableSpotsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("showAvailableSpots clicked");
                // expects implementation
            }
        });
        panel.add(showAvailableSpotsButton);
    }


    public static void createQueryPlateButton(JPanel panel) {
        JButton queryPlateButton = new JButton("Find a Vehicle's Parking Spot");
        queryPlateButton.setForeground(new Color(255, 255, 255));
        queryPlateButton.setBounds(400, 250, 180, 70);
        queryPlateButton.setBackground(new Color(31, 161, 231));

        queryPlateButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Find a Vehicle's Parking Spot clicked");
                // expects implementation
            }
        });
        panel.add(queryPlateButton);
    }}
