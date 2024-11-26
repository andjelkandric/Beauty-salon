/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Appointment;
import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Controller;

/**
 *
 * @author Admin
 */
public class TableModelWeekAppointment extends AbstractTableModel {

    private ArrayList<WeeklyServiceSummary> weeklyServiceSummaries;
    private String[] columns = {"Nedelja", "Usluga", "Količina"};
    
    public TableModelWeekAppointment(ArrayList<Appointment> app){
        aggregateServiceDataByWeek(app);
    }

    public TableModelWeekAppointment() {
        try {
            List<Appointment> appointments = Controller.getInstance().getAllAppointment();
            aggregateServiceDataByWeek(appointments);
        } catch (Exception ex) {
            Logger.getLogger(TableModelWeekAppointment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void aggregateServiceDataByWeek(List<Appointment> appointments) {
//        Map<LocalDate, Map<String, Integer>> weeklyServiceMap = new HashMap<>();
//        for (Appointment appointment : appointments) {
//            LocalDate localDate = appointment.getDate();
//            // Pronađi početak nedelje (ponedeljak)
//            LocalDate startOfWeek = localDate.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
//
//            String serviceName = appointment.getService().getName();
//            int amount = (int) appointment.getAmount();
//
//            weeklyServiceMap.putIfAbsent(startOfWeek, new HashMap<>());
//            Map<String, Integer> serviceAmountMap = weeklyServiceMap.get(startOfWeek);
//            serviceAmountMap.put(serviceName, serviceAmountMap.getOrDefault(serviceName, 0) + amount);
//        }
//
//        weeklyServiceSummaries = new ArrayList<>();
//        for (Map.Entry<LocalDate, Map<String, Integer>> weekEntry : weeklyServiceMap.entrySet()) {
//            LocalDate weekStart = weekEntry.getKey();
//            for (Map.Entry<String, Integer> serviceEntry : weekEntry.getValue().entrySet()) {
//                String serviceName = serviceEntry.getKey();
//                int totalAmount = serviceEntry.getValue();
//                weeklyServiceSummaries.add(new WeeklyServiceSummary(weekStart, serviceName, totalAmount));
//            }
//        }
//        weeklyServiceSummaries.sort((s1, s2) -> s2.getWeekStartDate().compareTo(s1.getWeekStartDate()));
    }

    @Override
    public int getRowCount() {
        return weeklyServiceSummaries.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        WeeklyServiceSummary summary = weeklyServiceSummaries.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return summary.getWeekStartDate().format(DateTimeFormatter.ofPattern("ww-yyyy"));
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
