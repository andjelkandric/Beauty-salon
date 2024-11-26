/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Appointment;
import java.time.LocalDate;
import javax.swing.table.AbstractTableModel;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Controller;
import java.time.*;

/**
 *
 * @author Admin
 */
public class TableModelMonthAppointment extends AbstractTableModel {

    private ArrayList<MonthlyServiceSummary> monthlyServiceSummaries;
    private String[] columns = {"Mesec", "Usluga","Količina"};

    public TableModelMonthAppointment(ArrayList<Appointment> app) {
        aggregateServiceDataByMonth(app);
    }

    public TableModelMonthAppointment() {
        try {
            List<Appointment> appointments = Controller.getInstance().getAllAppointment();
            aggregateServiceDataByMonth(appointments);
        } catch (Exception ex) {
            Logger.getLogger(TableModelPeriodAppointment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void aggregateServiceDataByMonth(List<Appointment> appointments) {
        Map<YearMonth, Map<String, Integer>> monthlyServiceMap = new HashMap<>();

        for (Appointment appointment : appointments) {
            LocalDate localDate = appointment.getDate();
            YearMonth month = YearMonth.from(localDate);

            String serviceName = appointment.getService().getName();

            monthlyServiceMap.putIfAbsent(month, new HashMap<>());
            Map<String, Integer> serviceCountMap = monthlyServiceMap.get(month);

            
            serviceCountMap.put(serviceName, serviceCountMap.getOrDefault(serviceName, 0) + 1);
        }

        monthlyServiceSummaries = new ArrayList<>();
        for (Map.Entry<YearMonth, Map<String, Integer>> monthEntry : monthlyServiceMap.entrySet()) {
            YearMonth month = monthEntry.getKey();
            for (Map.Entry<String, Integer> serviceEntry : monthEntry.getValue().entrySet()) {
                String serviceName = serviceEntry.getKey();
                int totalCount = serviceEntry.getValue();
                monthlyServiceSummaries.add(new MonthlyServiceSummary(month, serviceName, totalCount));
            }
        }

        // Sort summaries by month in descending order
        monthlyServiceSummaries.sort((m1, m2) -> m2.getMonth().compareTo(m1.getMonth()));
    }

    @Override
    public int getRowCount() {
        return monthlyServiceSummaries.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MonthlyServiceSummary summary = monthlyServiceSummaries.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return summary.getMonth().format(DateTimeFormatter.ofPattern("MM-yyyy"));
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
