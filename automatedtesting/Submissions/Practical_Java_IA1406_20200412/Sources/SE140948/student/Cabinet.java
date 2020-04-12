package com.practicalexam.student;

import com.practicalexam.student.data.Shoe;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
    private ArrayList<Shoe> list = new ArrayList();
    //EndList - do not remove this comment!!!
    Scanner scannerObj = new Scanner(System.in);

    public void add() {
        String code, model;
        int size;
        double price;
        System.out.println("Enter code:");
        code = scannerObj.nextLine();
        System.out.println("Enter model: ");
        model = scannerObj.nextLine();
        System.out.println("Enter size: ");
        size = Integer.parseInt(scannerObj.nextLine());
        System.out.println("Enter price: ");
        price = Double.parseDouble(scannerObj.nextLine());
        //Added by Team SE1267
        Shoe addNew = new Shoe(code, model, price, size);
        //
        list.add(addNew);
        System.out.println("Added");
        addNew.showProfile(); //Added
    }

    public boolean checkDuplicatedId(String id) {
        if (list.isEmpty()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCode().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return true;
    }

    public void update() {
        // Print the object details after updating name/model and price

    }

    public void search() {
        String code;
        System.out.println("enter code: ");
        code = scannerObj.nextLine();
        if (list.size() == 0) {
            System.out.println("list empty");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCode().equalsIgnoreCase(code)) {
                //Added by Team SE1267
                list.get(i).showProfile();
                //
                return;
            }
        }
        System.out.println("Can not find");
    }

    public void remove() {
        String code;
        int tmp;
        System.out.println("enter code");
        code = scannerObj.nextLine();
        if (list.size() == 0) {
            System.out.println("list empty");
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCode().equalsIgnoreCase(code)) {
                //Added by Team SE1267
                Shoe removeShoe = list.get(i);
                list.remove(removeShoe);
                //removeShoe.showProfile();
            }
        }
        printAll();

    }

    public void printAll() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).showProfile();
        }

    }

    public void sort() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).showProfile();
        }
    }

}
