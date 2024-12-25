package main.java.parkingLotApp.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import main.java.parkingLotApp.parking.*;
import main.java.parkingLotApp.utils.Pair;
import main.java.parkingLotApp.utils.VehicleType;
import main.java.parkingLotApp.vehicles.*;


public class ParkingLotApp extends JFrame {

    public CardLayout cardLayout;
    public JPanel mainPanel;
    public Autopark autopark;
    public HomePanel homePanel;
    public ParkPanel parkPanel;
    public ExitPanel exitPanel;
    public ListParkedVehiclesPanel listParkedVehiclesPanel;
    public AvailableSpotsPanel availableSpotsPanel;
    public FindVehiclePanel findVehiclePanel;

    public ParkingLotApp() {

        autopark = new Autopark();

        setTitle("Parking Lot Application");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        homePanel = new HomePanel(this);
        mainPanel.add(homePanel, "Home");

        parkPanel = new ParkPanel(this);
        mainPanel.add(parkPanel, "Park");

        exitPanel = new ExitPanel(this);
        mainPanel.add(exitPanel, "Exit");

        listParkedVehiclesPanel = new ListParkedVehiclesPanel(this);
        mainPanel.add(listParkedVehiclesPanel, "ListVehicles");

        availableSpotsPanel = new AvailableSpotsPanel(this);
        mainPanel.add(availableSpotsPanel, "AvailableSpots");

        findVehiclePanel = new FindVehiclePanel(this);
        mainPanel.add(findVehiclePanel, "FindVehicle");

        add(mainPanel);
        cardLayout.show(mainPanel, "Home");
        setVisible(true);
    }

    //RETURN BUTTON DONE
    public JButton createReturnHomeButton() {
        JButton returnButton = new JButton("Return Home");
        returnButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        returnButton.setBackground(new Color(31, 161, 231));
        returnButton.setForeground(Color.WHITE);
        return returnButton;
    }
}
