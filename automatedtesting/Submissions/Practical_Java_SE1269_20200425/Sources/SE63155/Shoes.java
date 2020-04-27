/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.data;

/**
 *
 * @author Win 10
 */
public class Shoes  implements Comparable<Shoes>{

    private String code;
    private String model;
    int size;
   double price;

    public Shoes(String code, String model, int size,double price) {
        this.code = code;
        this.model = model;
        this.size = size;
        this.price = price;
    }

    public Shoes() {
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

    public void showProfile() {
        System.out.printf("|IA1406|%-8s|%-20s|%4d|%9.1f|\n", code, model, size, price);
    }

    @Override
    public int compareTo(Shoes o) {
        return this.getModel().compareTo(o.getModel());
    }
}
