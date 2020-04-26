package com.practicalexam.student;

import java.io.Serializable;

public class TemplateQuestion implements Serializable {

    public static boolean checkLogin(String username, String password) {
        boolean check = false;
        try {
            // Student Call function
            
            // check = objectDAO.checkLogin(username, password);
            
            //
        } catch (Exception e) {
        }
        return check;
    }

    public static int searchByLastname(String lastname) {
        int result = -1;
        try {
            // Student call function
            
            // result = objectDAO.searchLastname(lastname).size();
            
            //
        } catch (Exception e) {
        }
        return result;
    }

    public static boolean deleteAccount(String username) {
        boolean check = false;
        try {
            // Student call function
            
            // check = objectDAO.deleteAccount(username);
            
            //
        } catch (Exception e) {
        }
        return check;
    }

    public static boolean insertAccount(String username, String password, String lastName, Boolean isAdmin) {
        boolean check = false;
        try {
            // Student call function
            
            // ObjectDTO dto = new ObjectDTO(String username, String password, String lastName, Boolean isAdmin);
            // check = objectDAO.insertAccount(dto);
            
            //
        } catch (Exception e) {
        }
        return check;
    }

    public static boolean updateAccount(String username, String password, String lastName, Boolean isAdmin) {
        boolean check = false;
        try {
            // Student call function
            
            // ObjectDTO dto = new ObjectDTO(String username, String password, String lastName, Boolean isAdmin);
            // check = objectDAO.updateAccount(dto);
            
            //
        } catch (Exception e) {
        }
        return check;
    }
}
