/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author ADMIN
 */
public class Test {

    static int fun(int n) {
        if (n == 0) {
            return 1;

        } else {
            return (n + fun(n / 2));
        }
    }

    public static void main(String[] args) {
        // Object obj = new Integer(42);
        //Object obj = new Double(42.0)
//        Object obj = new Integer(42);
//        String s = (String) obj;
        //  Stack sk = new Stack();
        Deque sk = new ArrayDeque();
        sk.push(1);
        sk.push(2);
        sk.push(3);
        sk.push(4);

        while (!sk.isEmpty()) {
            sk.push(sk.pop());
        }
        for (int i = 0; i < sk.size(); i++) {
            sk.getFirst();
        }
    }
}
