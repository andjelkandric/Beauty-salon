/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Service;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import logic.Controller;

/**
 *
 * @author Admin
 */
public class TableModelService extends AbstractTableModel{
    
    private ArrayList<Service> services;
    private String[] columns = {"Usluga", "Cena"};
    private String parameter = "";

    public TableModelService() {
        
        try {
            services = Controller.getInstance().getAllServices();
        } catch (Exception ex) {
            Logger.getLogger(TableModelService.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    @Override
    public int getRowCount() {
        return services.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Service service = services.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return service.getName();
            case 1:
                return service.getPrice();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }

    public Service getSelectedService(int row) {
        return services.get(row);
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
        refreshTable();
    }

    public void refreshTable() {
        try {
            services = Controller.getInstance().getAllServices();
            if (!parameter.equals("")) {
                ArrayList<Service> newList = new ArrayList<>();
                for (Service service : services) {
                    if (service.getName().toLowerCase().contains(parameter.toLowerCase())) {
                        newList.add(service);
                    }
                }
                services = newList;
            }
            fireTableDataChanged();
        } catch (Exception ex) {
            System.out.println("prazna lista");
            //ex.printStackTrace();
        }
    }

    public ArrayList<Service> getServices() {
        return services;
    }
}
