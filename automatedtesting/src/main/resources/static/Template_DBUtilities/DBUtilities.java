/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.connection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Kieu Trong Khanh
 */
public class DBUtilities implements Serializable {

    public static Connection makeConnection() {
        Connection con = null;
        // Add code get connection here
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "$url$";
            con = DriverManager.getConnection(url, $username$, $password$);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
        System.out.println(con);
        return con;
    }

}
