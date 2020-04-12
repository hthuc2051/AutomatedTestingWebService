package com.practicalexam.student;

import com.practicalexam.student.data.Shoes;
import java.util.Scanner;

/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    public static final int MAX_SIZE = 500;
    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
    private Shoes ds[] = new Shoes[500];
    //EndList - do not remove this comment!!!
    private int count = 0;
    public Scanner scannerObj = new Scanner(System.in);

    public void add() {
        // Print the object details after adding
        String code, model;
        int size;
        double price;
        System.out.println("Input shoes info of shoes no. #"
                + (count + 1) + "/" + ds.length);
        System.out.println("Input Code : ");
        code = scannerObj.nextLine();
        if (checkDuplicatedId(code) == true) {
            System.out.println("This code has been existed!");
        }
        System.out.println("Input Model : ");
        model = scannerObj.nextLine();
        System.out.println("Input Size : ");
        size = Integer.parseInt(scannerObj.nextLine());
        System.out.println("Input Price : ");
        price = Double.parseDouble(scannerObj.nextLine());
        if (checkDuplicatedId(code) == false) {
            ds[count] = new Shoes(code, model, size, price);
            System.out.println("Added");
            ds[count].showInfo();
            count++;
        }

    }

    public boolean checkDuplicatedId(String id) {
        // Your code here
        for (int i = 0; i < count; i++) {
            if (ds[i].getCode().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public void update() {
        String code;
        String model;
        double price;
        // Print the object details after updating name/model and price
        if (count == 0) {
            System.out.println("Array list was empty. Try to add more");
            return;
        }
        System.out.println("Input Code : ");
        code = scannerObj.nextLine();
        for (int i = 0; i < count; i++) {
            if (ds[i].getCode().equalsIgnoreCase(code)) {
                System.out.println("Input Model : ");
                model = scannerObj.nextLine();
                System.out.println("Input Price : ");
                price = Double.parseDouble(scannerObj.nextLine());
                ds[i].setModel(model);
                ds[i].setPrice(price);
                System.out.println("Updated successfully");
                ds[i].showInfo();
                return;
            }
        }

    }

    public void search() {
        // Print the object details after searching
        String code;

        if (count == 0) {
            System.out.println("Array list was empty. Try to add more");
            return;
        }
        System.out.println("Input Code : ");
        code = scannerObj.nextLine();
        for (int i = 0; i < count; i++) {
            if (ds[i].getCode().equalsIgnoreCase(code)) {
                ds[i].showInfo();
                return;
            }
        }
        System.out.println("Not Found!");
    }

    public void remove() {
        // Print the list after removing
        String code;
        int pos = -1;

        if (count == 0) {
            System.out.println("Array list was empty. Try to add more");
            return;
        }

        System.out.println("Input Code : ");
        code = scannerObj.nextLine();

        for (int i = 0; i < count; i++) {
            if (ds[i].getCode().equalsIgnoreCase(code)) {
                pos = i;
            }
        }

        System.out.println("------------------------------------");
        if (pos == -1) {
            System.out.println("Not existed!!!");
        } else {
            for (int i = pos; i < count - 1; i++) {
                Shoes tmp;
                tmp = ds[i];
                ds[i] = ds[i + 1];
                ds[i + 1] = tmp;
            }
            ds[count - 1].showInfo();
            count--;
            System.out.println("Deleted");

        }
    }

    public void sort() {
        // Print list after sorting
        if (count == 0) {
            System.out.println("Array list was empty. Try to add more");
            return;
        }
        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                if (ds[i].getModel().compareTo(ds[j].getModel()) > 0) {
                    Shoes tmp;
                    tmp = ds[i];
                    ds[i] = ds[j];
                    ds[j] = tmp;
                }
            }
        }
        System.out.println("Result");
        for (int i = 0; i < count; i++) {
            ds[i].showInfo();
        }
    }

}
