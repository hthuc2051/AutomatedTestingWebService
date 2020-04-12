/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.data;

/**
 *
 * @author DELL
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

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Shoes{" + "code=" + code + ", model=" + model + ", size=" + size + ", price=" + price + '}';
    }

    public void showAll() {
        System.out.printf("|IA1406|%20s|%20s|%-2d|%10.2f|\n", code, model, size, price);
    }

}
