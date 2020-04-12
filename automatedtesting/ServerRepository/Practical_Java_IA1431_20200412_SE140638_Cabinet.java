package com.practicalexam.student;

import java.util.Scanner;

/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
    private Cake ds[] = new Cake[500];
    //EndList - do not remove this comment!!!
    private int count = 0;

    public void add() {
        // Print the object details after adding
        Scanner sc = new Scanner(System.in);
        String id, name;
        int size;
        double price;
        boolean check;
        System.out.println("Input cake no #"
                + (count + 1) + "/" + ds.length);

        System.out.print("Input id:");
        id = sc.nextLine().toUpperCase();
        System.out.print("Input name:");
        name = sc.nextLine().toUpperCase();
        System.out.print("Input size:");
        size = Integer.parseInt(sc.nextLine());
        System.out.print("Input prices:");
        price = Double.parseDouble(sc.nextLine());
        check = checkDuplicatedId(id);

        if (check == false) {
            System.out.println("this ID already exist!!!");
        }
        if (check) {
            System.out.println("ngu vc");
            ds[count] = new Cake(id, name, size, price);
            ds[count].showProfile();
            count++;
        }

    }

    public boolean checkDuplicatedId(String id) {
        // Your code here
        if (count == 0) {
            return true;
        }
        for (int i = 0; i < count; i++) {
            if (ds[i].getId().equals(id) == true) {
                return false;
            }
        }
        return true;
    }

    public void update() {
        // Print the object details after updating name/model and price
        Scanner sc = new Scanner(System.in);
        System.out.print("Input id:");
        String id = sc.nextLine().toUpperCase();
        String name;
        double price;
        for (int i = 0; i < count - 1; i++) {
            if (ds[i].getId().equalsIgnoreCase(id)) {
                System.out.print("Input name:");
                name = sc.nextLine().toUpperCase();
                ds[i].setName(name);
                System.out.print("Input prices:");
                price = Double.parseDouble(sc.nextLine());
                ds[i].setPrice(price);
                ds[i].showProfile();
                return;
            }
        }
        System.out.println("this ID not exist!!!");
    }

    public void search() {
        // Print the object details after searching
        Scanner sc = new Scanner(System.in);
        System.out.print("Input id:");
        String id = sc.nextLine().toUpperCase();

        for (int i = 0; i < count - 1; i++) {
            if (ds[i].getId().equalsIgnoreCase(id)) {
                ds[i].showProfile();
                return;
            }
        }
        System.out.println("this ID not exist!!!");

    }

    public void remove() {
        // Print the list after removing
        Scanner sc = new Scanner(System.in);
        System.out.print("Input id:");
        String id = sc.nextLine().toUpperCase();

        if (ds.length == 0) {
            System.out.println("not thing to delete!!!");
        }
        for (int i = 0; i < count; i++) {
            if (ds[i].getId().equalsIgnoreCase(id)) {
                for (int j = i; j < count; j++) {
                    ds[i] = ds[i + 1];
                }
                // ds[count - 1].showProfile();
                count--;
            }
        }
        for (int i = 0; i < count; i++) {
            ds[i].showProfile();
        }
    }

    public void sort() {
        // Print the object details after sorting
        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                if (ds[i].getName().compareTo(ds[j].getName()) < 0) {
                    Cake t = ds[i];
                    ds[i] = ds[j];
                    ds[j] = t;
                }
            }
        }
        for (int i = 0; i < count; i++) {
            ds[i].showProfile();
        }
    }

}
