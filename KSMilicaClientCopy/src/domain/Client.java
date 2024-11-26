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
public class Client extends AbstractDomainObject {

    private Long clientId;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String email;
    private ArrayList<Size> size;

    public Client() {
    }

    public Client(Long clientId, String firstname, String lastname, String phoneNumber, String email, ArrayList<Size> size) {
        this.clientId = clientId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.size = size;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Size> getSize() {
        return size;
    }

    public void setSize(ArrayList<Size> size) {
        this.size = size;
    }

    @Override
    public String getTableName() {
        return "client";
    }

    @Override
    public String getColumnsForInsert() {
        return "(firstname, lastname, phoneNumber, email)";
    }

    @Override
    public String getParamsForInsert() {
        return "'" + firstname + "', '" + lastname + "', "
                + "'" + phoneNumber + "', '" + email + "'";
    }

    @Override
    public String getParamsForUpdate() {
        return " phoneNumber = '" + phoneNumber + "', email = '" + email + "'";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet resultSet) throws SQLException {
        ArrayList<AbstractDomainObject> list = new ArrayList<>();

        while (resultSet.next()) {
            Client client = new Client(resultSet.getLong("clientId"), resultSet.getString("firstname"),
                    resultSet.getString("lastname"), resultSet.getString("phoneNumber"), resultSet.getString("email"),null);
            list.add(client);
        }
        resultSet.close();
        return list;
    }

    @Override
    public String alias() {
        return "c";
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
        return "clientId = "+clientId;
    }

}
