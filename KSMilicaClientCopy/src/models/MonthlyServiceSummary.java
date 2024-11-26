/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Admin
 */
import java.time.YearMonth;

public class MonthlyServiceSummary {
    private YearMonth month;
    private String serviceName;
    private int totalAmount;

    public MonthlyServiceSummary(YearMonth month, String serviceName, int totalAmount) {
        this.month = month;
        this.serviceName = serviceName;
        this.totalAmount = totalAmount;
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}

