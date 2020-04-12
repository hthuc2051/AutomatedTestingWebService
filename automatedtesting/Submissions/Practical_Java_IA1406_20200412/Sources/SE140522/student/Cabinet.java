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
    private ArrayList<Shoe> shoeList = new ArrayList();
    //EndList - do not remove this comment!!!
    Scanner scannerObj = new Scanner(System.in);

    public void add() {
        // Print the object details after adding
        String code, model;
        int size;
        double price;
        System.out.print("Input shoe code: ");
        code = scannerObj.nextLine();
        System.out.print("Input shoe model: ");
        model = scannerObj.nextLine();
        System.out.print("Input shoe size: ");
        size = Integer.parseInt(scannerObj.nextLine());
        System.out.print("Input shoe price: ");
        price = Double.parseDouble(scannerObj.nextLine());
        Shoe addNew = new Shoe(code, model, size, price);
        shoeList.add(addNew);
        System.out.println("Shoe is added.");
        // Added by Team SE1267
        addNew.showProfile();
        //

    }

    public boolean checkDuplicatedId(String id) {
        // Your code here

        return true;
    }

    public void update() {
        System.out.print("Input shoe code: ");
        String code = scannerObj.nextLine();
        // Print the object details after updating name/model and price
        for (Shoe x : shoeList) {
            if (x.getCode().equalsIgnoreCase(code)) {
                System.out.print("Input new shoe model: ");
                String model = scannerObj.nextLine();
                System.out.print("Input new shoe price: ");
                double price = Double.parseDouble(scannerObj.nextLine());
                x.setModel(model);
                x.setPrice(price);
                x.showProfile(); // Added by Team SE1267
            }
        }
    }

    public void search() {
        // Print the object details after searching
        System.out.print("Input shoe code: ");
        String code = scannerObj.nextLine();
        if (shoeList.isEmpty()) {
            System.out.println("No information for search");
        }
        for (int i = 0; i < shoeList.size(); i++) {
            if (shoeList.get(i).getCode().equalsIgnoreCase(code)) {
                shoeList.get(i).showProfile();
            } else {
                System.out.println("Not found");
            }
        }
    }

//    public void update(String code) {
//        // Print the object details after updating name/model and price
//        for (Shoe x : shoeList) {
//            if (x.getCode().equalsIgnoreCase(code)) {
//                x.input(scannerObj);
//                x.showProfile(); // Added by Team SE1267
//            }
//        }
//    }
//    public void search(String code) {
//        // Print the object details after searching
//        if (shoeList.isEmpty()) {
//            System.out.println("No information for search");
//        }
//        for (int i = 0; i < shoeList.size(); i++) {
//            if (shoeList.get(i).getCode().equalsIgnoreCase(code)) {
//                System.out.println(shoeList.get(i).toString());
//            } else {
//                System.out.println("Not found");
//            }
//        }
//    }
    public void remove() {
        // Print the list after removing

    }

    public void sort() {
        // Print the object details after sorting
        if (shoeList.isEmpty()) {
            System.out.println("The list is empty");
            return;
        }
        shoeList.sort(Shoe.sortShoeByModel);
        for (Shoe x : shoeList) {
            x.showProfile();
        }
    }

}
