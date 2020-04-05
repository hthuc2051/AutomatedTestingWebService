/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.tblDoctorDAO;

import com.practicalexam.student.connection.DBUtilities;
import com.practicalexam.student.tblDoctorDTO.DoctorDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Le Ngoc Tan
 */
public class DoctorDAO implements Serializable {

    private List<DoctorDTO> listDoctors;

    public List<DoctorDTO> getListDoctors() {
        return this.listDoctors;
    }

    private List<DoctorDTO> listDoctorsSearch;

    public List<DoctorDTO> getListDoctorsSearch() {
        return this.listDoctorsSearch;
    }

    public boolean checkLogin(String username, String password)
            throws ClassNotFoundException, NamingException, SQLException {
        Connection con = null;
        PreparedStatement pStm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT doctorId "
                        + "FROM tbl_Doctor "
                        + "WHERE doctorId = ? AND password = ? AND leader = ?";
                pStm = con.prepareStatement(sql);
                pStm.setString(1, username);
                pStm.setString(2, password);
                pStm.setString(3, "true");
                rs = pStm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (pStm != null) {
                pStm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }

    public String getFullnameFromDoctorId(String doctorId)
            throws ClassNotFoundException, NamingException, SQLException {
        Connection con = null;
        PreparedStatement pStm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT fullName "
                        + "FROM tbl_Doctor "
                        + "WHERE doctorId = ?";
                pStm = con.prepareStatement(sql);
                pStm.setString(1, doctorId);
                rs = pStm.executeQuery();
                if (rs.next()) {
                    return rs.getString("fullName");
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (pStm != null) {
                pStm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public int getAllDoctorsWithSchedules()
            throws ClassNotFoundException, NamingException, SQLException {
        Connection con = null;
        PreparedStatement pStm = null;
        ResultSet rs = null;

        if (this.listDoctors == null) {
            this.listDoctors = new ArrayList<>();
        } else {
            this.listDoctors.clear();
        }

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT D.doctorId, "
                        + "D.fullName, "
                        + "D.specialization, "
                        + "D.phoneNumber, "
                        + "D.[address], "
                        + "D.leader, "
                        + "S.id, "
                        + "S.shiftDate, "
                        + "S.fromTime, "
                        + "S.[dateTime], "
                        + "S.[description] "
                        + "FROM tbl_Doctor D INNER JOIN tbl_Scheduler S ON D.doctorId=S.doctorId ";
                pStm = con.prepareStatement(sql);
                rs = pStm.executeQuery();
                while (rs.next()) {

                    // Doctor Information
                    String doctorId = rs.getString("doctorId");
                    String fullName = rs.getString("fullName");
                    String specialization = rs.getString("specialization");
                    String phoneNumber = rs.getString("phoneNumber");
                    String address = rs.getString("address");
                    boolean isLeader = rs.getBoolean("leader");

                    // Scheduler Information
                    int id = rs.getInt("id");
                    Date shiftDate = rs.getDate("shiftDate");
                    Date fromTime = rs.getDate("fromTime");
                    Date dateTime = rs.getDate("dateTime");
                    String description = rs.getString("description");

                    DoctorDTO doctor = new DoctorDTO(doctorId, fullName, specialization, phoneNumber, address, isLeader, id, shiftDate, fromTime, dateTime, description);

                    this.listDoctors.add(doctor);
                }
                return this.listDoctors.size();
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (pStm != null) {
                pStm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return 0;
    }

    public int searchDoctorSchedule(Date from, Date to, String doctorId)
            throws ClassNotFoundException, NamingException, SQLException {
        Connection con = null;
        PreparedStatement pStm = null;
        ResultSet rs = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        if (this.listDoctorsSearch == null) {
            this.listDoctorsSearch = new ArrayList<>();
        } else {
            this.listDoctorsSearch.clear();
        }

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String fromDate = sdf.format(from);
                String toDate = sdf.format(to);
                String sql = "SELECT D.fullName, "
                        + "D.specialization, "
                        + "D.phoneNumber, "
                        + "S.shiftDate, "
                        + "S.fromTime, "
                        + "S.[dateTime], "
                        + "S.[description] "
                        + "FROM tbl_Doctor D INNER JOIN tbl_Scheduler S ON D.doctorId=S.doctorId "
                        + "WHERE (S.fromTime >= CONVERT(datetime,?) AND S.[dateTime] <= CONVERT(datetime,?)) AND D.doctorId = ?";
                pStm = con.prepareStatement(sql);
                pStm.setString(1, fromDate);
                pStm.setString(2, toDate);
                pStm.setString(3, doctorId);
                rs = pStm.executeQuery();
                while (rs.next()) {

                    // Doctor Information
                    String fullName = rs.getString("fullName");
                    String specialization = rs.getString("specialization");
                    String phoneNumber = rs.getString("phoneNumber");

                    // Scheduler Information
                    Date shiftDate = rs.getDate("shiftDate");
                    Date fromTime = rs.getDate("fromTime");
                    Date dateTime = rs.getDate("dateTime");
                    String description = rs.getString("description");

                    DoctorDTO doctor = new DoctorDTO(doctorId, fullName, specialization, phoneNumber, shiftDate, fromTime, dateTime, description);

                    this.listDoctorsSearch.add(doctor);
                }
                return this.listDoctorsSearch.size();
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (pStm != null) {
                pStm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return 0;
    }

}
