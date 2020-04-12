package com.practicalexam.student;

import java.util.Scanner;

/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
    private Cake ds[] = new Cake[10];
    //EndList - do not remove this comment!!!

    private int count = 0;

    public void addCake() {
        Scanner sc = new Scanner(System.in);
        String name, id;
        double price;
        int size;
        System.out.println("Input the Cake: " + (count + 1) + "/" + ds.length);
        System.out.println("input name: ");
        name = sc.nextLine();

        System.out.println("input id: ");
        id = sc.nextLine();

        System.out.println("input price: ");
        price = Double.parseDouble(sc.nextLine());

        System.out.println("input size: ");
        size = Integer.parseInt(sc.nextLine());
        ds[count] = new Cake(name, id, size, price);
        ds[count].showProfile();
        count++;
    }

    public void showAll() {
        if (count == 0) {
            System.out.println("Nothing: ");
        }
        System.out.println("There are " + count + " " + "profile(s) in the cabinet");
        for (int i = 0; i < count; i++) {
            ds[i].showProfile();
        }
    }

    public void add() {
        // Print the object details after adding
        Scanner sc = new Scanner(System.in);
        String name, id;
        double price;
        int size;
        System.out.println("Input the Cake: " + (count + 1) + "/" + ds.length);
        System.out.println("input name: ");
        name = sc.nextLine();
        System.out.println("input id: ");
        id = sc.nextLine();
        System.out.println("input price: ");
        price = Double.parseDouble(sc.nextLine());
        System.out.println("input size: ");
        size = Integer.parseInt(sc.nextLine());
        ds[count] = new Cake(name, id, size, price);
        ds[count].showProfile();
        count++;
        //showAll();
    }

    public boolean checkDuplicatedId(String id) {
        // Your code here

        return true;
    }

    public void update() {
        // Print the object details after updating name/model and price
        Scanner sc = new Scanner(System.in);
        String id, name;
        double price;
        int check = 0;
        System.out.println("Input id: ");
        id = sc.nextLine();
        for (int i = 0; i < count; i++) {
            if (ds[i].getId().equalsIgnoreCase(id)) {
                System.out.println("Input new name: ");
                name = sc.nextLine();
                ds[i].setName(name);
                System.out.println("Input new price: ");
                price = sc.nextDouble();
                ds[i].setPrice(price);
                ds[i].showProfile();
            } else {
                check = 1;
            }
        }
        if (check == 1) {
            System.out.println("Not found!");
            return;
        }
    }

    public static Cake search(Cake[] ds, String id) {
        // Print the object details after searching
        for (Cake x : ds) {
            if (x.getId().equalsIgnoreCase(id)) {
                return x;
            }
        }
        return null;
    }

    public void search() {
        System.out.println("Enter id you want found: ");
        Scanner found = new Scanner(System.in);
        String id = found.nextLine();
        Cake x = search(ds, id);
        if (x == null) {
            System.out.println("Not found");
        } else {
            System.out.println("Found");
            x.showProfile();
        }
    }

    public void remove() {
        // Print the list after removing

    }

    public void sort() {
        // Print the object details after sorting

    }

}
