package com.practicalexam.student;

import com.practicalexam.student.tblUser.TblUserDAO;
import com.practicalexam.student.tblWeapon.TblWeaponDAO;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class TemplateQuestion implements Serializable {

    private static TblUserDAO userDao = new TblUserDAO();
    private static TblWeaponDAO weaponDao = new TblWeaponDAO();

    public static boolean checkLogin(String username, String password) {
        boolean check = false;
        try {
            // Student Call function
            boolean checkLogin = userDao.checkLogin(username, password);
            if (checkLogin) {
                check = userDao.checkBoss(username);
            }
            //
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public static boolean delete(String armorId) {
        boolean check = false;
        try {
            // Student call function
            check = weaponDao.deleteAmour(armorId);
            //
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public static int showAll() {
        int result = -1;
        try {
            // Student call function
            result = weaponDao.showAll();
            //
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
