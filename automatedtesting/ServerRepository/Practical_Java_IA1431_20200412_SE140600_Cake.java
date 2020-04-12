/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student;

/**
 *
 * @author cutay
 */
public class Cake {

    private String id, name;
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

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Cake{" + "id=" + id + ", name=" + name + ", size=" + size + ", price=" + price + '}';
    }

    public void showCake() {
        System.out.printf("|SE1431|%20s|%20s|%6.2d|%6.2f|\n",
                id, name, size, price);
    }
}
