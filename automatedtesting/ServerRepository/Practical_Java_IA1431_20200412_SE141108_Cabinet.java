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
    ArrayList<Templatee> list = new ArrayList();
    //EndList - do not remove this comment!!!
    Scanner sc = new Scanner(System.in);

    public void add() {
        // Print the object details after adding
        String id, name;
        int size;
        double price;

        do {
            System.out.print("Enter the ID:");
            id = sc.nextLine();
            if (!checkDuplicatedId(id)) {
                System.out.println("Your ID is duplicated!");
            }
        } while (!checkDuplicatedId(id));
        System.out.print("Name of cake : ");
        name = sc.nextLine();

        System.out.print(" Size of cake : ");
        size = Integer.parseInt(sc.nextLine());

        System.out.print("Price of cake : ");
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
            System.out.println("ID you want to update:");

            id = sc.nextLine();
            if (checkDuplicatedId(id)) {
                System.out.println("Wrong Code!");
            }
        } while (checkDuplicatedId(id));

        System.out.print("Name for your cake : ");
        name = sc.nextLine();
        System.out.println("Price for your cake : ");
        price = Double.parseDouble(sc.nextLine());
        for (int i = 0; i < list.size(); i++) {
            if (id.equals((list.get(i)).getId())) {
                list.get(i).setName(name);
                list.get(i).setPrice(price);
                list.get(i).print();
            }
        }
        //System.out.println("updated!!!");

    }

    public void search() {
        // Print the object details after searching
        if (list.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }
        String id;
        int count = 0;
        System.out.print("ID you want to search :");
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
        String removeCode;
        if (list.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }
        do {
            System.out.println("ID you want to remove:");

            removeCode = sc.nextLine();
            if (checkDuplicatedId(removeCode)) {
                System.out.println("Wrong Code!");
            }
        } while (checkDuplicatedId(removeCode));
        for (int i = 0; i < list.size(); i++) {
            if (removeCode.equals((list.get(i)).getId())) {
                list.remove(i);
            }
        }
        System.out.println("deleted!!!!");
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
        System.out.println(" sorted!!!");
        for (int i = 0; i < list.size(); i++) {
            list.get(i).print();
        }
    }
}
