/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class ClientCard extends AbstractDomainObject {

    private Long clientCardId;
    private Date date;
    private double payed;
    private double amount;
    private Service service;
    private Client client;

    public ClientCard() {
    }

    public ClientCard(Long clientCardId, Date date, double payed, double amount, Service service, Client client) {
        this.clientCardId = clientCardId;
        this.date = date;
        this.payed = payed;
        this.amount = amount;
        this.service = service;
        this.client = client;
    }

    public Long getClientCardId() {
        return clientCardId;
    }

    public void setClientCardId(Long clientCardId) {
        this.clientCardId = clientCardId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPayed() {
        return payed;
    }

    public void setPayed(double payed) {
        this.payed = payed;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String getTableName() {
        return "clientcard";
    }

    @Override
    public String getColumnsForInsert() {
        return "(date, payed, amount, serviceId, clientId)";
    }

    @Override
    public String getParamsForInsert() {
        return "'" + new java.sql.Date(date.getTime()) + "', '" + payed + "', '" + amount + "', '" + service.getServiceId() + "', '" + client.getClientId() + "'";
    }

    @Override
    public String getParamsForUpdate() {
        return " amount = '" + amount + "'";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet resultSet) throws SQLException {
        ArrayList<AbstractDomainObject> list = new ArrayList<>();

        while (resultSet.next()) {
            Client c = new Client(resultSet.getLong("clientId"), resultSet.getString("firstname"),
                    resultSet.getString("lastname"), resultSet.getString("phoneNumber"), resultSet.getString("email"), null);

            Service s = new Service(resultSet.getLong("serviceId"), resultSet.getString("name"),
                    resultSet.getDouble("price"));

            ClientCard cc = new ClientCard(resultSet.getLong("clientCardId"), resultSet.getDate("date"), resultSet.getDouble("payed"),
                    resultSet.getDouble("amount"), s, c);
            list.add(cc);
        }
        resultSet.close();
        return list;
    }

    @Override
    public String alias() {
        return "cc";
    }

    @Override
    public String join() {
        return "JOIN CLIENT C ON (C.CLIENTID = CC.CLIENTID) "
                + "JOIN SERVICE S ON (S.SERVICEID = CC.SERVICEID) ";
    }

    @Override
    public String condition() {
        return "WHERE C.CLIENTID = " + client.getClientId();
    }

    @Override
    public String valueForPK() {
        return "clientCardId = " + clientCardId;
    }


}
