/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student;

/**
 *
 * @author Win 10
 */
public class Cake {

    private String id;
    private String name;
    private double price;
    private int size;

    public Cake(String id, String name, int size, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        // System.out.printf("|SE1431|%-9s|%-15s|%4.1f|%8.1f|\n ", id, name, size, price);
        System.out.printf("|SE1431|%-9s|%-15s|%4d|%8.1f|\n ", id, name, size, price);

    }

}
