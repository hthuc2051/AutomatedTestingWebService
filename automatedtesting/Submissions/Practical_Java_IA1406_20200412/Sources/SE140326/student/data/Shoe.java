/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.data;

/**
 *
 * @author MSI-PC
 */
public class Shoe {

    private String code;
    private String model;
    private double size;
    private double price;

    public Shoe(String code, String model, double size, double price) {
        this.code = code;
        this.model = model;
        this.size = size;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void showProfile() {
        System.out.printf("|IA1406" + "|%-10s|%-15s|%6.2f|%2.2f|\n", code, model, size, price);
    }
}
