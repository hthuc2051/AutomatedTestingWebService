package com.practicalexam.student;

import java.util.Scanner;

/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
    private Cake ds[] = new Cake[100];
    //EndList - do not remove this comment!!!
    private int count = 0;

    public void add() {
        // Print the object details after adding
        Scanner sc = new Scanner(System.in);
        String id, name;
        double price;
        int size;
        System.out.println("input the cake: " + (count + 1) + "/" + ds.length);
        System.out.println("input the name: ");
        name = sc.nextLine();

        System.out.println("input the id: ");
        id = sc.nextLine();

        System.out.println("input price: ");
        price = sc.nextDouble();

        System.out.println("input a size: ");
        size = sc.nextInt();

        ds[count] = new Cake(name, id, price, size);
        ds[count].showProfile();
        count++;
    }

    public void showAll() {
        if (count == 0) {
            System.out.println("nothing: ");
        } else {
            System.out.println("There are " + count + " " + "profile(s) in the cabinet");
            for (int i = 0; i < count; i++) {
                ds[1].showProfile();
            }
        }
    }

    public boolean checkDuplicatedId(String id) {
        // Your code here
        for (int i = 0; i < 10; i++) {

        }
        return true;
    }

    public void update() {
        // Print the object details after updating name/model and price
        Scanner sc = new Scanner(System.in);
        String id, name;
        double price;
        System.out.println("Input id: ");
        id = sc.nextLine();
        for (int i = 0; i < count; i++) {
            if (ds[i].getId().equalsIgnoreCase(id) == true) {
                System.out.println("Input new name: ");
                name = sc.nextLine();
                ds[i].setName(name);
                System.out.println("Input new price: ");
                price = sc.nextDouble();
                ds[i].setPrice(price);
                ds[i].showProfile();
            } else {
                System.out.println("Not found!");
            }
        }
    }

    public void search() {
        // Print the object details after searching
        Scanner sc = new Scanner(System.in);
        String id;
        System.out.println("Input id: ");
        id = sc.nextLine();
        for (int i = 0; i < count; i++) {
            if (ds[i].getId().equalsIgnoreCase(id) == true) {
                ds[i].showProfile();
                return;
            } else {
                System.out.println("Not found!");
            }
        }
    }

    public void remove() {
        // Print the list after removing

    }

    public void sort() {
        // Print the object details after sorting
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count; j++) {
                Cake tmp;
                if (ds[i].getName().compareTo(ds[j].getName()) > 0) {
                    tmp = ds[i];
                    ds[i] = ds[j];
                    ds[j] = tmp;
                }
            }
        }
        for (int i = 0; i < count; i++) {
            ds[i].showProfile();
        }
    }

}
