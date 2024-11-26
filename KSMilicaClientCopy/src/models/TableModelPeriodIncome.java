/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Income;
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
public class TableModelPeriodIncome extends AbstractTableModel{
    private ArrayList<ServiceSummary> serviceSummaries;
    private String[] columns = {"Usluga", "Prihod"};

    public TableModelPeriodIncome(ArrayList<Income> incomes) {
        aggregateServiceData(incomes);
    }

    public TableModelPeriodIncome() {
        try {
            ArrayList<Income> incomes = Controller.getInstance().getAllIncomes();
            aggregateServiceData(incomes);
        } catch (Exception ex) {
            Logger.getLogger(TableModelPeriodAppointment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void aggregateServiceData(ArrayList<Income> incomes) {
        Map<String, Integer> serviceMap = new HashMap<>();
        for (Income income : incomes) {
            String serviceName = income.getServiceId().getName();
            int amount=(int) income.getMoney();
            serviceMap.put(serviceName, serviceMap.getOrDefault(serviceName, 0)+amount);
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
