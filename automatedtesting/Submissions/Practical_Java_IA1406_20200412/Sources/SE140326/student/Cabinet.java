package com.practicalexam.student;

import com.practicalexam.student.data.Mytoys;
import com.practicalexam.student.data.Shoe;
import java.util.Scanner;

/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    private String label;
    private int count = 0;
    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
    private Shoe ds[] = new Shoe[100];
    //EndList - do not remove this comment!!!
    Scanner scannerObj = new Scanner(System.in);

    public Cabinet(String label) {
        this.label = label;
    }

    public Cabinet() {
    }

    public void add() {
        String code, model;
        double size, price;
        System.out.println("Input Code : ");
        code = scannerObj.nextLine();
        System.out.println("Input Model : ");
        model = scannerObj.nextLine();
        System.out.println("Input Size : ");
        size = scannerObj.nextDouble();
        System.out.println("Input Price : ");
        price = scannerObj.nextDouble();
        ds[count] = new Shoe(code, model, size, price);
        ds[count].showProfile();
        count++;
    }

    public boolean checkDuplicatedId(String code) {
        Scanner sc = new Scanner(System.in);
        code = Mytoys.getAString("Please input shoe's code: ", "Please try again!!");
        for (int i = 0; i < count; i++) {
            if (ds[i].getCode().compareToIgnoreCase(code) == 0) {
                return false;
            }
        }

        return true;
    }

    public void update() {
        String code, model;
        double price;
        Shoe x;
        System.out.println("Input Code : ");
        code = scannerObj.nextLine();
        x = seachOjectShoeById(code);
        if (x == null) {
            System.out.println("Can not find your shoes");
        } else {
            System.out.println("Here is your shoes before updating: ");
            x.showProfile();
            System.out.println("Input Model : ");
            model = scannerObj.nextLine();
            x.setModel(model);
            System.out.println("Input Price : ");
            price = scannerObj.nextDouble();
            x.setPrice(price);
            System.out.println("HERE IS YOUR SHOES AFTER UPDATING:");
            x.showProfile();
        }
    }

    public void search() {
        String code;
        Shoe x;
        System.out.println("Input Code : ");
        code = scannerObj.nextLine();
        x = seachOjectShoeById(code);
        if (x == null) {
            System.out.println("Not found !!!");
        } else {
            System.out.println("Here is you shoe: ");
            x.showProfile();
        }

    }

    public Shoe seachOjectShoeById(String code) {
        if (ds.length == 0) {
            return null;
        }
        for (int i = 0; i < count; i++) {
            if (ds[i].getCode().equalsIgnoreCase(code)) {
                return ds[i];
            }
        }
        return null;
    }

    public void remove() {
        String code;
        Shoe x;
        System.out.println("Input Code : ");
        code = scannerObj.nextLine();
        x = seachOjectShoeById(code);
        for (int i = 0; i < count; i++) {
            if (ds[i].getCode().compareToIgnoreCase(code) == 0) {
                Shoe getShoe = ds[i]; //Added
                ds[i] = null;
                count--;
                getShoe.showProfile(); //Added
            } else if (ds[i].getCode() == null) {
                System.out.println("The laptop you want to remove does not exist !!");
            }
        }
    }

    public void sort() {
        System.out.println("The list of you shoes before sorting");
        for (int i = 0; i < count; i++) {
            ds[i].showProfile();
        }
        for (int i = 0; i < ds.length - 1; i++) {
            for (int j = i + 1; j < ds.length; j++) {
                if (ds[i].getModel().compareToIgnoreCase(ds[i].getModel()) > 0) {
                    Shoe tmp = ds[i];
                    ds[i] = ds[j];
                    ds[j] = tmp;
                }
            }
        }
        System.out.println("List of shoes ascending by model: ");
        for (int i = 0; i < count; i++) {
            ds[i].showProfile();
        }

    }

}
