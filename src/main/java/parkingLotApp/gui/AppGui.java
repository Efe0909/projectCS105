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

        HomePage.crateParkButton(homePanel);
        HomePage.createExitButton(homePanel);
        HomePage.createListParkedVehiclesButton(homePanel);
        HomePage.createShowAvailableSpotsButton(homePanel);
        HomePage.createQueryPlateButton(homePanel);

        frame.add(homePanel);
    }
}




