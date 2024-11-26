/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import ClientForm.SaleService;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import logic.Controller;

/**
 *
 * @author Admin
 */
public class TableModelBill extends AbstractTableModel {

    private ArrayList<Bill> bills;
    private String[] columns = {"Usluga", "Cena", "Količina", "Ukupno"};
    private String parameter = "";
    private double summary;

    public TableModelBill() {
        try {
            bills = new ArrayList<>();
            summary = 0;
        } catch (Exception ex) {
            Logger.getLogger(TableModelService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return bills.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Bill bill = bills.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return bill.getService().getName();
            case 1:
                return bill.getPrice();
            case 2:
                return bill.getAmount();
            case 3:
                return bill.getSum();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }

    public Bill getSelectedBill(int row) {
        return bills.get(row);
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public void setBills(ArrayList<Bill> bills) {
        this.bills = bills;
        fireTableDataChanged();
    }

    public void addItem(Bill bill) {
        for (Bill b : bills) {
            if (b.getService().getServiceId().equals(bill.getService().getServiceId())) {
                JOptionPane.showMessageDialog(null, "Ova usluga je već dodata na račun.");
                return;
            }
        }
        bills.add(bill);
        summary += bill.getSum();
        fireTableDataChanged();
    }

    public double getSummary() {
        return summary;
    }

    public void deleteItem(int row) {
        bills.remove(row);
        fireTableDataChanged();
    }

    public void deleteAllItems() {
        bills = new ArrayList<>();
        fireTableDataChanged();
    }

}
