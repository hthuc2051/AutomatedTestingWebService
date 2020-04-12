/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.data;

/**
 *
 * @author trant
 */
public class Shoes {

    private String code;
    private String model;
    private int size;
    private double price;

    public Shoes(String code, String name, int size, double price) {
        this.code = code;
        this.model = name;
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

    public void setModel(String model) {
        this.model = model;
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

    public void showDetail() {
        System.out.printf("|IA1406|%10s|%10s|%5d|%6.2f|",
                code, model, size, price);
    }
}
