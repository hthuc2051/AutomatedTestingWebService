/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.data;

/**
 *
 * @author kietp
 */
public class Shoes {

    private String code;
    private String model;
    private double size; // int size 
    private double price;

    public Shoes(String code, String model, double size, double price) {
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

    @Override
    public String toString() {
        return "Shoes{" + "code=" + code + ", model=" + model + ", size=" + size + ", price=" + price + '}';
    }

    public void show() {
        System.out.printf("|IA1406|%2s|%2s|%2.1f|%6.0f|\n", code, model, size, price);
    }
}
