/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Appointment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import logic.Controller;

/**
 *
 * @author Admin
 */
public class TableModelPeriodAppointment extends AbstractTableModel {

    private ArrayList<ServiceSummary> serviceSummaries;
    private String[] columns = {"Usluga", "Količina"};

    public TableModelPeriodAppointment(ArrayList<Appointment> app) {
        aggregateServiceData(app);
    }

    public TableModelPeriodAppointment() {
        try {
            ArrayList<Appointment> appointments = Controller.getInstance().getAllAppointment();
            aggregateServiceData(appointments);
        } catch (Exception ex) {
            Logger.getLogger(TableModelPeriodAppointment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void aggregateServiceData(ArrayList<Appointment> appointments) {
        Map<String, Integer> serviceMap = new HashMap<>();
        for (Appointment appointment : appointments) {
            String serviceName = appointment.getService().getName();
            int amount=0;
            for (Appointment app : appointments) {
                if(app.getService().getName().equals(serviceName)){
                    amount++;
                }
            }
            serviceMap.put(serviceName, serviceMap.getOrDefault(serviceName, amount));
        }
        serviceSummaries = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : serviceMap.entrySet()) {
            serviceSummaries.add(new ServiceSummary(entry.getKey(), entry.getValue()));
        }
    }

    @Override
    public int getRowCount() {
        return serviceSummaries.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ServiceSummary summary = serviceSummaries.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return summary.getServiceName();
            case 1:
                return summary.getTotalAmount();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }
}
