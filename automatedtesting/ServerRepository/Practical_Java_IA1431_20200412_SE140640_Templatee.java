/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student;

/**
 *
 * @author star
 */
public class Templatee {

    private String id;
    private String name;
    private int size;
    private double price;

    public Templatee(String id, String name, int size, double price) {
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

    public void print() {
        String tmp = String.format("|SE1431|%4s|%10s|%2d|%.2f|", id, name, size, price);
        System.out.println(tmp);
    }
}
