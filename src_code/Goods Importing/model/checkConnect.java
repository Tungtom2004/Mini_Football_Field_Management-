package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class checkConnect {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Mini_Football_Field_Management;encrypt=false";
        String user = "sa"; // tài khoản SQL Server
        String password = "123456"; // mật khẩu

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Successful!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("Failed");
            e.printStackTrace();
        }
    }
}
