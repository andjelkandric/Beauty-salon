/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Income extends AbstractDomainObject {

    private Long incomeId;
    private LocalDate date;
    private double money;
    private Service serviceId;

    public Income() {
    }

    public Income(Long incomeId, LocalDate date, double money, Service serviceId) {
        this.incomeId = incomeId;
        this.date = date;
        this.money = money;
        this.serviceId = serviceId;
    }

    public Service getServiceId() {
        return serviceId;
    }

    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
    }

    public Long getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Long incomeId) {
        this.incomeId = incomeId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String getTableName() {
        return "income";
    }

    @Override
    public String getColumnsForInsert() {
        return "(date, money, serviceId)";
    }

    @Override
    public String getParamsForInsert() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);
        return "'" + formattedDate + "', '" + money + "', '" + serviceId.getServiceId() + "'";
    }

    @Override
    public String getParamsForUpdate() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet resultSet) throws SQLException {
        ArrayList<AbstractDomainObject> list = new ArrayList<>();

        while (resultSet.next()) {
            Service s = new Service(resultSet.getLong("serviceId"), resultSet.getString("name"), resultSet.getDouble("price"));
            LocalDate date = resultSet.getDate("date").toLocalDate();
            Income income = new Income(resultSet.getLong("incomeId"), date, resultSet.getDouble("money"), s);
            list.add(income);
        }
        resultSet.close();
        return list;
    }

    @Override
    public String alias() {
        return "i";
    }

    @Override
    public String join() {
        return "JOIN SERVICE S ON (S.SERVICEID=I.SERVICEID)";
    }

    @Override
    public String condition() {
        return "";
    }

    @Override
    public String valueForPK() {
        return "incomeId = " + incomeId;
    }

}
