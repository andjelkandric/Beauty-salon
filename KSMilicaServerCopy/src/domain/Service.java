/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Service extends AbstractDomainObject{
    private Long serviceId;
    private String name;
    private double price;

    public Service() {
    }

    public Service(Long serviceId, String name, double price) {
        this.serviceId = serviceId;
        this.name = name;
        this.price = price;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getTableName() {
        return "service";
    }

    @Override
    public String getColumnsForInsert() {
        return "(name, price)";
    }

    @Override
    public String getParamsForInsert() {
        return "'" + name + "', '" + price + "'";
    }

    @Override
    public String getParamsForUpdate() {
        return " name = '" + name + "', price = '" + price + "'";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet resultSet) throws SQLException {
        ArrayList<AbstractDomainObject> list = new ArrayList<>();

        while (resultSet.next()) {
            Service service = new Service(resultSet.getLong("serviceId"), resultSet.getString("name"),
                    resultSet.getDouble("price"));
            list.add(service);
        }
        resultSet.close();
        return list;
    }

    @Override
    public String alias() {
        return "s";
    }

    @Override
    public String join() {
       return "";
    }

    @Override
    public String condition() {
        return "";
    }

    @Override
    public String valueForPK() {
        return "serviceId = "+serviceId;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
    
    
}
