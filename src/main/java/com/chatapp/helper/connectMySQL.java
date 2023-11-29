/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatapp.helper;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Admin
 */
public class connectMySQL {
    public static Connection getConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");  
        String connectionUrl = "jdbc:mysql://localhost/chatapp";
        String username = "root";
        String password = "Nqv@762003";
        Connection con = DriverManager.getConnection(connectionUrl, username, password);
        return con;
    }
}
