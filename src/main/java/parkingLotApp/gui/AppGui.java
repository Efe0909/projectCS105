package main.java.parkingLotApp.gui;

import jdk.jshell.spi.ExecutionControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AppGui {
    public int WINDOW_HEIGHT = 400;
    public int WINDOW_WIDTH = 500;

    public JFrame frame;

    public JPanel homePanel;
    public JPanel parkPanel;
    public JPanel exitPanel;
    public JPanel parkedVehiclesPanel;
    public JPanel availableSpotsPanel;
    public JPanel plateQueryPanel;

    public AppGui() {
        initFrame();
        initHomeScreen();

        frame.setVisible(true);
    }

    public void initFrame() {
        frame = new JFrame("Parking Lot Application");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initHomeScreen() {
        homePanel = new JPanel();
        homePanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        homePanel.setBackground(new Color(255, 255, 255));
        homePanel.setLayout(null);

        crateParkButton();
        createExitButton();
        createListParkedVehiclesButton();
        createShowAvailableSpotsButton();
        createQueryPlateButton();

        frame.add(homePanel);
    }

    public void crateParkButton() {

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
        homePanel.add(parkButton);
    }


    public void createExitButton() {

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
        homePanel.add(exitButton);
    }


    public void createListParkedVehiclesButton() {
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
        homePanel.add(listParkedVehiclesButton);
    }

    public void createShowAvailableSpotsButton() {
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
        homePanel.add(showAvailableSpotsButton);
    }


    public void createQueryPlateButton() {
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
        homePanel.add(queryPlateButton);
    }

}




