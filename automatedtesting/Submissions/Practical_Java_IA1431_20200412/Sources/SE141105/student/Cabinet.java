package com.practicalexam.student;

import java.util.Scanner;

/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
    public static Cake ds[] = new Cake[10];
    //EndList - do not remove this comment!!!
    static int count = 0;

    public void add() {
        // Print the object details after adding
        Scanner sc = new Scanner(System.in);
        String id, name;
        int size;
        double price;
        System.out.println("Input Cake no#" + (count + 1) + "/" + ds.length);
        System.out.println("Input ID of cake: ");
        id = sc.nextLine();
        System.out.println("Input name of cake: ");
        name = sc.nextLine();
        System.out.println("Input size of cake: ");
        size = sc.nextInt();
        System.out.println("Input price of cake");
        price = sc.nextDouble();
        ds[count] = new Cake(id, name, size, price);
        ds[count].showProfile();
        count++;
    }

    public void printWatchAll(Cake[] ds) {
        if (ds.length == 0) {
            System.out.println("Nothing to print");
            return;
        }
        System.out.println("The list of students");
        for (int i = 0; i < count; i++) {
            Cabinet.ds[i].showProfile();
        }
    }

    public boolean checkDuplicatedId(Cake ds[], String id) {
        // Your code here
        for (int i = 0; i < count; i++) {
            if (Cabinet.ds[i].getId().equalsIgnoreCase(id) == true) {
                return true;
            }
        }
        return false;
    }

    public void update() {
        System.out.println("Enter the ID you want to update ");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        Cake x1 = Cabinet.searchById(ds, id);
        if (x1 == null) {
            System.out.println("Not found!");
        } else {
            System.out.println("We will update your profile");
            System.out.println("Enter name to modified: ");
            String name = sc.nextLine();
            x1.setName(name);
            System.out.println("Enter price to modified: ");
            double price = sc.nextDouble();
            x1.setPrice(price);
            System.out.println("After update profiles: ");
            x1.showProfile();
        }
    }

    public void search() {
        // Print the object details after searching
        System.out.println("Enter the ID you want to update ");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        for (Cake x : ds) {
            if (x.getId().equalsIgnoreCase(id) == true) {
                x.showProfile();
            }
        }
    }

    public static Cake searchById(Cake ds[], String id) {
        // Print the object details after searching
        for (Cake x : ds) {
            if (x.getId().equalsIgnoreCase(id) == true) {
                return x;
            }
        }
        return null;
    }

    public void remove() {
        // Print the list after removing
        System.out.println("Enter the ID you want to update ");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        for (int i = 0; i < Cabinet.count; i++) {
            if (ds[i].getId().equals(id) == true) {
                ds[i] = ds[i + 1];
            }
        }
        //ds[count - 1].showProfile();
        (Cabinet.count)--;
        System.out.println("Removed!");
        for (Cake x : ds) {
            x.showProfile();
        }
    }

//    public static Cake search(Cake ds[], String id) {
//        // Print the object details after searching
//        for (Cake x : ds) {
//            if (x.getId().equalsIgnoreCase(id) == true) {
//                return x;
//            }
//        }
//        return null;
//    }
    public void sort() {
        // Print the object details after sorting

    }

}
