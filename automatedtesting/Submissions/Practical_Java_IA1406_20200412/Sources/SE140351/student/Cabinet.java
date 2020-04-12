package com.practicalexam.student;

import com.practicalexam.student.data.Shoes;
import java.util.Scanner;

/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    private int count = 0;
    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
    private Shoes ds[] = new Shoes[500];
    //EndList - do not remove this comment!!!
    Scanner scannerObj = new Scanner(System.in);

    public void add() {
        // Print the object details after adding
        String code, model;
        int size;
        double price;
        System.out.println("Input the shoes no " + (count + 1));

        System.out.print("Input code: ");
        code = scannerObj.nextLine();

        System.out.print("Input model: ");
        model = scannerObj.nextLine();

        System.out.print("Input size: ");
        size = Integer.parseInt(scannerObj.nextLine());
        size = scannerObj.nextInt();

        System.out.print("Input price: ");
        price = Double.parseDouble(scannerObj.nextLine());

        ds[count] = new Shoes(code, model, size, price);
        ds[count].showAll();
        count++;

    }

    public boolean checkDuplicatedId(String id) {
        // Your code here

        return true;
    }

    public void update() {
        String code;
        System.out.print("Input code shoes: ");
        code = scannerObj.nextLine();
        String model;
        double price;
        // Print the object details after updating name/model and price
        for (Shoes x : ds) {
            if (x.getCode() == code) {
                System.out.print("Enter the model to update");
                model = scannerObj.nextLine();
                x.setModel(model);
                System.out.println("Enter the price to update");
                price = Double.parseDouble(scannerObj.nextLine());
                x.setPrice(price);
                x.showAll();
            }
        }
    }

    public void search() {
        String code;
        System.out.print("Input code shoes: ");
        code = scannerObj.nextLine();
        if (ds.length == 0) {
            System.out.println("You don't have any shoes");
        }
        for (Shoes x : ds) {
            if (x.getCode() == code) {
                x.showAll();
            }
        }
    }

    public void remove() {
        // Print the list after removing

    }

    public void sort() {
        // Print the object details after sorting

    }

}
