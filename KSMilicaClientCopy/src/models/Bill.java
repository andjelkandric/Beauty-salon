/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domain.Service;

/**
 *
 * @author Admin
 */
public class Bill {
    private double amount;
    private double price;
    private Service service;
    private double sum;

    public Bill() {
    }

    public Bill(double amount, double price, Service service, double sum) {
        this.amount = amount;
        this.price = price;
        this.service = service;
        this.sum = sum;
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

}
