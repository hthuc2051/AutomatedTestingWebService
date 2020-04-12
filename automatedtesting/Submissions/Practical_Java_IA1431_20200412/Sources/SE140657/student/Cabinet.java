package com.practicalexam.student;

import com.practicalexam.student.data.Cake;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
    private ArrayList<Cake> cakeList = new ArrayList();
    //EndList - do not remove this comment!!!
    private Scanner sc = new Scanner(System.in);

    public void add() {
        String Class = null, id, name;
        int size;
	double price;
        int pos;
        do {

            id = Mytoy2.getID("Input Cake id(CXXXXX): ","The format of id is CXXXXX (X stands for a digit)", "^[C|c]\\d{5}$");
            pos = search(id);
            if (pos >= 0) {
                System.out.println("The Cake id already exists. "
                        + "Input another one!");
            }
        } while (pos != -1);
        name = Mytoy2.getString("Input Cake name: ", "The Cake name is required!");
        size = Mytoy2.getAnInteger("Input cake Size (1..10): ", "Cake size is 1..10!", 1, 10);
        price = Mytoy2.getAnInteger("Input Price (50_000..1_000_000): ", "Price is from 50_000 to 1_000_000", 50_000, 1_000_000);
        Cake dto = new Cake(id, name, size, price);
        cakeList.add(dto);
        dto.showProFile();
        //System.out.println("A cat profile is added sucessfully");
    }

    public boolean checkDuplicatedId(String id) {
        // Your code here

        return true;
    }

    public void update() {
        String id;
        //int price;
        String name;
        //int tmpPrice;
        Cake x;
        Cake y;
        id = Mytoy2.getString("Input Cake id: ", "Cake id is required!");
        x = searchCakeObjectByID(id);
        //y = searchCakeObjectByID(price);
        System.out.println("------------------------------------");
        if (x == null) {
            System.out.println("Not found!!!");
        } else {
            System.out.println("Here is the Pet before updating");
            x.showProFile();
            System.out.println("You are required to input a new name");
            name = Mytoy2.getString("Input new name: ", "Name is required!");
            x.setName(name);
            // System.out.println("You are required to input a new price");
            //tmpPrice = Mytoy2.getAnInteger("Input new Price: ", "Price is required!");
            // y.setPrice(price);
            x.showProFile();
            System.out.println("The pet info is updated successfully!");
        }

    }

    public void search() {
        String id;
        Cake x;
        id = Mytoy2.getString("Input cake id: ", "cake id is required!");
        x = searchCakeObjectByID(id);
        System.out.println("------------------------------------");
        if (x == null) {
            System.out.println("Not found!!!");
        } else {
            System.out.println("Here is the Cake "
                    + "that you want to search");
            x.showProFile();
        }

    }

    public int search(String petID) {

        int pos;
        if (cakeList.isEmpty()) {
            return -1;
        }

        for (int i = 0; i < cakeList.size(); i++) {
            if (cakeList.get(i).getId().equalsIgnoreCase(petID)) {
                return i;
            }
        }

        return -1;
    }

    public void remove() {
        String id;
        int pos;
        id = Mytoy2.getString("Input Cake id: ", "Cake id is required!");
        pos = search(id);
        System.out.println("------------------------------------");
        if (pos == -1) {
            System.out.println("Not found!!!");
        } else {
            Cake getCake = cakeList.remove(pos);
            getCake.showProFile();
            System.out.println("The selected pet is removed successfully!");
        }

    }

    public void sort() {
        // Print the object details after sorting

    }

    public Cake searchCakeObjectByID(String cakeID) {
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

}
