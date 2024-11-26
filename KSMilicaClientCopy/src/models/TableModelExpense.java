/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Expense;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import logic.Controller;

/**
 *
 * @author Admin
 */
public class TableModelExpense extends AbstractTableModel {

    private ArrayList<Expense> expenses;
    private String[] columns = {"Datum", "Stavka", "Cena", "Količina", "Ukupno"};
    private String parameter = "";

    public TableModelExpense() {
        try {
            expenses = Controller.getInstance().getAllExpenses();
        } catch (Exception ex) {
            Logger.getLogger(TableModelService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public TableModelExpense(ArrayList<Expense> searchedExpenses) {
        expenses=searchedExpenses;
    }

    @Override
    public int getRowCount() {
        return expenses.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Expense expense = expenses.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return expense.getDate();
            case 1:
                return expense.getItem();
            case 2:
                return expense.getPrice();
            case 3:
                return expense.getAmount();
            case 4:
                return expense.getPrice() * expense.getAmount();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }

    public Expense getSelectedExpense(int row) {
        return expenses.get(row);
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
        refreshTable();
    }

    public void refreshTable() {
        try {
            expenses = Controller.getInstance().getAllExpenses();
//            if (!parameter.equals("")) {
//                ArrayList<Expense> newList = new ArrayList<>();
//                for (Expense expense : expenses) {
//                    if (expense.getItem().toLowerCase().contains(parameter.toLowerCase())) {
//                        newList.add(expense);
//                    }
//                }
//                expenses = newList;
           // }
            fireTableDataChanged();
        } catch (Exception ex) {
            System.out.println("prazna lista");
            //ex.printStackTrace();
        }
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }
}
