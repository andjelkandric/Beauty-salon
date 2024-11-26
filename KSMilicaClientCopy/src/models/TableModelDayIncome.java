/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Appointment;
import domain.Income;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import logic.Controller;

/**
 *
 * @author Admin
 */
public class TableModelDayIncome extends AbstractTableModel {

    private ArrayList<DailyServiceSummary> dailyServiceSummaries;
    private String[] columns = {"Datum", "Usluga", "Prihod"};

    public TableModelDayIncome(ArrayList<Income> incomes) {
        aggregateServiceDataByDay(incomes);
    }

    public TableModelDayIncome() {
        try {
            List<Income> incomes = Controller.getInstance().getAllIncomes();
            aggregateServiceDataByDay(incomes);
        } catch (Exception ex) {
            Logger.getLogger(TableModelDayAppointment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void aggregateServiceDataByDay(List<Income> incomes) {
        Map<LocalDate, Map<String, Double>> dailyServiceMap = new HashMap<>();

        for (Income income : incomes) {
            LocalDate date = income.getDate();  // Pretpostavljam da getDate() već vraća LocalDate

            String serviceName = income.getServiceId().getName();
            double amount = income.getMoney();

            dailyServiceMap.putIfAbsent(date, new HashMap<>());
            Map<String, Double> serviceIncomeMap = dailyServiceMap.get(date);

            serviceIncomeMap.put(serviceName, serviceIncomeMap.getOrDefault(serviceName, 0.0) + amount);
        }

        dailyServiceSummaries = new ArrayList<>();
        for (Map.Entry<LocalDate, Map<String, Double>> dayEntry : dailyServiceMap.entrySet()) {
            LocalDate day = dayEntry.getKey();
            for (Map.Entry<String, Double> serviceEntry : dayEntry.getValue().entrySet()) {
                String serviceName = serviceEntry.getKey();
                double totalIncome = serviceEntry.getValue();
                dailyServiceSummaries.add(new DailyServiceSummary(day, serviceName, (int) totalIncome));
            }
        }

        dailyServiceSummaries.sort((d1, d2) -> d2.getDate().compareTo(d1.getDate()));
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
