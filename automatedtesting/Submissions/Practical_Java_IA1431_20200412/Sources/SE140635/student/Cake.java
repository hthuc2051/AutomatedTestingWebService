/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student;

/**
 *
 * @author ACER
 */
public class Cake {

    private String id;
    private String name;
    private int size;
    private double price;

    public Cake(String id, String name, int size, double price) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
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

    @Override
    public String toString() {
        return "SE140635{" + "id=" + id + ", name=" + name + ", size=" + size + ", price=" + price + '}';
    }

    public void showProfile() {
        System.out.printf("|SE1431|%-10s|%-10s|%1d|%4.1f|\n", id, name, size, price);

    }
}
