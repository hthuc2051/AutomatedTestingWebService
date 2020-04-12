/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.data;

import java.util.Scanner;

/**
 *
 * @author MSI-PC
 */
public class Mytoys {
    private static  Scanner sc = new Scanner(System.in);
    
    
    public static String getAString(String msg, String errorMsg){
        String tmp;
        while (true) {
            System.out.print(msg);
            tmp = sc.nextLine().trim();            
            if (tmp.length() == 0 || tmp.isEmpty())
                System.out.println(errorMsg);
            else 
                return tmp;
        }
    }
    
    
    public static double getADouble(String msg, String errorMsg){
        double tmp;
        while (true) {
            try {
                System.out.print(msg);
                tmp = Double.parseDouble(sc.nextLine());    
                return tmp;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
        
    }

    
}
