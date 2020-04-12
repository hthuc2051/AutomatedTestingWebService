/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.data;

/**
 *
 * @author Admin
 */
public class Shoes {

    private String code;
    private String model;
    private int size;
    private double price;

    public Shoes(String code, String model, int size, double price) {
        this.code = code;
        this.model = model;
        this.size = size;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String modal) {
        this.model = modal;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void showAll() {
        System.out.printf("|IA1406|%-10s|%-10s|%3d|%10.1f|", code, model, size, price);
    }
}
