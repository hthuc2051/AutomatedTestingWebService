/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.data;

/**
 *
 * @author trand
 */
public class Shoe {

    String code;
    String model;
    double price;
    int size;

    public Shoe(String code, String model, double price, int size) {
        this.code = code;
        this.model = model;
        this.price = price;
        this.size = size;
    }

    public String getCode() {
        return code;
    }

//    public void setCode(String code) {
//        this.code = code;
//    }
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void showProfile() {
        System.out.printf("|IA1406|%5s|%15s|%2d|%4.1f|\n", code, model, size, price);
    }
}
