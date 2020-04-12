package com.practicalexam.student;

import com.practicalexam.student.data.Shoes;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author FPT University - HCMC Java OOP Practical Exam Template
 */
public class Cabinet {

    //StartList - do not remove this comment!!!
    // Declare ArrayList or Array here
    ArrayList<Shoes> list;
    //EndList - do not remove this comment!!!

    public Cabinet() {
        list = new ArrayList<>();
    }

    public void add() {
        Scanner sc = new Scanner(System.in);
        String code, model;
        int size = 0;
        double price = 0;
        do {
            System.out.println("Input code: ");
            code = sc.nextLine().toUpperCase();
            if (!checkDuplicatedId(code)) {
                System.out.println("\tDuplicated!");
            }
        } while (!checkDuplicatedId(code));

        System.out.println("Input Model: ");
        model = sc.nextLine().toUpperCase();
        boolean check = true;
        do {
            try {
                System.out.println("Input size: ");
                size = Integer.parseInt(sc.nextLine());
                System.out.println("Input price");
                price = Integer.parseInt(sc.nextLine());
                check = true;
            } catch (Exception e) {
                System.out.println("\tRequired Integer!");
                check = false;
            }
        } while (check == false);
        Shoes addNew = new Shoes(code, model, size, price);
        list.add(addNew);
        System.out.println("\tAdded!");
        //Added by Team SE1267
        addNew.showProfile();
        //

    }

    public boolean checkDuplicatedId(String id) {
        for (Shoes s : list) {
            if (s.getCode().equalsIgnoreCase(id)) {
                return false;
            }
        }
        return true;
    }

    public void update() {
        if (list.isEmpty()) {
            System.out.println("\tEmpty list");
            return;
        }
        Scanner sc = new Scanner(System.in);
        String code;
        System.out.println("Input code: ");
        code = sc.nextLine().toUpperCase();
        int pos = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCode().equalsIgnoreCase(code)) {
                pos = i;
            }
        }
        if (pos >= 0) {
            String model;
            int price = 0;
            System.out.println("New model: ");
            model = sc.nextLine().toUpperCase();
            boolean check = true;
            do {
                try {
                    System.out.println("New price: ");
                    price = Integer.parseInt(sc.nextLine());
                    check = true;
                } catch (Exception e) {
                    System.out.println("\tRequired Integer!");
                    check = false;
                }
            } while (check == false);
            list.get(pos).setModel(model);
            list.get(pos).setPrice(price);
            System.out.println("\tUpdated");
            //Added by Team SE1267
            list.get(pos).showProfile();
            //
        } else {
            System.out.println("Not exist this shoes");
        }
    }

    public void search() {
        // Print the object details after searching
        if (list.isEmpty()) {
            System.out.println("\tEmpty list");
            return;
        }
        Scanner sc = new Scanner(System.in);
        String code;
        System.out.println("Input code: ");
        code = sc.nextLine().toUpperCase();
        for (Shoes s : list) {
            if (s.getCode().equalsIgnoreCase(code)) {
                s.showProfile();
            }
        }
    }

    public void remove() {
        // Print the list after removing
        if (list.isEmpty()) {
            System.out.println("\tEmpty list");
            return;
        }
        Scanner sc = new Scanner(System.in);
        String code;
        System.out.println("Input Code: ");
        code = sc.nextLine().toUpperCase();
        int pos = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCode().equalsIgnoreCase(code)) {
                pos = i;
            }
        }
        if (pos > 0) {
            //Added by Team SE1267
            Shoes getShoes = list.remove(pos);
            getShoes.showProfile();
            //
        } else {
            System.out.println("\tNot exist");
        }
    }

    public void sort() {
        // Print the object details after sorting
        Collections.sort(list);
        Iterator<Shoes> ite = list.iterator();
        while (ite.hasNext()) {
            ite.next().showProfile();
        }
    }

}
