package main.java.parkingLotApp.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage {

     public static void crateParkButton(JPanel panel) {

        JButton parkButton = new JButton("Park");
        parkButton.setBounds(100, 100, 100, 50);
        parkButton.setBackground(new Color(30, 31, 34));
        parkButton.setForeground(new Color(255, 255, 255));

        parkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Park button clicked");
            }
        });
        panel.add(parkButton);
    }


    public static void createExitButton(JPanel panel) {

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(220, 100, 100, 50);
        exitButton.setBackground(new Color(231, 31, 31));
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
        listParkedVehiclesButton.setBounds(320, 80, 100, 50);
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
        showAvailableSpotsButton.setBounds(220, 100, 100, 50);
        showAvailableSpotsButton.setBackground(new Color(231, 31, 31));
        showAvailableSpotsButton.setForeground(new Color(255, 255, 255));

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
        queryPlateButton.setBounds(220, 100, 100, 50);
        queryPlateButton.setBackground(new Color(231, 31, 31));
        queryPlateButton.setForeground(new Color(255, 255, 255));

        queryPlateButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Find a Vehicle's Parking Spot clicked");
                // expects implementation
            }
        });
        panel.add(queryPlateButton);
    }}
