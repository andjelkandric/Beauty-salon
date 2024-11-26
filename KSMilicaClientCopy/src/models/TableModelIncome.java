/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Appointment;
import domain.Income;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import logic.Controller;

/**
 *
 * @author Admin
 */
public class TableModelIncome extends AbstractTableModel {

    private ArrayList<Income> incomes;
    private String[] columns = {"Datum", "Usluga", "Prihod"};
    public TableModelIncome(ArrayList<Income> incomes) {
        this.incomes = incomes;
    }

    public TableModelIncome() {
        try {
            incomes = Controller.getInstance().getAllIncomes();
        } catch (Exception ex) {
            Logger.getLogger(TableModelAllAppointment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return incomes.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Income i = incomes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return i.getDate();
            case 1:
                return i.getServiceId().getName();
            case 2:
                return i.getMoney();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }
}
