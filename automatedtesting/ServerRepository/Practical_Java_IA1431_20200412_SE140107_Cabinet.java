package com.practicalexam.student;

import com.practicalexam.student.data.Cake;
import java.util.Scanner;

/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
    Cake dsCake[] = new Cake[500];
    //EndList - do not remove this comment!!!
    private int count = 0;

    public void add() {
        String id, name;
        int size;
        double price;

        Scanner scan = new Scanner(System.in);
        do {
            System.out.print("Enter cake id: ");
            id = scan.nextLine();
            if (!checkDuplicatedId(id)) {
                System.out.println("This id really exist");
            }
        } while (!checkDuplicatedId(id));
        System.out.print("Enter cake name: ");
        name = scan.nextLine();
        size = tryCatchSize();
        price = tryCatchPrice();
        dsCake[count] = new Cake(id, name, size, price);
        dsCake[count].showAll();
        ++count;
    }

    public double tryCatchPrice() {
        double price;
        Scanner scan = new Scanner(System.in);
        //
        do {
            System.out.print("Enter cake size: ");
            try {
                price = Double.parseDouble(scan.nextLine());
                return price;
            } catch (Exception e) {
                System.out.println("Please input is number");
            }
return 0;
        } while (true);
    }

    public int tryCatchSize() {
        int size;
        Scanner scan = new Scanner(System.in);
        //
        do {
            System.out.print("Enter cake price: ");
            try {
                size = Integer.parseInt(scan.nextLine());
                return size;
            } catch (Exception e) {
                System.out.println("Please input is number");
            }
	return 0;
        } while (true);
    }

    public boolean checkDuplicatedId(String id) {
        // Your code here
        for (int i = 0; i < count; i++) {
            if (dsCake[i].getId().equalsIgnoreCase(id) ) {
                return false;
            }
        }
        return true;
    }

    public void update() {
        // Print the object details after updating name/model and price

        if (dsCake.length < 0) {
            System.out.println("Mang dsCake khong co du lieu");
        }
        String id, name;
        double price;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter id you want to update name and price: ");
        id = scan.nextLine();
        System.out.print("Enter new name cake you want to update: ");
        name = scan.nextLine();
        System.out.print("Enter new price cake you want to update: ");
        price = Double.parseDouble(scan.nextLine());
        for (int i = 0; i < count; i++) {
            if (dsCake[i].getId().equalsIgnoreCase(id) ) {
                dsCake[i].setName(name);
                dsCake[i].setPrice(price);
                dsCake[i].showAll();
            }
        }
    }

    public void search() {
        // Print the object details after searching
        if (dsCake.length < 0) {
            System.out.println("Mang dsCake khong co du lieu");
        }
        String id;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter id you want to search: ");
        id = scan.nextLine();
        for (int i = 0; i < count; i++) {
            if (dsCake[i].getId().equalsIgnoreCase(id) ) {
                dsCake[i].showAll();
            }
        }

    }

    public void remove() {
        // Print the list after removing
        if (dsCake.length < 0) {
            System.out.println("Mang dsCake khong co du lieu");
        }
        String id;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter id you want to remove: ");
        id = scan.nextLine();
        for (int i = 0; i < count; i++) {
            if (dsCake[i].getId().equalsIgnoreCase(id) ) {
                for (int j = i; j < count; j++) {
                    dsCake[i] = dsCake[i + 1];
                }
                //dsCake[count - 1].showAll();
                count--;
                System.out.println("REMOVE SUCCESSFUL");
            }
        }
        for (int i = 0; i < count; i++) {
            dsCake[i].showAll();
        }

    }

    public void sort() {
        // Print the object details after sorting
        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                if (dsCake[i].getName().compareTo(dsCake[j].getName()) < 0) {
                    Cake t = dsCake[i];
                    dsCake[i] = dsCake[j];
                    dsCake[j] = t;
                }
            }
        }
        for (int i = 0; i < count; i++) {
            dsCake[i].showAll();
        }

    }

}
