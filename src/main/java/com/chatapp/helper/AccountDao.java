/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatapp.helper;

import com.chatapp.model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class AccountDAO {
     public Account findAccountDAO(String username) throws Exception {
        String sql = "select * from account where username=?";
        try (
                 Connection con = connectMySQL.getConnection();  PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setString(1, username);

            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                Account act = new Account(rs.getString(1), rs.getString(2), rs.getString(3));
                return act;
            }
        }
        return null;
    }

    public boolean saveAccountDAO(Account acc) throws Exception {
        String sql = "insert into account values(?,?,?)";
        try (
                 Connection con = connectMySQL.getConnection();  PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setString(1, acc.getUserName());
            psmt.setString(2, acc.getPassword());
            psmt.setString(3, acc.getAvatar());

            if (psmt.executeUpdate() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean updateAccountDAO(Account acc) throws Exception {
        String sql = "update account set password=?, avatar=? where username=?";
        try (
                 Connection con = connectMySQL.getConnection();  PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setString(1, acc.getPassword());
            psmt.setString(2, acc.getAvatar());
            psmt.setString(3, acc.getUserName());
            if (psmt.executeUpdate() > 0) {
                return true;
            }

            if (psmt.executeUpdate() > 0) {
                return true;
            }
        }
        return false;
    }
    
}
