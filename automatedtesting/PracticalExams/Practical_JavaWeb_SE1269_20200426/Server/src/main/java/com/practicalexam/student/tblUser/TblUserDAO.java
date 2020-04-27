/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.tblUser;

import com.practicalexam.student.connection.DBUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 * @author Dell
 */
public class TblUserDAO {

    public boolean checkLogin(String username, String password) throws ClassNotFoundException, NamingException, SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "select userId from tbl_User where userId = ? and password = ?";
        try {
            int passwordInt = Integer.parseInt(password.trim());
            con = DBUtilities.makeConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, username);
            pstm.setInt(2, passwordInt);
            rs = pstm.executeQuery();

            if (rs.next()) {
                check = true;
            }

        } finally {

            if (con != null) {
                con.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (rs != null) {
                rs.close();
            }

        }
        return check;
    }

    public boolean checkBoss(String username) throws ClassNotFoundException, NamingException, SQLException {
        boolean check = false;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "select boss from tbl_User where userId = ?";
        try {
            con = DBUtilities.makeConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, username);
            rs = pstm.executeQuery();
            if (rs.next()) {
                if (rs.getBoolean("boss")) {
                    check = true;
                }
            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return check;
    }

    public String getFullName(String username) throws ClassNotFoundException, NamingException, SQLException {
        String result = "";
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "select fullName from tbl_User where userId = ?";

        try {

            con = DBUtilities.makeConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, username);
            rs = pstm.executeQuery();

            if (rs.next()) {
                result = rs.getString("fullName");
            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return result;
    }

}
