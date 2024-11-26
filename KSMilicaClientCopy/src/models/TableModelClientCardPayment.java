/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Client;
import domain.ClientCard;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import logic.Controller;

/**
 *
 * @author Admin
 */
public class TableModelClientCardPayment extends AbstractTableModel {

    private ArrayList<ClientCard> clientCards;
    private String[] columns = {"Usluga", "Datum uplate", "Uplaćena suma"};

    public TableModelClientCardPayment(Client client) {
        try {
            clientCards = Controller.getInstance().getAllClientCard(client);
        } catch (Exception ex) {
            Logger.getLogger(TableModelClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public int getRowCount() {
        return clientCards.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ClientCard cc = clientCards.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cc.getService().getName();
            case 1:
                return cc.getDate();
            case 2:
                return cc.getPayed();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }

    public ClientCard getSelectedClientCard(int row) {
        return clientCards.get(row);
    }

    public ArrayList<ClientCard> getClientCards() {
        return clientCards;
    }
}
