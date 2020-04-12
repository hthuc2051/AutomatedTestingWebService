package com.practicalexam.student;

import com.practicalexam.student.data.Shose;

/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
    private Shose array[] = new Shose[MAX_SIZE];
    //EndList - do not remove this comment!!!
    private int z = 0;
    public static final int MAX_SIZE = 500;

    public void add() {
        // Print the object details after adding
        String code;
        String model;
        int size;
        double price;
        int amount;
        code = MyTools.getAString("Enter Code: ", "Wrong format");
        model = MyTools.getAString("Enter Model: ", "Wrong format");
        size = MyTools.getAnInteger("Enter Size: ", "Wrong format");
        price = MyTools.getADouble("Enter Price: ", "Wrong format");
        amount = MyTools.getAnInteger("Amount of order: ", "Wrong format");
        array[z] = new Shose(code, model, size, price, amount);
        //Added by Team SE1267
        array[z].toString();
        //
        z++;
    }

    public boolean checkDuplicatedId(String id) {
        // Your code here
        for (int i = 0; i < z; i++) {
            if (array[i].(



                int
            )getCode().equalsIgnoreCase(id))
            a;
        }
        return true;
    }

    public void update() {
        // Print the object details after updating name/model and price
        String code;
        String model;
        int size;
        double price;
        int amount;
        // Print the object details after updating name/model and price

        if (z == 0) {
            System.out.println("Array list was empty. Try to add more");
            return;
        }
        code = MyTools.getAString("Enter Code: ", "Wrong format");

        for (int i = 0; i < z; i++) {
            if (array[i].getCode().equalsIgnoreCase(code)) {
                model = MyTools.getAString("Input model:", "Invalid");
                price = MyTools.getADouble("Input price:", "Invalid");
                array[i].setModel(model);
                array[i].setPrice(price);
                System.out.println("Result");
                array[i].showInfo();

                return;
            }
        }
    }

    public void search() {
        // Print the object details after searching
        String code;
        if (z == 0) {
            System.out.println("Array list is empty.");
            return;
        }
        code = MyTools.getAString("Enter Code: ", "Wrong format");
        for (int i = 0; i < z; i++) {
            if (array[i].getCode().equalsIgnoreCase(code)) {
                array[i].toString();
                return;
            }
        }
        System.out.println("Not Found!");
    }

    public void remove() {
        // Print the list after removing

    }

    //Added by Team SE1267
    public void printAll() {
        for (int i = 0; i < z; i++) {
            array[i].toString();
        }
    }
    //

    public void sort() {
        for (int i = 0; i < z; i++) {
            for (int j = i + 1; j < z; j++) {
                if (array[i].getAmount() > array[j].getAmount()) {
                    array[z] = array[i];
                    array[i] = array[j];
                    array[j] = array[z];
                }
            }
        }
        array[z] = array[z + 1];
        printAll();
    }

}
