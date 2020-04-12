/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.data;

import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Shoe {

    String code, model;
    int size;
    double price;

    public Shoe(String code, String model, int size, double price) {
        this.code = code;
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
        return "Shoe{" + "code=" + code + ", model=" + model + ", size=" + size + ", price=" + price + '}';
    }

    public void input(Scanner s) {
        System.out.print("Input new shoe model: ");
        model = s.nextLine();
        System.out.print("Input new shoe price: ");
        price = Double.parseDouble(s.nextLine());
    }

    public void showProfile() {
        System.out.printf("|IA1406|%-10s|%-10s|%4d|%4.1f|\n",
                code, model, size, price);
    }

    public static Comparator<Shoe> sortShoeByModel = new Comparator<Shoe>() {
        @Override
        public int compare(Shoe o1, Shoe o2) {
            if (o1.getModel().compareToIgnoreCase(o2.getModel()) > 1) {
                return 1;
            } else if (o1.getModel().compareToIgnoreCase(o2.getModel()) < 1) {
                return -1;
            } else {
                return 0;
            }
        }
    };
}
