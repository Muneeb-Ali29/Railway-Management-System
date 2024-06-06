package Trips;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Employees.EmployeesDatabase;
import RailwayManagementSystem.Database;
import RailwayManagementSystem.GUI;
import RailwayManagementSystem.Main;
import Trains.TrainsDatabase;

public class EditTrip {
    
    private JComboBox<String> id, deptHH, deptmm, arrHH, arrmm, datedd, dateMM, dateyyyy,
                    driver, train;
    private JTextField start, destination, price;
    
    public EditTrip(JFrame parent, Database database) throws SQLException {
        
        String[] HH = new String[25];
        HH[0] = "HH";
        for (int i=0;i<24;i++) {
            HH[i+1] = String.format("%02d", i);
        }
        
        String[] mm = new String[61];
        mm[0] = "mm";
        for (int i=0;i<60;i++) {
            mm[i+1] = String.format("%02d", i);
        }
        
        JFrame frame = new JFrame("Edit Trip Schedule");
        frame.setSize(750, 675);
        frame.getContentPane().setBackground(GUI.background);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(parent);
        
        JPanel panel = new JPanel(new GridLayout(10, 2, 20, 20));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 30, 50));
        
        panel.add(GUI.JLabel("ID:"));
        id = GUI.JComboBox(TripsDatabase.getIDs(database));
        panel.add(id);
        
        panel.add(GUI.JLabel("Start:"));
        start = GUI.JTextField();
        panel.add(start);
        
        panel.add(GUI.JLabel("Destination:"));
        destination = GUI.JTextField();
        panel.add(destination);
        
        panel.add(GUI.JLabel("Departure Time:"));
        JPanel dept = new JPanel(new GridLayout(1, 2, 10, 10));
        dept.setBackground(null);
        deptHH = GUI.JComboBox(HH);
        deptmm = GUI.JComboBox(mm);
        dept.add(deptHH);
        dept.add(deptmm);
        panel.add(dept);
        
        panel.add(GUI.JLabel("Arrival Time:"));
        JPanel arr = new JPanel(new GridLayout(1, 2, 10, 10));
        arr.setBackground(null);
        arrHH = GUI.JComboBox(HH);
        arrmm = GUI.JComboBox(mm);
        arr.add(arrHH);
        arr.add(arrmm);
        panel.add(arr);
        
        String[] dd = new String[32];
        dd[0] = "dd";
        for (int i=1;i<32;i++) {
            dd[i] = String.format("%02d", i);
        }
        
        String[] MM = new String[13];
        MM[0] = "MM";
        for (int i=1;i<13;i++) {
            MM[i] = String.format("%02d", i);
        }
        
        String[] yyyy = new String[51];
        yyyy[0] = "yyyy";
        for (int i=2010;i<2060;i++) {
            yyyy[i-2009] = String.format("%02d", i);
        }
        
        panel.add(GUI.JLabel("Date:"));
        JPanel date = new JPanel(new GridLayout(1, 3, 10, 10));
        date.setBackground(null);
        datedd = GUI.JComboBox(dd);
        dateMM = GUI.JComboBox(MM);
        dateyyyy = GUI.JComboBox(yyyy);
        date.add(datedd);
        date.add(dateMM);
        date.add(dateyyyy);
        panel.add(date);
        
        panel.add(GUI.JLabel("Price:"));
        price = GUI.JTextField();
        panel.add(price);
        
        panel.add(GUI.JLabel("Driver:"));
        driver = GUI.JComboBox(
                EmployeesDatabase.getEmployeesNames(database));
        panel.add(driver);
        
        panel.add(GUI.JLabel("Train"));
        train = GUI.JComboBox(TrainsDatabase.getTrains(database));
        panel.add(train);
        
        JButton submit = GUI.JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (id.getSelectedItem() != null) { // Check if ID is selected
                    Trip trip = new Trip();
                    trip.setID(Integer.parseInt(id.getSelectedItem().toString()));
                    trip.setStart(start.getText());
                    trip.setDestination(destination.getText());
                    String deptTime = deptHH.getSelectedItem().toString()
                            +":"+deptmm.getSelectedItem().toString();
                    String arrTime = arrHH.getSelectedItem().toString()+":"+
                            arrmm.getSelectedItem().toString();
                    trip.setDepartureTime(deptTime);
                    trip.setArrivalTime(arrTime);
                    String d = dateyyyy.getSelectedItem().toString()+
                            "-"+dateMM.getSelectedItem().toString()+
                            "-"+datedd.getSelectedItem().toString();
                    trip.setDate(d);
                    trip.setBookedSeats(0);
                    trip.setPrice(Double.parseDouble(price.getText()));
                    try {
                        trip.setDriver(EmployeesDatabase.getEmployeeByName(
                                driver.getSelectedItem().toString(), database));
                        trip.setTrain(TrainsDatabase.getTrainByDescription(
                                train.getSelectedItem().toString(), database));
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(frame, e1.getMessage());
                    }
                    trip.setPassengers(new ArrayList<>());
                    try {
                        TripsDatabase.EditTrip(trip, database);
                        JOptionPane.showMessageDialog(frame, "Trip updated successfully");
                        Main.refreshTable(TripsDatabase.getAllTrips(database));
                        frame.dispose();
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(frame, "Operation Failed");
                        frame.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select an ID");
                }
            }
        });
        panel.add(submit);
        
        JButton delete = GUI.JButton("Delete");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (id.getSelectedItem() != null) { // Check if ID is selected
                    try {
                        TripsDatabase.DeleteTrip(id.getSelectedItem().toString(), database);
                        JOptionPane.showMessageDialog(frame, "Trip deleted successfully");
                        Main.refreshTable(TripsDatabase.getAllTrips(database));
                        frame.dispose();
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(frame, "Operation Failed");
                        frame.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select an ID");
                }
            }
        });
        panel.add(delete);
        
        if (id.getSelectedItem()!=null) refreshData(database);
        
        id.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    refreshData(database);
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(frame, e1.getMessage());
                    frame.dispose();
                }
            }
        });
        
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        
        // Refresh data initially
        if (id.getSelectedItem() != null) {
            try {
                refreshData(database);
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(frame, e1.getMessage());
                frame.dispose();
            }
        }
    }
    private void refreshData(Database database) throws SQLException {
        if (id.getSelectedItem() != null) {
            String ID = id.getSelectedItem().toString();
            Trip t = TripsDatabase.getTrip(ID, database);
            start.setText(t.getStart());
            destination.setText(t.getDestination());
            price.setText(String.valueOf(t.getPrice()));
            driver.setSelectedItem(t.getDriver().getName());
            train.setSelectedItem(t.getTrain().getDescription());
            deptHH.setSelectedItem(t.getDeptHour());
            deptmm.setSelectedItem(t.getDeptMin());
            arrHH.setSelectedItem(t.getArrHour());
            arrmm.setSelectedItem(t.getArrMin());
            datedd.setSelectedItem(t.getDay());
            dateMM.setSelectedItem(t.getMonth());
            dateyyyy.setSelectedItem(t.getYear());
        }
    }
    
}