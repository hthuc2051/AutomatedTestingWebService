package com.practicalexam.student;

import java.util.Scanner;

/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
    Cake ds[] = new Cake[200];
    //EndList - do not remove this comment!!!
    int count = 0;

    public Cabinet() {
    }

    public void add() {
        // Print the object details after adding
        String id;
        String name;
        int size;
        double price;
        boolean x;
        Scanner sc = new Scanner(System.in);

        System.out.println("The " + (count + 1) + "/" + (ds.length) + " of Cake:");
        System.out.print("ID of cake: ");
        id = sc.nextLine();
        System.out.print("Name of cake: ");
        name = sc.nextLine();
        System.out.print("Size of cake: ");
        size = sc.nextInt();
        System.out.print("Price of cake: ");
        price = sc.nextDouble();
        x = checkDuplicatedId(id);

        if (x == false) {
            ds[count] = new Cake(id, name, size, price);
            ds[count].showCake();
            count++;
        }

    }

    public double checkSize() {
        Scanner sc = new Scanner(System.in);
        double x;
        do {
            System.out.print("Size of cake: ");
            try {
                x = Double.parseDouble(sc.nextLine());
                return x;
            } catch (Exception e) {
                System.out.println("Try again !!!");
            }
            return 0;
        } while (true);
    }

    public double checkPrice() {
        Scanner sc = new Scanner(System.in);
        double x;
        do {
            System.out.print("Price of cake: ");
            try {
                x = Double.parseDouble(sc.nextLine());
                return x;
            } catch (Exception e) {
                System.out.println("Try again !!!");
            }
            return 0;
        } while (true);
    }

    public boolean checkDuplicatedId(String id) {
        // Your code here

        for (int i = 0; i < count; i++) {
            if (ds[i].getId().equalsIgnoreCase(id)) {
                System.out.println("already");
                return true;
            }
        }
        return false;

    }

    public void update() {
        // Print the object details after updating name/model and price
        Scanner sc = new Scanner(System.in);
        boolean x = false;
        String name;
        double price;
        String id;

        System.out.print("ID of watch: ");
        id = sc.nextLine();

        if (count == 0) {
            System.out.println("nothing here ");
        }
        for (int i = 0; i < count; i++) {
            if (ds[i].getId().equalsIgnoreCase(id) == true) {
                x = true;

                System.out.print("Name of cake: ");
                name = sc.nextLine();
                System.out.print("Price of cake: ");
                price = sc.nextDouble();

                for (int j = 0; j < count; j++) {
                    ds[j].setName(name);
                    ds[j].setPrice(price);
                    ds[j].showCake(); //Added
                }
                break;
            }
        }

        if (x == false) {
            System.out.println("can't update");
        }

    }

    public void search() {
        // Print the object details after searching
        Scanner sc = new Scanner(System.in);
        boolean x = false;
        String id;

        System.out.print("ID of watch: ");
        id = sc.nextLine();

        if (count == 0) {
            System.out.println("nothing here ");
        }
        for (int i = 0; i < count; i++) {
            if (ds[i].getId().equalsIgnoreCase(id)) {
                ds[i].showCake();
                x = true;
                break;
            }
        }
        if (x == false) {
            System.out.println("not found");
        }
    }

    public void remove() {
        // Print the list after removing
        Scanner sc = new Scanner(System.in);
        String id;
        System.out.print("ID of cake: ");
        id = sc.nextLine();
        if (count == 0) {
            System.out.println("can't find anything");
        }

        for (int i = 0; i < count; i++) {
            if (ds[i].getId().equalsIgnoreCase(id)) {
                for (int j = i; j < count; j++) {
                    ds[i] = ds[i + 1];
                }
                //ds[count - 1].showCake(); //Added
                count--;
                System.out.println("remove complete");
            }
        }
        for (int i = 0; i < count; i++) {
            ds[i].showCake();
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
            ds[i].showCake();
        }

    }

}
