package com.practicalexam.student;



import java.io.Serializable;

public class TemplateQuestion implements Serializable {

    private static TblUserDAO userDao = new TblUserDAO();
    private static TblWeaponDAO weaponDao = new TblWeaponDAO();

    public static boolean checkLogin(String username, String password) {
        boolean check = false;
       
        return check;
    }

    public static boolean delete(String armorId) {
        boolean check = false;
       
        return check;
    }

    public static int showAll() {
        int result = -1;
       

        return result;
    }

}
