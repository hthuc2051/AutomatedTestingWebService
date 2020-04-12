/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.data;

/**
 *
 * @author Nam
 */
public class Cake {

    private String Class;
    protected String id;
    private String name;
    private int size;
    private double price;

    public Cake(String id, String name, int size, double price) {

        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
    }

//    public String getClass() {
//        return Class;
//    }
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

    public void setClass(String Class) {
        this.Class = Class;
    }

//    public void setId(String id) {
//        this.id = id;
//    }
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
        return "Cake{" + "Class=" + Class + ", id=" + id + ", name=" + name + ", size=" + size + ", price=" + price + '}';
    }

    public void showProFile() {
        String msg;
        msg = String.format("|SE1431|%13s|%-15s|%5d|%10f|", id, name, size, price);
        System.out.println("msg");
    }

}
