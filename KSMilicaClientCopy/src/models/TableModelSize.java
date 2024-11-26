/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Client;
import domain.Size;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import logic.Controller;

/**
 *
 * @author Admin
 */
public class TableModelSize extends AbstractTableModel {

    private ArrayList<Size> sizes;
    private String[] columns = {"Datum merenja", "Visina", "Težina", "Grudi", "Struk", "Kukovi"};
    private long rb = 0;

    public TableModelSize(Client client) {
        try {
            sizes = Controller.getInstance().getAllSizes(client);
        } catch (Exception ex) {
            Logger.getLogger(TableModelService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public int getRowCount() {
        return sizes.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Size size = sizes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return size.getDate();
            case 1:
                return size.getHeight();
            case 2:
                return size.getWeight();
            case 3:
                return size.getChestCircumference();
            case 4:
                return size.getWaistSize();
            case 5:
                return size.getHipCircumference();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }


    public ArrayList<Size> getSizes() {
        return sizes;
    }

    public void addSize(Size size) {
        rb=sizes.size();
        size.setSizeId(++rb);
        sizes.add(size);
        fireTableDataChanged();
    }
    public long getId(){
        return sizes.size()+1;
    }
}
