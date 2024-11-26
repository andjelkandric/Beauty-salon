/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Client;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import logic.Controller;

/**
 *
 * @author Admin
 */
public class TableModelClient extends AbstractTableModel {

    private ArrayList<Client> clients;
    private String[] columns = {"Ime", "Prezime", "Broj telefona", "Email"};
    private String parameter = "";

    public TableModelClient() {
        try {
            clients = Controller.getInstance().getAllClients();
        } catch (Exception ex) {
            Logger.getLogger(TableModelClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public int getRowCount() {
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Client c = clients.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getFirstname();
            case 1:
                return c.getLastname();
            case 2:
                return c.getPhoneNumber();
            case 3:
                return c.getEmail();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }

    public Client getSelectedClient(int row) {
        return clients.get(row);
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
        refreshTable();
    }

    public void refreshTable() {
        try {
            clients = Controller.getInstance().getAllClients();
            if (!parameter.equals("")) {
                ArrayList<Client> newList = new ArrayList<>();
                for (Client client : clients) {
                    if (client.getFirstname().toLowerCase().contains(parameter.toLowerCase()) || client.getLastname().toLowerCase().contains(parameter.toLowerCase())) {
                        newList.add(client);
                    }
                }
                clients = newList;
            }
            fireTableDataChanged();
        } catch (Exception ex) {
            System.out.println("prazna lista");
            //ex.printStackTrace();
        }
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

}
