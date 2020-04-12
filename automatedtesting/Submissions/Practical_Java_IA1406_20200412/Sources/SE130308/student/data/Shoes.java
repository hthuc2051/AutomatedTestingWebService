package com.practicalexam.student.data;

public class Shoes implements Comparable<Shoes> {

    protected String code;
    protected String name;
    protected String model;
    protected int size;
    protected double price;

    public Shoes(String code, String name, String model, int size, double price) {
        this.code = code;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Shoes{" + "code=" + code + ", name=" + name + ", model=" + model + ", size=" + size + ", price=" + price + '}';
    }

    
    public void showInfo() {
        System.out.printf("|IA1406|%-10s|%-20s|%4d|%6.2f|\n|", code, model, size, price);
    }

    @Override
    public int compareTo(Shoes that) {
        return this.code.compareToIgnoreCase(that.code);
    }

}
