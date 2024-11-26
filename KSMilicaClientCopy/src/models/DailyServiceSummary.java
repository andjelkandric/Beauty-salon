/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class DailyServiceSummary {
     private LocalDate date;
    private String serviceName;
    private int totalAmount;

    public DailyServiceSummary(LocalDate date, String serviceName, int totalAmount) {
        this.date = date;
        this.serviceName = serviceName;
        this.totalAmount = totalAmount;
    }

    // Getters
    public LocalDate getDate() {
        return date;
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "DailyServiceSummary{"
                + "date=" + date
                + ", serviceName='" + serviceName + '\''
                + ", totalAmount=" + totalAmount
                + '}';
    }

}
