package com.practicalexam.student.data;

public class Shose {

    private String code;
    private String model;
    private int size;
    private double price;
    private int amount;

    public Shose(String code, String model, int size, double price, int amount) {
        this.code = code;
        this.model = model;
        this.size = size;
        this.price = price;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    @Override
    public String toString() {
        return "|IA1406|" + "code=" + code + "|model=" + model + "|size=" + size + "|price=" + price + "|";
    }

}
