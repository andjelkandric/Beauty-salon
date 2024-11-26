/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Client;
import domain.ClientCard;
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
public class TableModelClientCardState extends AbstractTableModel {

    private ArrayList<ClientCard> clientCards;
    private String[] columns = {"Usluga", "Količina"};
    private Map<String, Double> uniqueServices;

    public TableModelClientCardState(Client client) {
        try {
            clientCards = Controller.getInstance().getAllClientCard(client);
            uniqueServices = new HashMap<>();

            if (clientCards.size() > 0) {
                for (ClientCard cc : clientCards) {
                    String serviceName = cc.getService().getName();
                    double amount = cc.getAmount();

                    //uniqueServices.put(serviceName, uniqueServices.getOrDefault(serviceName, 0.0) + amount);
                      uniqueServices.put(serviceName, uniqueServices.getOrDefault(serviceName, amount));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TableModelClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return uniqueServices.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (uniqueServices.size() > 0) {
            String serviceName = (String) uniqueServices.keySet().toArray()[rowIndex];
            switch (columnIndex) {
                case 0:
                    return serviceName;
                case 1:
                    return uniqueServices.get(serviceName);
                default:
                    return null;
            }
        }
        return null;

    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }

    public ClientCard getSelectedClientCard(int row) {
        if (row < 0 || row >= uniqueServices.size()) {
            throw new IllegalArgumentException("Invalid row index: " + row);
        }

        String serviceName = (String) uniqueServices.keySet().toArray()[row];
        for (ClientCard cc : clientCards) {
            if (cc.getService().getName().equals(serviceName)) {
                return cc;
            }
        }
        return null; // ili bacite izuzetak, zavisno od vašeg scenarija
    }

    public ArrayList<ClientCard> getClientCards() {
        return clientCards;
    }
}
