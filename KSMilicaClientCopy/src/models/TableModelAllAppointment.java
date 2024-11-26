/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Appointment;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import logic.Controller;

/**
 *
 * @author Admin
 */
public class TableModelAllAppointment extends AbstractTableModel {

    private ArrayList<Appointment> appointments;
    private String[] columns = {"Datum", "Usluga", "Ime i prezime"};

    public TableModelAllAppointment(ArrayList<Appointment> app) {
        appointments = app;
    }

    public TableModelAllAppointment() {
        try {
            appointments = Controller.getInstance().getAllAppointment();
        } catch (Exception ex) {
            Logger.getLogger(TableModelAllAppointment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return appointments.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Appointment a = appointments.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return a.getDate();
            case 1:
                return a.getService().getName();
            case 2:
                return a.getClient().getFirstname() + " " + a.getClient().getLastname();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }
}
