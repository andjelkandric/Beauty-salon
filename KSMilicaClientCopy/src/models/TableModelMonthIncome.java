/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Income;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
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
public class TableModelMonthIncome extends AbstractTableModel {

    private ArrayList<MonthlyServiceSummary> monthlyServiceSummaries;
    private String[] columns = {"Mesec", "Usluga", "Prihod"};

    public TableModelMonthIncome(ArrayList<Income> incomes) {
        aggregateServiceDataByMonth(incomes);
    }

    public TableModelMonthIncome() {
        try {
            List<Income> incomes = Controller.getInstance().getAllIncomes();
            aggregateServiceDataByMonth(incomes);
        } catch (Exception ex) {
            Logger.getLogger(TableModelPeriodAppointment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void aggregateServiceDataByMonth(List<Income> incomes) {
        Map<YearMonth, Map<String, Double>> monthlyServiceMap = new HashMap<>();

        for (Income income : incomes) {
            LocalDate date = income.getDate();

            YearMonth month = YearMonth.from(date);

            String serviceName = income.getServiceId().getName();
            double amount = income.getMoney();

            monthlyServiceMap.putIfAbsent(month, new HashMap<>());
            Map<String, Double> serviceIncomeMap = monthlyServiceMap.get(month);

            serviceIncomeMap.put(serviceName, serviceIncomeMap.getOrDefault(serviceName, 0.0) + amount);
        }

        monthlyServiceSummaries = new ArrayList<>();
        for (Map.Entry<YearMonth, Map<String, Double>> monthEntry : monthlyServiceMap.entrySet()) {
            YearMonth month = monthEntry.getKey();
            for (Map.Entry<String, Double> serviceEntry : monthEntry.getValue().entrySet()) {
                String serviceName = serviceEntry.getKey();
                double totalIncome = serviceEntry.getValue();
                monthlyServiceSummaries.add(new MonthlyServiceSummary(month, serviceName, (int) totalIncome));
            }
        }

        monthlyServiceSummaries.sort((m1, m2) -> m2.getMonth().compareTo(m1.getMonth()));

// Sada možete da koristite listu `monthlyServiceSummaries` za prikazivanje u tabeli.
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
