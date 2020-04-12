package com.practicalexam.student;

import com.practicalexam.student.data.Shoes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
    private ArrayList<Shoes> sh = new ArrayList();
    //EndList - do not remove this comment!!!
    private static Scanner scannerObj = new Scanner(System.in);

    public void add() {
        // Print the object details after adding
        String code, model;
        int size;
        double price;
        int pos;
        do {
            code = MyToys.getById("Input code(SXXXXX): ", "Enter id is required, X is digit", "^[S|s]\\d{5}$");
            pos = searchById(code);
            if (pos >= 0) {
                System.out.println("This code is exist.Please input another one!");
            }
        } while (pos != -1);
        model = MyToys.getString("Input model: ", "Enter model is required!!!");
        price = MyToys.getDouble("Input price: ", "Please input from 1_000_000 to 100_000_000", 1_000_000, 100_000_000);
        size = MyToys.getInteger("Input size: ", "Enter model is required!!!", 34, 45);
        //Not done yet
        System.out.println("Add a shoes successfully");
    }

    public boolean checkDuplicatedId(String id) {
        // Your code here

        return true;
    }

    public void update() {
        // Print the object details after updating name/model and price
        String code, model, price, tmp; // declare without using
        Shoes x;
        code = MyToys.getString("Input code: ", "Enter id is required!!!");
        x = searchObjectById(code);
        if (x == null) {
            System.out.println("Not found");
        } else {
            System.out.println("This is info before update");
        }
        x.showInfo();
        System.out.println("You must be required input new name");
        tmp = MyToys.getString("Input name: ", "Enter name is required!!!");
        x.getName();
        System.out.println("You must be required input new model");
        tmp = MyToys.getString("Input model: ", "Enter name is required!!!");
        x.getModel();
        System.out.println("You must be required input new price");
        tmp = MyToys.getString("Input price: ", "Enter name is required!!!");
        x.getPrice();
        System.out.println("Update name watch successfully");
        x.showInfo();
    }

    public void search() {
        // Print the object details after searching
        String code;
        Shoes x;
        code = MyToys.getString("Input id: ", "Enter name is required!!!");
        x = searchObjectById(code);
        if (x == null) {
            System.out.println("Nothing here");
        } else {
            System.out.println("Which watch do you want?");
            x.showInfo();
        }
    }

    public Shoes searchObjectById(String code) {
        if (sh.isEmpty()) {
            return null;
        }
        for (int i = 0; i < sh.size(); i++) {
            if (sh.get(i).getCode().equalsIgnoreCase(code)) {
                return sh.get(i);
            }
        }
        return null;
    }

    public int searchById(String code) {
        int pos;
        if (sh.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < sh.size(); i++) {
            if (sh.get(i).getCode().equalsIgnoreCase(code)) {
                return i;
            }

        }
        return -1;

    }

    public void remove() {
        // Print the list after removing
        int pos;
        String code;
        code = MyToys.getString("Input code: ", "Enter id is required!!!");
        pos = searchById(code);
        if (pos == -1) {
            System.out.println("Not found");
        } else {
            Shoes getShoes = sh.get(pos); //Added
            sh.remove(getShoes);
            System.out.println("Remove successfully");
        }
        for (int i = 0; i < sh.size(); i++) {
            sh.get(i).showInfo();
        }

    }

    public void sort() {
        // Print the object details after sorting
        if (sh.isEmpty()) {
            System.out.println("Not found");
            return;
        }
        Collections.sort(sh);
        System.out.println("----------------------");
        System.out.println("The list Shoes");
        String h = String.format("|%-15s|%-10s|%-10s|%4d|%6.2f|/n", "CODE", "NAME", "MODEL", "SIZE", "PRICE");
        System.out.println(h);
        for (int i = 0; i < sh.size(); i++) {
            sh.get(i).showInfo();
        }

    }
}
