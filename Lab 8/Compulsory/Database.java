/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compulsory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vlad Adriana
 */
public class Database {
    private static final String URL
            = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "music";
    private static final String PASSWORD = "music";
    private static Connection connection = null;
    private static Database instance = null;
    private Database() {
        createConnection();
    }
    public static Connection getConnection(){
        if (instance==null)
            instance = new Database();
        return connection;
    }

    private static void createConnection() {
        try {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
