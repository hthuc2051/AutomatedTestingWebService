package com.practicalexam.student;

import java.util.Scanner;

/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
    public Cake dsc[] = new Cake[200];
    //EndList - do not remove this comment!!!

    private String x;
    private int count = 0;
    private Object integer;

    public void add() {
        // Print the object details after adding
        String id;
        String name;
        int size;
        double price;
        Scanner sc = new Scanner(System.in);

        System.out.println("Cake " + (count + 1) + " of " + (dsc.length) + ": ");
        do {
            System.out.println("ID of cake: ");
            id = sc.nextLine();

            System.out.println("Name of cake: ");
            name = sc.nextLine();

            System.out.println("Size of cake: ");
            size = Integer.parseInt(sc.nextLine());

            System.out.println("Price of cake: ");
            price = Double.parseDouble(sc.nextLine());

        } while (checkDuplicatedId(id));

        if (!checkDuplicatedId(id)) {
            dsc[count] = new Cake(id, name, size, price);
            dsc[count].showProfile();
            count++;
        }

    }

    public boolean checkDuplicatedId(String id) {
        if (count == 0) {
            return true;
        }

        for (int i = 0; i < count; i++) {
            if (dsc[i].getId().equalsIgnoreCase(id)) {
                System.out.println("FAIL");
                return false;
            }
        }

        return true;
    }

    public void update() {
        // Print the object details after updating name/model and price
        Scanner sc = new Scanner(System.in);
        boolean x = false;
        String id;
        String name;
        double price;

        System.out.print("ID of cake: ");
        id = sc.nextLine();

        if (count == 0) {
            System.out.println("nothing here ");
        }

        for (int i = 0; i < count; i++) {
            if (dsc[i].getId().equalsIgnoreCase(id)) {
                x = true;

                System.out.print("New name of cake: ");
                name = sc.nextLine();

                System.out.println("New price of  cake: ");
                price = Double.parseDouble(sc.nextLine());

                if (x != false) {
                    for (int j = 0; j < count; j++) {
                        dsc[j].setName(name);
                        dsc[j].setPrice(price);
                        dsc[j].showProfile(); //Added
                    }
                }
                break;
            }
        }

        if (x == false) {
            System.out.println("Can't UPDATE");
        }

    }

    public void search() {
        // Print the object details after searching
        Scanner sc = new Scanner(System.in);
        boolean x = false;
        String id;

        System.out.print("ID of cake to search: ");
        id = sc.nextLine();

        if (count == 0) {
            System.out.println("nothing here ");
        }

        for (int i = 0; i < count; i++) {
            if (dsc[i].getId().equalsIgnoreCase(id)) {
                System.out.println("FOUND");
                x = true;
                dsc[i].showProfile();
                break;
            }
        }

        if (x == false) {
            System.out.println("Not FOUND");
        }
    }

    public void remove() {
        // Print the list after removing
        Scanner sc = new Scanner(System.in);
        String id;
        System.out.print("ID of cake want to remove: ");
        id = sc.nextLine();

        if (count == 0) {
            System.out.println("Mang dsc Cake không có dữ liệu");
        }
        for (int i = 0; i < count; i++) {
            if (dsc[i].getId().equalsIgnoreCase(id)) {
                for (int j = i; j < count; j++) {
                    dsc[i] = dsc[i + 1];
                }
                //dsc[count - 1].showProfile();
                count--;
                System.out.println("REMOVE SUCCESSFUL");
            }
        }
        for (int i = 0; i < count; i++) {
            dsc[i].showProfile();
        }
    }

    public void sort() {
        // Print the object details after sorting
        Scanner sc = new Scanner(System.in);
        System.out.print("Input id:");
        String id = sc.nextLine().toUpperCase();

        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                char t = dsc[i].getName().charAt(0);
                if (t <= dsc[j].getName().charAt(0)) {
                    Cake a = dsc[i];
                    dsc[i] = dsc[j];
                    dsc[j] = a;
                }
            }
        }
        //Added
        for (int i = 0; i < count; i++) {
            dsc[i].showProfile();
        }
    }

}
