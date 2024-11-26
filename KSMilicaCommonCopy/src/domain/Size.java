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
public class Size extends AbstractDomainObject {

    private Long sizeId;
    private Client client;
    private Date date;
    private double waistSize;
    private double hipCircumference;
    private double chestCircumference;
    private double weight;
    private double height;

    public Size() {
    }

    public Size(Long sizeId, Client client, Date date, double waistSize, double hipCircumference, double chestCircumference, double weight, double height) {
        this.sizeId = sizeId;
        this.client = client;
        this.date = date;
        this.waistSize = waistSize;
        this.hipCircumference = hipCircumference;
        this.chestCircumference = chestCircumference;
        this.weight = weight;
        this.height = height;
    }

    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getWaistSize() {
        return waistSize;
    }

    public void setWaistSize(double waistSize) {
        this.waistSize = waistSize;
    }

    public double getHipCircumference() {
        return hipCircumference;
    }

    public void setHipCircumference(double hipCircumference) {
        this.hipCircumference = hipCircumference;
    }

    public double getChestCircumference() {
        return chestCircumference;
    }

    public void setChestCircumference(double chestCircumference) {
        this.chestCircumference = chestCircumference;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String getTableName() {
        return "size";
    }

    @Override
    public String getColumnsForInsert() {
        return "(clientId, sizeId, date, waistSize, hipCircumference, chestCircumference, weight, height)";
    }

    @Override
    public String getParamsForInsert() {
        return "'" + client.getClientId() + "', '" + sizeId + "', '" + new java.sql.Date(date.getTime()) + "', '" + waistSize + "', '" + hipCircumference + "', '" + chestCircumference + "', '" + weight + "', '" + height + "'";
    }

    @Override
    public String getParamsForUpdate() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet resultSet) throws SQLException {
        ArrayList<AbstractDomainObject> list = new ArrayList<>();

        while (resultSet.next()) {
            Client c = new Client(resultSet.getLong("clientId"), resultSet.getString("firstname"), resultSet.getString("lastname"),
                    resultSet.getString("phoneNumber"), resultSet.getString("email"), null);

            Size size = new Size(resultSet.getLong("sizeId"), c, resultSet.getDate("date"), resultSet.getDouble("waistSize"),
                    resultSet.getDouble("hipCircumference"), resultSet.getDouble("chestCircumference"), resultSet.getDouble("weight"),resultSet.getDouble("height"));
            list.add(size);
        }
        resultSet.close();
        return list;
    }

    @Override
    public String alias() {
        return "sz";
    }

    @Override
    public String join() {
        return " JOIN CLIENT C ON (C.CLIENTID=SZ.CLIENTID) ";
    }

    @Override
    public String condition() {
        return "WHERE C.CLIENTID = " + client.getClientId();
    }

    @Override
    public String valueForPK() {
        return "clientId = " + client.getClientId();
    }

}
