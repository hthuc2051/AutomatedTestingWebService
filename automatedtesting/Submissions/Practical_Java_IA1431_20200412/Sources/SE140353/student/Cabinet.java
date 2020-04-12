package com.practicalexam.student;

import com.practicalexam.student.data.Cake;
import java.util.Scanner;

/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    private final Scanner sc = new Scanner(System.in);
    private int count = 0;

    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
    private final Cake ds[] = new Cake[50];
    //EndList - do not remove this comment!!!

    public void add() {
        // Print the object details after adding
        System.out.println("The list of cake " + (count + 1) + "/" + ds.length + ": ");
        System.out.println("Input code : ");
        String id = sc.nextLine();
        System.out.println("Input model : ");
        String name = sc.nextLine();
        System.out.println("Input size: ");
        int size = Integer.parseInt(sc.nextLine());
        System.out.println("Input price: ");
        double price = Double.parseDouble(sc.nextLine());
        ds[count] = new Cake(id, name, size, price);
        ds[count].showCake();
        count++;

    }

    public boolean checkDuplicatedId(String id) {
        // Your code here
        System.out.println("Input the cake do you want to check: ");
        id = sc.nextLine();
        for (int i = 0; i < count; i++) {
            if (ds[i].getId().equalsIgnoreCase(id)) {
                System.out.println("Checking successful.");
                System.out.println("Here is the cake you want to check.");
                ds[i].showCake();
            } else {
                System.out.println("Checking fail!!");
                System.out.println("You shoul enter existing data. ");
            }

        }

        return true;
    }

    public void update() {
        // Print the object details after updating name/model and price

    }

    public void search() {
        // Print the object details after searching

    }

    public void remove() {
        // Print the list after removing

    }

    public void sort() {
        // Print the object details after sorting
        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                if (ds[i].getPrice() > ds[j].getPrice()) {
                    Cake tmp;
                    tmp = ds[i];
                    ds[i] = ds[j];
                    ds[j] = tmp;
                }
            }
        }
        try {
            for (int i = 0; i < count; i++) {
                ds[i].showCake();
            }
        } catch (Exception e) {
        }

    }

}
