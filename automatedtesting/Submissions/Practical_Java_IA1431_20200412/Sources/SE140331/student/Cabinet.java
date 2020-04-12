package com.practicalexam.student;

import com.practicalexam.student.data.Cake;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
    private ArrayList<Cake> listCake = new ArrayList();
    //EndList - do not remove this comment!!!
    private Scanner scannerObj = new Scanner(System.in);

    public void add() {
        // Print the object details after adding
        String id, name;
        int size;
        double price;
        System.out.print("Input id: ");
        id = scannerObj.nextLine().trim();
        System.out.print("Input name: ");
        name = scannerObj.nextLine().trim();
        System.out.print("Input size: ");
        size = Integer.parseInt(scannerObj.nextLine());
        System.out.print("Input price: ");
        price = Double.parseDouble(scannerObj.nextLine());
        Cake x = new Cake(id, name, size, price);
        listCake.add(x);
        x.showProfile();
    }

    public boolean checkDuplicatedId(String id) {
        // Your code here
        if (listCake.isEmpty()) {
            return false;
        }
        for (int i = 0; i < listCake.size(); i++) {
            if (listCake.get(i).getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return true;
    }

    public void update() {
        // Print the object details after updating name/model and price
        String id;
        String name;
        double price;
        Cake x;
        System.out.print("Input id of Cake want to update: ");
        id = scannerObj.nextLine().trim();
        x = searchCakeById(id);
        if (x == null) {
            System.out.println("Can not found this Cake.");
        } else {
            System.out.println("The Cake before update");
            x.showProfile();
            System.out.print("Update name: ");
            name = scannerObj.nextLine().trim();
            x.setName(name);
            System.out.print("Update price: ");
            price = Double.parseDouble(scannerObj.nextLine());
            x.setPrice(price);
            System.out.println("Update finish.");
            System.out.println("The Cake after update new name and price: ");
            x.showProfile();
        }
    }

    public void search() {
        // Print the object details after searching
        String id;
        Cake x;
        System.out.print("Input id want to search: ");
        id = scannerObj.nextLine().trim();
        x = searchCakeById(id);
        if (x == null) {
            System.out.println("Can not found this Cake.");
        } else {
            System.out.println("The Cake want to search:");
            x.showProfile();
        }
    }

    public Cake searchCakeById(String idCake) {
        if (listCake.isEmpty()) {
            return null;
        }
        for (int i = 0; i < listCake.size(); i++) {
            if (listCake.get(i).getId().equalsIgnoreCase(idCake)) {
                return listCake.get(i);
            }
        }
        return null;
    }

    public void remove() {
        // Print the list after removing
        String id;
        int index;
        System.out.print("Input id of Cake want to remove: ");
        id = scannerObj.nextLine().trim();
        while (id.length() == 0 || id.isEmpty()) {
            System.out.print("Input id again: ");
            id = scannerObj.nextLine().trim();
        }
        index = searchCakeToRemove(id);
        if (index == -1) {
            System.out.println("Can not found this Cake to remove.");
        } else {
            listCake.remove(index);
            // cake.showProfile(); //Added
            System.out.println("The Cake is removed.");
        }
        for (int i = 0; i < listCake.size(); i++) {
            listCake.get(i).showProfile();
        }
    }

    public int searchCakeToRemove(String idCake) {
        if (listCake.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < listCake.size(); i++) {
            if (listCake.get(i).getId().equalsIgnoreCase(idCake)) {
                return i;
            }
        }
        return -1;
    }

    public void sort() {
        // Print the object details after sorting
        if (listCake.isEmpty()) {
            System.out.println("The list is empty. Have no Cake to show.");
        }
        Collections.sort(listCake);
        Collections.reverse(listCake);
        System.out.println("The list of Cake desccending by name");
        for (int i = 0; i < listCake.size(); i++) {
            listCake.get(i).showProfile();
        }
    }

}
