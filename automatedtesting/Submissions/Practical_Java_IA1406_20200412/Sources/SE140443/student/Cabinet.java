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
    private int count = 0;
    Scanner scannerObj = new Scanner(System.in);

    public void add() {
        // Print the object details after adding
        String code, model;
        int size;
        double price;
        System.out.println("Input the shoes no#" + (count + 1));
        System.out.print("Input code: ");
        code = scannerObj.nextLine();

        System.out.print("Input model: ");
        model = scannerObj.nextLine();

        System.out.print("Input size: ");
        size = Integer.parseInt(scannerObj.nextLine());

        System.out.print("Input price: ");
        price = Double.parseDouble(scannerObj.nextLine());

        ds[count] = new Shoes(code, model, size, price);
        ds[count].show();
        count++;
    }

    public boolean checkDuplicatedId(String code) {
        // Your code here
        for (Shoes x : ds) {
            if (x.getCode().equalsIgnoreCase(code)) {
                return false;
            }
        }
        return true;
    }

//     public void update(String code) {
//        // Print the object details after updating name/model and price
//        String model;
//        Double price;
//
//        if (ds.length == 0) {
//            System.out.println("There are no shoes to update!");
//        } else {
//
//            for (Shoes x : ds) {
//                if (x.getCode().equalsIgnoreCase(code)) {
//                    System.out.print("Enter model: ");
//                    model = scannerObj.nextLine();
//                    System.out.print("Enter price: ");
//                    price = scannerObj.nextDouble();
//                    x.setModel(model);
//                    x.setPrice(price);
//                    x.show();
//                } else {
//                    System.out.println("Wrong code!");
//                }
//
//            }
//        }
//
//    }
    public void update() {
        // Print the object details after updating name/model and price
        String model;
        Double price;
        System.out.print("Input code: ");
        String code = scannerObj.nextLine();
        if (ds.length == 0) {
            System.out.println("There are no shoes to update!");
        } else {
            for (Shoes x : ds) {
                if (x.getCode().equalsIgnoreCase(code)) {
                    System.out.print("Enter model: ");
                    model = scannerObj.nextLine();
                    System.out.print("Enter price: ");
                    price = scannerObj.nextDouble();
                    x.setModel(model);
                    x.setPrice(price);
                    x.show();
                } else {
                    System.out.println("Wrong code!");
                }

            }
        }

    }

    public void search() {
        // Print the object details after searching
        System.out.print("Input code: ");
        String code = scannerObj.nextLine();
        if (ds.length == 0) {
            System.out.println("There are no shoes to search!");
        } else {
            for (Shoes x : ds) {
                if (x.getCode().equalsIgnoreCase(code)) {
                    x.show();
                }
            }
        }
    }
//    public void search(String code) {
//        // Print the object details after searching
//        if (ds.length == 0) {
//            System.out.println("There are no shoes to search!");
//        } else {
//            for (Shoes x : ds) {
//                if (x.getCode().equalsIgnoreCase(code)) {
//                    x.show();
//                }
//            }
//        }
//    }

    public void remove() {
        // Print the list after removing

    }

    public void sort() {
//         Print the object details after sorting
//        for (int i = 0; i < ds.length - 1; i++) {
//            for (int j = 0; j < ds.length; j++) {
//
//            }
//        }
    }

}
