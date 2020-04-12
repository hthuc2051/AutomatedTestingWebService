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
public class MyToys {

        private static Scanner sc = new Scanner(System.in);

        public static int getAnInteger(String inputMsg, String errorMsg) {
            int n;
            while (true) {
                try {
                    System.out.print(inputMsg);
                    n = Integer.parseInt(sc.nextLine());
                    return n;
                } catch (Exception e) {
                    System.out.println(errorMsg);
                }
            }
        }

        public static int getAnInteger(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
            int n, tmp;
                        if (lowerBound > upperBound) {
                tmp = lowerBound;
                lowerBound = upperBound;
                upperBound = tmp;
            }
            while (true) {
                try {
                    System.out.print(inputMsg);
                    n = Integer.parseInt(sc.nextLine());
                    if (n < lowerBound || n > upperBound) {
                        throw new Exception();
                    }
                    return n;
                } catch (Exception e) {
                    System.out.println(errorMsg);
                }
            }
        }

  
        public static double getADouble(String inputMsg, String errorMsg) {
            double n;
            while (true) {
                try {
                    System.out.print(inputMsg);
                    n = Double.parseDouble(sc.nextLine());
                    return n;
                } catch (Exception e) {
                    System.out.println(errorMsg);
                }
            }
        }

        public static double getADouble(String inputMsg, String errorMsg, double lowerBound, double upperBound) {
            double n, tmp;
         
            if (lowerBound > upperBound) {
                tmp = lowerBound;
                lowerBound = upperBound;
                upperBound = tmp;
            }
            while (true) {
                try {
                    System.out.print(inputMsg);
                    n = Double.parseDouble(sc.nextLine());
                    if (n < lowerBound || n > upperBound) {
                        throw new Exception();
                    }
                    return n;
                } catch (Exception e) {
                    System.out.println(errorMsg);
                }
            }
        }


 
        public static String getID(String inputMsg, String errorMsg, String format) {
            String id;
            boolean match;
            while (true) {
                System.out.print(inputMsg);
                id = sc.nextLine().trim().toUpperCase();
                match = id.matches(format);
                if (id.length() == 0 || id.isEmpty() || match == false) {
                    System.out.println(errorMsg);
                } else {
                    return id;
                }
            }
        }

 
        public static String getString(String inputMsg, String errorMsg) {
            String id;
            while (true) {
                System.out.print(inputMsg);
                id = sc.nextLine().trim();
                if (id.length() == 0 || id.isEmpty()) {
                    System.out.println(errorMsg);
                } else {
                    return id;
                }
            }
        }

        public static void main(String[] args) {
            String id = getID("Input ID(DXXXXX): ", "Your input must be under "
                    + "the format of DXXXXX, X stands for a digit",
                    "^[D|d]\\d{5}$");
            System.out.println("Your ID: " + id);
        }
    }

