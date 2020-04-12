package com.practicalexam.student;

import com.practicalexam.student.data.Shoes;
import java.util.Scanner;

/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
    private Shoes ds[] = new Shoes[500];
    //EndList - do not remove this comment!!!

    //public static final int MAX_SIZE = 500;
    private int count = 0;
    Scanner scannerObj = new Scanner(System.in);

    int find(String code) {
        for (int i = 0; i < count; i++) {
            if (code.equals(ds[i].getCode())) {
                return i;
            }
        }
        return -1;
    }

    public void add() {
        if (count == ds.length) {
            System.out.println("List is full!");
        } else {
            String code, model;
            double price;
            int size;
            int pos;
            do {
                System.out.print("Enter the code name: ");
                code = scannerObj.nextLine();
                pos = find(code);
                if (pos >= 0) {
                    System.out.println("\tThis id existed");
                }
            } while (pos >= 0);

            System.out.print("Enter the model name: ");
            model = scannerObj.nextLine().toUpperCase();
            System.out.print("Enter the size: ");
            size = Integer.parseInt(scannerObj.nextLine());
            System.out.print("Enter the price: ");
            price = Double.parseDouble(scannerObj.nextLine());
            ds[count++] = new Shoes(code, model, size, price);
            System.out.println("New Shoes has been added");
            //Added by Team SE1267
            ds[count++].showDetail();
            //

        }
    }

    public boolean checkDuplicatedId(String id) {
        // Your code here

        return true;
    }

    public void update() {
        if (count == 0) {;
            System.out.println("Empty list.");
            return;
        }
        String code;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the id of shoes: ");
        code = sc.nextLine();
        int pos = find(code);
        if (pos < 0) {
            System.out.println("This shoes does not exist.");
        } else {
            String model;
            double price;
            System.out.println("Enter the new Model shoes: ");
            model = sc.nextLine();
            System.out.println("Enter the new price shoes: ");
            price = Double.parseDouble(sc.nextLine());
            ds[pos].setModel(model);
            ds[pos].setPrice(price);
            System.out.println("The shoes " + code + " was updated");
            ds[pos].showDetail();
        }
    }

    public Shoes search() {
        Shoes ds[] = null;
        System.out.println("Enter the id of shoes: ");
        String code = scannerObj.nextLine();
        Shoes tmp;
        if (ds.length == 0) {
            return null;
        }

        for (int i = 0; i < ds.length; i++) {
            if (ds[i].getCode().equalsIgnoreCase(code)) {
                return ds[i];
            }
        }
        return null;
    }

    public void remove() {

    }

    public void print() {
        if (ds.length == 0) {
            System.out.println("The shoes list is empty. Nothing to print");
            return;
        }
        System.out.println("The shoes list");
        for (int i = 0; i < count; i++) {
            ds[i].showDetail();
        }
    }

    public void sort() {
    }
}
