/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.tblWeapon;

import com.practicalexam.student.connection.DBUtilities;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Dell
 */
public class TblWeaponDAO {
    
    List<TblWeaponDTO> listWeapon;

    public List<TblWeaponDTO> getListWeapon() {
        return listWeapon;
    }
    
    public int showAll() throws ClassNotFoundException, NamingException, SQLException {
        
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        String sql = "select amourId, description, classification, defense, timeOfCreate, status from Tbl_Weapon";
        
        try {
            
            con = DBUtilities.makeConnection();
            stmt = con .createStatement();
            rs = stmt.executeQuery(sql);
            
            int rowEffect = 0;
            
            while (rs.next()) {                
                
                rowEffect++;
                String amourId, description, classification, defense;
                Date timeOfCreate;
                boolean status;
                
                amourId = rs.getString("amourId");
                description = rs.getString("description");
                classification = rs.getString("classification");
                defense = rs.getString("defense");
                timeOfCreate = rs.getDate("timeOfCreate");
                status = rs.getBoolean("status");
                
                if (listWeapon == null) {
                    listWeapon = new ArrayList<>();
                }
                
                this.listWeapon.add(new TblWeaponDTO(amourId, description, classification, defense, timeOfCreate, status));
                
            }
            return rowEffect;
            
        } finally{
            
            if (con != null) {
                con.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            
        }
    }
    public boolean deleteAmour(String amourId) throws ClassNotFoundException, NamingException, SQLException {
        
        // Connection con = null;
        // PreparedStatement pstm = null;
        // try {
        //     String sql = "delete from tbl_Weapon where amourId = ?";
            
        //     con = DBUtilities.makeConnection();
        //     pstm = con.prepareStatement(sql);
        //     pstm.setString(1, amourId);
        //     int rowEffect = 0;
            
        //     rowEffect = pstm.executeUpdate();
        //     if (rowEffect > 0 ) {
                
        //         return true;
                
        //     }
            
        // } finally {
            
        //     if (con != null) {
        //         con.close();
        //     }
        //     if (pstm != null) {
        //         pstm.close();
        //     }
        // }
        return false;
    }
    
}
