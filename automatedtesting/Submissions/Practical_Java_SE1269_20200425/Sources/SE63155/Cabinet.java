package com.practicalexam.student;

import com.practicalexam.student.data.Shoes;
import com.practicalexam.student.util.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
static     ArrayList<Shoes> shoeses = new ArrayList<>();
    //EndList - do not remove this comment!!!
    String code, model;
    int size;
    double price;
    private int count;
    Scanner scannerObj = new Scanner(System.in);

    public void add() {
        // Print the object details after adding
        Shoes shoes = Utils.readFile("add");
        shoeses.add(shoes);
        Shoes shoes2 = Utils.readFile("add2");
        shoeses.add(shoes2);
        showAll();
    }

    public boolean checkDuplicatedId(String id) {
        // Your code here
        Shoes shoes = Utils.readFile("remove");
        for (int i = 0; i < shoeses.size(); i++) {
            if (shoeses.get(i).getCode().equals(shoes.getCode())) {
                shoeses.remove(shoeses.get(i));
            }
        }
        return true;
    }

    public void update() {
        // Print the object details after updating name/model and price
        Shoes shoes = Utils.readFile("update");
        for (int i = 0; i < shoeses.size(); i++) {
            if (shoeses.get(i).getCode().equals(shoes.getCode())) {
                shoeses.get(i).setModel(shoes.getModel());
                shoeses.get(i).setPrice(shoes.getPrice());
                shoeses.get(i).setSize(shoes.getSize());
            }
        }
        showAll();
    }

    public void search() {
        // Print the object details after searching
        Shoes shoes = Utils.readFile("search");
        for (int i = 0; i < shoeses.size(); i++) {
            if (shoeses.get(i).getCode().equals(shoes.getCode())) {
                shoeses.get(i).showProfile();
            }
        }
    }

    public void remove() {
        // Print the list after removing
        Shoes shoes = Utils.readFile("remove");
        shoeses.add(shoes);
        for (int i = 0; i < shoeses.size(); i++) {
            if (shoeses.get(i).getCode().equals(shoes.getCode())) {
                shoeses.remove(shoeses.get(i));
            }
        }
        showAll();
    }

    public void sort() {
        Shoes shoes = Utils.readFile("sort");
        shoeses.add(shoes);
        Collections.sort(shoeses);
        showAll();
    }

    public void showAll() {
        for (Shoes shoese : shoeses) {
            shoese.showProfile();
        }
    }

}
