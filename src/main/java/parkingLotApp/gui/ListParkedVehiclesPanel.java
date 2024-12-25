package main.java.parkingLotApp.gui;

import main.java.parkingLotApp.parking.ParkingSpot;
import main.java.parkingLotApp.utils.SortType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ListParkedVehiclesPanel extends JPanel {
    public final ParkingLotApp app;
    public final DefaultTableModel tableModel;
    public final JComboBox<String> sortComboBox;

    public ListParkedVehiclesPanel(ParkingLotApp app) {
        this.app = app;

        setLayout(null);

        JLabel titleLabel = new JLabel("Parked Vehicles");
        titleLabel.setBounds(200, 10, 200, 30);
        this.add(titleLabel);

        String[] columnNames = {"Vehicle Type", "License Plate", "Spot ID"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable parkedVehiclesTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(parkedVehiclesTable);
        scrollPane.setBounds(50, 50, 500, 200);
        this.add(scrollPane);

        JLabel sortLabel = new JLabel("Sort By:");
        sortLabel.setBounds(50, 270, 100, 30);
        this.add(sortLabel);

        sortComboBox = new JComboBox<>(new String[]{"Spot ID", "License Plate", "Vehicle Type"});
        sortComboBox.setBounds(120, 270, 150, 30);
        this.add(sortComboBox);

        JButton returnButton = app.createReturnHomeButton();
        returnButton.setBounds(420, 270, 130, 30);
        this.add(returnButton);
    }

    public void updateTable() {
        tableModel.setRowCount(0); // Clear the table
        SortType selectedSort = getSortTypeFromComboBox();

        ArrayList<ParkingSpot> parkedVehicles = app.autopark.getParkedVehicles(selectedSort);
        for (ParkingSpot spot : parkedVehicles) {
            tableModel.addRow(new Object[]{
                    spot.getVehiclePlate().getClass().getSimpleName(),
                    spot.getVehiclePlate(),
                    spot.getId()
            });
        }
    }

    private SortType getSortTypeFromComboBox() {
        String selected = (String) sortComboBox.getSelectedItem();
        switch (selected) {
            case "Spot ID":
                return SortType.BySpot;
            case "License Plate":
                return SortType.ByPlate;
            case "Vehicle Type":
                return SortType.ByType;
            default:
                throw new IllegalArgumentException("Invalid sort type");
        }
    }
}
