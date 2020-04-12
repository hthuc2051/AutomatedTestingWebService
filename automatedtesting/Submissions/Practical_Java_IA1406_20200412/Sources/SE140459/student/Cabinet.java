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
    private Shoes ds[] = new Shoes[20];
    //EndList - do not remove this comment!!!
    String code, model;
    int size;
    double price;
    private int count;
    Scanner scannerObj = new Scanner(System.in);

    public void add() {
        // Print the object details after adding

        System.out.println("Input shoes no #"
                + (count + 1) + "/" + ds.length);
        //Input student no 1/5, lừa đảo con só, mảng đếm từ 0, mình mời nhập 1
        System.out.println("Input code: ");
        code = scannerObj.nextLine();

        System.out.println("Input model: ");
        model = scannerObj.nextLine();

        System.out.println("Input size: ");
        size = Integer.parseInt(scannerObj.nextLine());  //chưa try-catch

        System.out.println("Input prize: ");
        price = Double.parseDouble(scannerObj.nextLine()); //chưa try-catch

        ds[count] = new Shoes(code, model, size, price);
        //Added by Team SE1267
        ds[count].showProfile();
        //
        count++;
    }

    public boolean checkDuplicatedId(String id) {
        // Your code here
        for (Shoes x : ds) {
            if (x.getCode() == id) {
                x.showProfile();
                //forgot return
            } else {
                System.out.println("There are not duplicated ID");
            }
        }

        return true;
    }

    public void update() {
        // Print the object details after updating name/model and price
        for (Shoes x : ds) {
            if (count < 20) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Input model:");
                model = sc.nextLine();
                System.out.println("Input price");
                price = Double.parseDouble(sc.nextLine());
                x.showProfile();
                //Wrong
            }
            if (count == 20) {
                System.out.println("The list is full");
            }
        }
    }

    public void search() {
        // Print the object details after searching
        Scanner sc = new Scanner(System.in);
        code = sc.nextLine();
        for (Shoes x : ds) {
            if (x.getCode() == code) {
                x.showProfile();
            } else {
                System.out.println("There has not this code");
            }
        }
    }

    public void remove() {
        // Print the list after removing
        Scanner sc = new Scanner(System.in);
        System.out.println("Input your code want to remove: ");
        code = sc.nextLine();
        for (Shoes x : ds) {
            if (x.getCode() == code) {
                //Not done yet.
            }
        }
    }

    public void sort() {
        // Print the object details after sorting
        //Not done yet.

    }

}
