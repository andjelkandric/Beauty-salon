/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Admin
 */
class ExpenseSummary {
    private String itemName;
    private double totalCost;

    public ExpenseSummary(String itemName, double totalCost) {
        this.itemName = itemName;
        this.totalCost = totalCost;
    }

    public String getItemName() {
        return itemName;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
