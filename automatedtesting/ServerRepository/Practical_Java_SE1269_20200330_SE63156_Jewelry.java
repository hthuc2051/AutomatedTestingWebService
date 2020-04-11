/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.data;

/**
 *
 * @author ADMIN
 */
public class Jewelry implements Comparable<Jewelry> {

    private String code;
    private String name;
    private String brand;
    private double price;

    public Jewelry(String code, String name, String brand, double price) {
        this.code = code;
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    public void showDetail() {
        String msg = String.format("|SE1431|%-5s|%10s|%5s|%5f|", code, name, brand, price);
        System.out.println(msg);
    }

    @Override
    public int compareTo(Jewelry o) {
        if (name.compareTo(o.name) == 0) {
            return 0;
        } else if (name.compareTo(o.name) > 0) {
            return 1;
        } else {
            return -1;
        }
    }
}
