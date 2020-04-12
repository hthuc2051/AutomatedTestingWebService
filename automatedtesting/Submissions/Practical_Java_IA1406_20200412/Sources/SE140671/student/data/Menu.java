/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.data;

import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class Menu {
    String[] hints;
    int n = 0;
// current number of hints 
// create a menu with site elements 

    public Menu(int size) {
        if (size < 20) {
            size = 20;
        }
        hints = new String[size];
    }
    public int getChoice() {
        int result = 0;
            System.out.print("Please select an operation(1 - 5): ");
            Scanner sc = new Scanner(System.in);
            result = Integer.parseInt(sc.nextLine());
        return result;
    }
} 
    

