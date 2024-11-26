/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Appointment;
import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Controller;

public class TableModelDayAppointment extends AbstractTableModel {

    private ArrayList<DailyServiceSummary> dailyServiceSummaries;
    private String[] columns = {"Datum", "Usluga", "Količina"};

    public TableModelDayAppointment(ArrayList<Appointment> app) {
        aggregateServiceDataByDay(app);
    }

    public TableModelDayAppointment() {
        try {
            List<Appointment> appointments = Controller.getInstance().getAllAppointment();
            aggregateServiceDataByDay(appointments);
        } catch (Exception ex) {
            Logger.getLogger(TableModelDayAppointment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void aggregateServiceDataByDay(List<Appointment> appointments) {
        Map<LocalDate, Map<String, Integer>> dailyServiceMap = new HashMap<>();

        for (Appointment appointment : appointments) {
            LocalDate localDate = appointment.getDate();
            String serviceName = appointment.getService().getName();

            dailyServiceMap.putIfAbsent(localDate, new HashMap<>());
            Map<String, Integer> serviceCountMap = dailyServiceMap.get(localDate);

            // Count how many times each service was performed
            serviceCountMap.put(serviceName, serviceCountMap.getOrDefault(serviceName, 0) + 1);
        }

        // Aggregate data into the list
        dailyServiceSummaries = new ArrayList<>();
        for (Map.Entry<LocalDate, Map<String, Integer>> dayEntry : dailyServiceMap.entrySet()) {
            LocalDate date = dayEntry.getKey();
            for (Map.Entry<String, Integer> serviceEntry : dayEntry.getValue().entrySet()) {
                String serviceName = serviceEntry.getKey();
                int totalCount = serviceEntry.getValue();
                dailyServiceSummaries.add(new DailyServiceSummary(date, serviceName, totalCount));
            }
        }

        // Sort by date in descending order
        dailyServiceSummaries.sort(Comparator.comparing(DailyServiceSummary::getDate).reversed());
    }

    @Override
    public int getRowCount() {
        return dailyServiceSummaries.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DailyServiceSummary summary = dailyServiceSummaries.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return summary.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            case 1:
                return summary.getServiceName();
            case 2:
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
