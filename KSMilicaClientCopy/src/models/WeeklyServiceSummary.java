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
public class WeeklyServiceSummary {
    private LocalDate weekStartDate;
    private String serviceName;
    private int totalAmount;

    public WeeklyServiceSummary(LocalDate weekStartDate, String serviceName, int totalAmount) {
        this.weekStartDate = weekStartDate;
        this.serviceName = serviceName;
        this.totalAmount = totalAmount;
    }

    // Getters and toString() (optional)
    public LocalDate getWeekStartDate() {
        return weekStartDate;
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "WeeklyServiceSummary{"
                + "weekStartDate=" + weekStartDate
                + ", serviceName='" + serviceName + '\''
                + ", totalAmount=" + totalAmount
                + '}';
    }
}
