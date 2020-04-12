/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.data;

import java.util.Scanner;

/**
 *
 * @author trand
 */
public class MyToys {
    private static Scanner sc = new Scanner(System.in);
    
    public static int getAnInteger(String inputMsg, String errorMsg){
        int n;
        while(true){
            try{
                System.out.println(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n <= 0)
                    throw new Exception();
                return n;
            } catch (Exception e){
                System.out.println(errorMsg);
            }
        }
    }
    
    public static double getAnDouble(String inputMsg, String errorMsg){
        double n;
        while(true){
            try{
                System.out.println(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                
                if (n <= 0) 
                    throw new Exception();
                return n;
            } catch (Exception e){
                System.out.println(errorMsg);
            }
        }
    }
    
    public static String getCode(String inputMsg, String errorMsg){
        String id;
        while (true){
            System.out.println(inputMsg);
            id = sc.nextLine().trim().toLowerCase();
            if (id.length() == 0 || id.isEmpty())
                System.out.println(errorMsg);
            else 
                return id;
        }
    }
    
  
}
