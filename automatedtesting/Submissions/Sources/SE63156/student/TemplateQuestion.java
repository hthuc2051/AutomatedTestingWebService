package com.practicalexam.student;

import com.practicalexam.student.tblDoctorDAO.DoctorDAO;

import java.io.Serializable;
import java.util.Date;

public class TemplateQuestion implements Serializable {

    public static boolean checkLogin(String username, String password) {
        boolean check = false;
        try {
            // Student Call function
            DoctorDAO dao = new DoctorDAO();
            check = dao.checkLogin(username, password);
            //
        } catch (Exception e) {
        }
        return check;
    }

    public static int searchDoctorSchedule(Date from, Date to, String doctorId) {
        int result = -1;
        try {
            // Student call function
            DoctorDAO dao = new DoctorDAO();
            result = dao.searchDoctorSchedule(from, to, doctorId);
            //
        } catch (Exception e) {
        }
        return result;
    }

    public static int getAllDoctorSchedule() {
        int result = -1;
        try {
            // Student call function
            DoctorDAO dao = new DoctorDAO();
            result = dao.getAllDoctorsWithSchedules();
            //
        } catch (Exception e) {
        }
        return result;
    }

}
