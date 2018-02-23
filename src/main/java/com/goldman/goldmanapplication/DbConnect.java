/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goldman.goldmanapplication;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;


/**
 *
 * @author user
 */
public class DbConnect {
    
    private static final Logger log = Logger.getLogger(DbConnect.class.getName());
    
    public static void main(String args[]) {        
        try {            
            Class.forName("com.mysql.jdbc.Driver");            
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mysql", "root", "");
            log.info("Connected success");
//here system is database name, root is username and blank passwd  
            Statement stmt = con.createStatement();            
            ResultSet rs = stmt.executeQuery("select * from bookstore");            
            while (rs.next()) {
                log.info(rs.getString(1) + "  " + rs.getDate(2) + "  " + rs.getFloat(3) + " " + rs.getString(4) + " " + rs.getString(5));
            }            
            con.close();            
        } catch (Exception e) {
            System.out.println(e);
        }        
    }    
    
}
