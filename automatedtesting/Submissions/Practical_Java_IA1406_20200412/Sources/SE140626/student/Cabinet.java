package com.practicalexam.student;

import com.practicalexam.student.data.MyToys;
import com.practicalexam.student.data.Shoes;
import java.util.Scanner;

/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    int count = 0;
    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
    public Shoes ds[] = new Shoes[50];
    //EndList - do not remove this comment!!!

    public void add() {

        Scanner scannerObj = new Scanner(System.in);
        String code, model;
        int size;
        double price;

        System.out.println("Input Shoes no #"
                + (count + 1) + "/" + ds.length);

        System.out.print("Input code: ");
        code = scannerObj.nextLine();

        System.out.print("Input mode: ");
        model = scannerObj.nextLine();

        System.out.print("Input Size : ");

        size = Integer.parseInt(scannerObj.nextLine());

        System.out.print("Input price: ");

        price = Double.parseDouble(scannerObj.nextLine());
        Shoes addNew = new Shoes(code, model, size, price); //Added
        ds[count] = addNew;
        ds[count].showAll(); //Added
        count++;
    }

    public boolean checkDuplicatedId(String id) {
        // Your code here

        return true;
    }

    public void update() {
        String code2;
        String model2;
        double price2;
        if (count == 0) {
            System.out.println("EMPTY. TRY AGAIN !!!");
            return;
        }
        code2 = MyToys.getID("Input Code: ", "OK", "???");

        for (int i = 0; i < count; i++) {

        }
    }

    public void search() {
//        String code;
//        Shoes x;
//        System.out.println("Input : ");
//        x = MyToys.getString("Input : ");
//        x = searchPetObjectByID(id);

    }

    public void remove() {
        // Print the list after removing

    }

    public void sort() {

    }

}
