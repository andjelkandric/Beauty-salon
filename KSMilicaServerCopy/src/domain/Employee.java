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
public class Employee extends AbstractDomainObject{
    private Long employeeId;
    private String firstname;
    private String lastname;
    private String username;
    private String password;

    public Employee() {
    }

    public Employee(Long employeeId, String firstname, String lastname, String username, String password) {
        this.employeeId = employeeId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getTableName() {
        return "employee";
    }

    @Override
    public String getColumnsForInsert() {
        return "(firstname, lastname, username, password)";
    }

    @Override
    public String getParamsForInsert() {
        return "'" + firstname + "', '" + lastname + "', "
                + "'" + username + "', '" + password + "'";
    }

    @Override
    public String getParamsForUpdate() {
        return " password = '" + password + "'";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet resultSet) throws SQLException {
        ArrayList<AbstractDomainObject> list = new ArrayList<>();

        while (resultSet.next()) {
            Employee employee = new Employee(resultSet.getLong("employeeId"), resultSet.getString("firstname"),
                    resultSet.getString("lastname"), resultSet.getString("username"), resultSet.getString("password"));
            list.add(employee);
        }
        resultSet.close();
        return list;
    }

    @Override
    public String alias() {
        return "e";
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
        return "employeeId = "+employeeId;
    }
    
    
}
