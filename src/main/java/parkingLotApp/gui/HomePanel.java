package main.java.parkingLotApp.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel {
    public final ParkingLotApp app;
    // done
    HomePanel(ParkingLotApp app) {
        this.app = app;
        setLayout(null);


        JButton parkButton = new JButton("Park");
        parkButton.setBounds(50, 50, 200, 100);
        parkButton.addActionListener(e -> app.cardLayout.show(app.mainPanel, "Park"));
        this.add(parkButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(345, 50, 200, 100);
        exitButton.addActionListener(e -> app.cardLayout.show(app.mainPanel, "Exit"));
        this.add(exitButton);

        JButton listVehiclesButton = new JButton("List Parked Vehicles");
        listVehiclesButton.setBounds(20, 250, 180, 70);
        listVehiclesButton.addActionListener(e -> app.cardLayout.show(app.mainPanel, "ListVehicles"));
        this.add(listVehiclesButton);

        JButton availableSpotsButton = new JButton("Show Available Spots");
        availableSpotsButton.setBounds(210, 250, 180, 70);
        this.add(availableSpotsButton);



        availableSpotsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.availableSpotsPanel.updateState();
                app.cardLayout.show(app.mainPanel, "AvailableSpots");
            }
        });
        this.add(availableSpotsButton);


        JButton findVehicleButton = new JButton("Find a Vehicle's Parking Spot");
        findVehicleButton.setBounds(400, 250, 215, 70);
        findVehicleButton.addActionListener(e -> app.cardLayout.show(app.mainPanel, "FindVehicle"));
        this.add(findVehicleButton);
    }
}
