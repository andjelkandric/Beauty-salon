/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Expense;
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
public class TableModelPeriodExpense extends AbstractTableModel {

    private ArrayList<ExpenseSummary> expenseSummaries;
    private String[] columns = {"Stavka", "Ukupno"};
    private String parameter = "";

    public TableModelPeriodExpense() {
        try {
            ArrayList<Expense> expenses = Controller.getInstance().getAllExpenses();
            aggregateExpenseData(expenses);
        } catch (Exception ex) {
            Logger.getLogger(TableModelService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public TableModelPeriodExpense(ArrayList<Expense> searchedExpenses) {
        aggregateExpenseData(searchedExpenses);
    }

    @Override
    public int getRowCount() {
        return expenseSummaries.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ExpenseSummary summary = expenseSummaries.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return summary.getItemName();
            case 1:
                return summary.getTotalCost();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }

    public ExpenseSummary getSelectedExpenseSummary(int row) {
        return expenseSummaries.get(row);
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
        refreshTable();
    }

    public void refreshTable() {
        try {
            ArrayList<Expense> expenses = Controller.getInstance().getAllExpenses();
            if (!parameter.equals("")) {
                ArrayList<Expense> filteredExpenses = new ArrayList<>();
                for (Expense expense : expenses) {
                    if (expense.getItem().toLowerCase().contains(parameter.toLowerCase())) {
                        filteredExpenses.add(expense);
                    }
                }
                aggregateExpenseData(filteredExpenses);
            } else {
                aggregateExpenseData(expenses);
            }
            fireTableDataChanged();
        } catch (Exception ex) {
            System.out.println("prazna lista");
            //ex.printStackTrace();
        }
    }

    public ArrayList<ExpenseSummary> getExpenseSummaries() {
        return expenseSummaries;
    }

    private void aggregateExpenseData(ArrayList<Expense> expenses) {
        Map<String, Double> expenseMap = new HashMap<>();

        for (Expense expense : expenses) {
            String itemName = expense.getItem();
            double totalCost = expense.getPrice() * expense.getAmount();
            expenseMap.put(itemName, expenseMap.getOrDefault(itemName, 0.0) + totalCost);
        }

        expenseSummaries = new ArrayList<>();
        for (Map.Entry<String, Double> entry : expenseMap.entrySet()) {
            expenseSummaries.add(new ExpenseSummary(entry.getKey(), entry.getValue()));
        }
    }
}
