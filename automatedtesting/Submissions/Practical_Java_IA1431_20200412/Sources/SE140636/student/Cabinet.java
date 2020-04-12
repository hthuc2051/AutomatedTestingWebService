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
        Scanner sc = new Scanner(System.in);
        String id;
        String name;
        int size;
        double price;
        boolean tmp = true;

        System.out.println("Input Cake no #"
                + (count + 1) + "/" + ds.length);

        System.out.print("Input id: ");
        id = sc.nextLine().toUpperCase();

        System.out.print("Input name: ");
        name = sc.nextLine().toUpperCase();

        System.out.print("Input Size: ");
        size = Integer.parseInt(sc.nextLine());

        System.out.print("Input Price: ");
        price = Double.parseDouble(sc.nextLine());
        tmp = checkDuplicatedId(id);
        if (tmp == false) {
            System.out.println("This id already exits");
        }
        if (tmp) {
            ds[count] = new Cake(id, name, size, price);
            ds[count].showAll();
            count++;
        }

//        for (int i = 0; i < count; i++) {
//            ds[i].showAll();
//        }
    }

    public boolean checkDuplicatedId(String id) {
        if (count == 0) {
            return true;
        }
        for (int i = 0; i < count; i++) {
            if (ds[i].getId().equalsIgnoreCase(id)) {
                System.out.println("This id already exits");
                return false;
            }
        }
        return true;
    }

    public void update() {
        Scanner sc = new Scanner(System.in);
        int tmp = 0;
        String name;
        double price = 0;
        String id;
        System.out.print("Input id: ");
        id = sc.nextLine().toUpperCase();
        for (int i = 0; i < count; i++) {
            if (ds[i].getId().equalsIgnoreCase(id)) {
                System.out.print("Update name: ");
                name = sc.nextLine().toUpperCase();
                ds[i].setName(name);
                System.out.print("Update price: ");
                price = Double.parseDouble(sc.nextLine());
                ds[i].setPrice(price);
                ds[i].showAll();
                tmp++;
            }
        }

//        if (tmp != 0) {
//            System.out.println("Array After Update");
//            for (int i = 0; i < count; i++) {
//                ds[i].showAll();
//            }
//        } else {
//            System.out.println("This ID not exits");
//        }
    }

    public void search() {
        // Print the object details after searching
        String id;
        Scanner sc = new Scanner(System.in);
        System.out.print("Input id: ");
        id = sc.nextLine().toUpperCase();
        for (int i = 0; i < count - 1; i++) {
            if (ds[i].getId().equalsIgnoreCase(id)) {
                ds[i].showAll();
                return;
            }
        }
        System.out.println("This ID not exits");

    }

    public void remove() {
        // Print the list after removing
        // Print the list after removing
        if (ds.length < 0) {
            System.out.println("Mang ds khong co du lieu");
        }
        String id;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter id you want to remove: ");
        id = scan.nextLine();
        for (int i = 0; i < count; i++) {
            if (ds[i].getId().equalsIgnoreCase(id)) {
                for (int j = i; j < count; j++) {
                    ds[i] = ds[i + 1];
                }
                //ds[count - 1].showAll();
                count--;
                System.out.println("REMOVE SUCCESSFUL");
            }
        }
        for (int i = 0; i < count; i++) {
            ds[i].showAll();
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
            ds[i].showAll();
        }

    }

}
