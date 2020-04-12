package com.practicalexam.student;

import com.practicalexam.student.data.Cake;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
    private ArrayList<Cake> cakeList = new ArrayList();
    //EndList - do not remove this comment!!!

    public void add() {
        // Print the object details after adding
        String id;
        int pos;
        do {
            id = MyToys.getStringCode("Input cake code (CXXXXX): ", "The format of id is CXXXXX (X stands for a digit)", "^[C|c]\\d{5}$");
            pos = searchCakeById(id);
            if (pos >= 0) {
                System.out.println("The cake code already exists. " + "Input another one!");
            }
        } while (pos != -1);
        String name = MyToys.getString("Input Cake name: ", "The cake name is required!");
        int size = MyToys.getAnInteger("Input cake size: ", "Size is required!");
        double price = MyToys.getAnDouble("Input cake price (100000...999000): ", "Price from 100000 to 999000", 100000, 999000);
        if (pos == -1) {
            Cake cake = new Cake(id, name, size, price);
            cakeList.add(cake);
            cake.showProfile();//Added 
        }

    }

    public boolean checkDuplicatedId(String id) {
        // Your code here
        if (cakeList.isEmpty()) {
            return false;
        }
        for (int i = 0; i < cakeList.size(); i++) {
            if (cakeList.get(i).getId().equalsIgnoreCase(id)) {
                return false;
            }
        }
        return true;
    }

    public void update() {
        // Print the object details after updating name/model and price
        String id, name;
        double price;
        Cake x;
        id = MyToys.getString("Input cake id: ", "Cake id is required");
        x = searchObjectCakeById(id);
        if (x == null) {
            System.out.println("Not found!!!");
        } else {
            System.out.println("Here is the Cake before updating");
            x.showProfile();
            name = MyToys.getString("Input new name: ", "Name is required");
            x.setName(name);
            price = MyToys.getAnDouble("Input cake price (100000...999000): ", "Price from 100000 to 999000", 100000, 999000);
            x.setPrice(price);
            x.showProfile(); //Added
        }
    }

    public void search() {
        // Print the object details after searching
        String id;
        Cake x;
        id = MyToys.getString("Input cake code: ", "Cake id is required");
        x = searchObjectCakeById(id);
        for (int i = 0; i < cakeList.size(); i++) {
            if (x == null) {
                System.out.println("Not found!!!");
            } else {
                System.out.println("Here is the Cake that you want to search");
                x.showProfile();
            }
        }
    }

    public void remove() {
        // Print the list after removing
        String id;
        int pos;
        id = MyToys.getString("Input cake id: ", "Cake id is required");
        pos = searchCakeById(id);
        if (pos == -1) {
            System.out.println("Not found!!!");
        } else {
            cakeList.remove(pos);
            //getcake.showProfile(); //Added
            System.out.println("The selected cake is removed successfully!");
        }
        for (int i = 0; i < cakeList.size(); i++) {
            cakeList.get(i).showProfile();
        }
    }

    public void sort() {
        // Print the object details after sorting
        if (cakeList.isEmpty()) {
            System.out.println("The cage is empty. Nothing to print");
            return;
        }
        Comparator modelBlance = new Comparator<Cake>() {
            @Override
            public int compare(Cake o1, Cake o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        };
        Collections.sort(cakeList, modelBlance);
        System.out.println("-----------------------------------------");
        System.out.println("Here is the cake list");
        for (int i = 0; i < cakeList.size(); i++) {
            cakeList.get(i).showProfile();
        }

    }

    public Cake searchObjectCakeById(String cakeID) {
        if (cakeList.isEmpty()) {
            return null;
        }
        for (int i = 0; i < cakeList.size(); i++) {
            if (cakeList.get(i).getId().equalsIgnoreCase(cakeID)) {
                return cakeList.get(i);
            }
        }
        return null;
    }

    public int searchCakeById(String cakeID) {
        if (cakeList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < cakeList.size(); i++) {
            if (cakeList.get(i).getId().equalsIgnoreCase(cakeID)) {
                return i;
            }
        }
        return -1;
    }

}
