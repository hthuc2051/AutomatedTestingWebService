package com.practicalexam.student;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
    public ArrayList<Templatee> list = new ArrayList();
    //EndList - do not remove this comment!!!
    Scanner sc = new Scanner(System.in);

    public void add() {
        // Print the object details after adding
        String id, name;
        int size;
        double price;
        do {
            System.out.print("Enter the code:");
            id = sc.nextLine();
            if (!checkDuplicatedId(id)) {
                System.out.println("Your code is duplicated!");
            }
        } while (!checkDuplicatedId(id));
        System.out.print("Enter name of cake : ");
        name = sc.nextLine();
        System.out.print("Enter size of cake : ");
        size = Integer.parseInt(sc.nextLine());
        System.out.print("Enter price of cake : ");
        price = Double.parseDouble(sc.nextLine());
        if (checkDuplicatedId(id)) {
            Templatee dto = new Templatee(id, name, size, price);
            list.add(dto);
            dto.print();
        }

    }

    public boolean checkDuplicatedId(String id) {
        // Your code here
        for (int i = 0; i < list.size(); i++) {
            if (id.equals(list.get(i).getId())) {
                return false;
            }
        }
        return true;
    }

    public void update() {
        // Print the object details after updating name/model and price
        String id;
        String name;
        double price;
        if (list.isEmpty()) {
            System.out.println("nothing to update!");
            return;
        }
        do {
            System.out.println("Enter your code you want to update:");

            id = sc.nextLine();
            if (checkDuplicatedId(id)) {
                System.out.println("Wrong Code!");
            }
        } while (checkDuplicatedId(id));
        System.out.print("Enter new Name for your cake : ");
        name = sc.nextLine();
        System.out.println("Enter new Price for your cake : ");
        price = Double.parseDouble(sc.nextLine());
        for (int i = 0; i < list.size(); i++) {
            if (id.equals((list.get(i)).getId())) {
                list.get(i).setName(name);
                list.get(i).setPrice(price);
                list.get(i).print();//Added
            }
        }
        System.out.println("Your cake has been updated!");

    }

    public void search() {
        // Print the object details after searching
        if (list.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }
        String id;
        int count = 0;
        System.out.print("Enter the code that you want to search :");
        id = sc.nextLine();
        for (int i = 0; i < list.size(); i++) {
            if (id.equals((list.get(i)).getId())) {
                list.get(i).print();
                count++;
            }
        }
        if (count == 0) {
            System.out.println("NOT FOUND!");
        }
    }

    public void remove() {
        // Print the list after removing
        String id;
        if (list.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }
        do {
            System.out.println("Enter your code you want to remove:");

            id = sc.nextLine();
            if (checkDuplicatedId(id)) {
                System.out.println("Wrong Code!");
            }
        } while (checkDuplicatedId(id));
        for (int i = 0; i < list.size(); i++) {
            if (id.equals((list.get(i)).getId())) {
                list.remove(i);
                //Added
            }
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).print();
        }
    }

    public void sort() {
        // Print the object details after sorting
        if (list.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if ((list.get(i).getName()).compareTo((list.get(j).getName())) < 0) {
                    Templatee tmp;
                    tmp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, tmp);
                }
            }
        }
        System.out.println("Your Cake's list has been sorted!");
        for (int i = 0; i < list.size(); i++) {
            list.get(i).print();
        }
    }
}
