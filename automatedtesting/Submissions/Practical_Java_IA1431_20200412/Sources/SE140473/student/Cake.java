/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student;

/**
 *
 * @author Long
 */
public class Cake {

    private String name;
    private String id;
    private double price;
    private int size;

    public Cake(String name, String id, int size, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Cake{" + "name = " + name + ", id = " + id + ", price = " + price + ", size = " + size + '}';
    }

    public void showProfile() {
        System.out.printf("|SE1431|%-8s|%-8s|%-8d|%-8f \n", id, name, size, price); //Added
    }

    public void pain() {
        System.out.printf("|Cake|SE1431|%-8s|%-8s|%-8s|\n", name, id, price, size);
    }
}
