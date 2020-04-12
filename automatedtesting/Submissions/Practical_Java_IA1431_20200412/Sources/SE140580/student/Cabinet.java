package com.practicalexam.student;

import com.practicalexam.student.data.Cake;
import java.util.Scanner;

/**
 *
 * @author FPT University - HCMC
 * Java OOP Practical Exam Template
 */

public class Cabinet {

    //StartList - do not remove this comment!!!
    
    // Declare ArrayList or Array here
    
    //EndList - do not remove this comment!!!
    
    
    private String name;
    private Cake ds[] = new Cake[5];
    private int count = 0;

    public Cabinet(String name) {
        this.name = name;
    }
    
    
    public void add() {
        // Print the object details after adding
    Scanner sc = new Scanner(System.in);
    String id, name;
    int size;
    double price;
    
        System.out.println("Input cake no#"  
               + (count + 1) + "/" + ds.length );
        System.out.println("Input id:");
        id = sc.nextLine();
        System.out.println("Input name:");
        name = sc.nextLine();
        System.out.println("Input size:");
        size = Integer.parseInt(sc.nextLine());
        System.out.println("Input price:");
        price = Double.parseDouble(sc.nextLine());
        
        ds[count] = new Cake(id, name, size, price);
        count ++;
                
    }

    public boolean checkDuplicatedId(String id) {
        // Your code here
        
        return true;
    }

    public Cake update(Cake[] ds,String id) {
        // Print the object details after updating name/model and price
        for (Cake x : ds) {
            if(x.getId().equalsIgnoreCase(id) == true)
                return x;
        }
        return null;
    }

    public Cake search(String id) {
        // Print the object details after searching
        
        for (Cake x : ds) {
            if (x.getId().equalsIgnoreCase(id) == true)
               
              return x;
        }
         return null;
    }
    
    public void remove() {
        // Print the list after removing

    }

    public Cake sort(Cake[] ds) {
        // Print the object details after sorting
        for (int i = 0; i < ds.length - 1; i++) {
            for (int j = i + 1; j < ds.length; j++) {
               if(ds[i].getName() > ds[j].getName()) 
                   Cake t;
               
               
               
            }
        }
    }
    
}
