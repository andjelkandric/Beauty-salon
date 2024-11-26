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
public class Expense extends AbstractDomainObject{
    private Long expenseId;
    private Date date;
    private String item;
    private double amount;
    private double price;
    private Employee employee;

    public Expense() {
    }

    public Expense(Long expenseId, Date date, String item, double amount, double price, Employee employee) {
        this.expenseId = expenseId;
        this.date = date;
        this.item = item;
        this.amount = amount;
        this.price = price;
        this.employee = employee;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String getTableName() {
        return "expense";
    }

    @Override
    public String getColumnsForInsert() {
        return "(date, item, amount, price, employeeId)";
    }

    @Override
    public String getParamsForInsert() {
       return "'" + new java.sql.Date(date.getTime())+ "', '" + item +"', '" + amount + "', '" + price + "', '" + employee.getEmployeeId() + "'";
    }

    @Override
    public String getParamsForUpdate() {
         return " date = '" + new java.sql.Date(date.getTime())+ "', item = '" + item + "', amount = '" + amount + "', price = '" + price + "', employeeId = '" + employee.getEmployeeId() + "'";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet resultSet) throws SQLException {
        ArrayList<AbstractDomainObject> list = new ArrayList<>();

        while (resultSet.next()) {
            Employee e = new Employee(resultSet.getLong("employeeId"), resultSet.getString("firstname"),
                    resultSet.getString("lastname"), resultSet.getString("username"), resultSet.getString("password"));
            
            Expense ex=new Expense(resultSet.getLong("expenseId"), resultSet.getDate("date"), resultSet.getString("item"),
                    resultSet.getDouble("amount"), resultSet.getDouble("price"), e);
            list.add(ex);
        }
        resultSet.close();
        return list;
    }

    @Override
    public String alias() {
        return "ex";
    }

    @Override
    public String join() {
        return "JOIN EMPLOYEE E ON (E.EMPLOYEEID = EX.EMPLOYEEID)";
    }

    @Override
    public String condition() {
        return "";
    }

    @Override
    public String valueForPK() {
       return "expenseId = "+expenseId; 
    }
    
    
}
