/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.tblShedulerDAO;

import com.practicalexam.student.connection.DBUtilities;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
/**
 *
 * @author Le Ngoc Tan
 */
public class ShedulerDAO {
   public int searchDoctorShedule(Date from, Date to, String doctorId) throws ClassNotFoundException, NamingException, SQLException{
       Connection con = null;
       PreparedStatement pStm = null;
       ResultSet rs = null;
       int row = 0;
       try{
           con = DBUtilities.makeConnection();
           if (con != null) {
               String sql = "Select id, shiftDate, fromDate, dateTime, description, doctorId "
                       + "From tbl_Sheduler "
                       + "Where fromDate = ?, dateTime = ?, doctorId = ?";
               pStm = con.prepareStatement(sql);
               pStm.setDate(1, from);
               pStm.setDate(2, to);
               pStm.setString(3, doctorId);
               rs = pStm.executeQuery();
               
               while(rs.next()){
                   Date fromDate = rs.getDate("fomDate");
               row++;
               }
           }
       }finally{
           
       }
       return row;
   }  
}
