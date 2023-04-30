/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Vlad Adriana
 */
public class Database {
    static HikariDataSource ds;
    private static Database instance = null;
    private static Connection connection = null;
    private Database() throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(100);
        config.setAutoCommit(false);
        config.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
        config.addDataSourceProperty("serverName", "localhost");
        config.addDataSourceProperty("port", "1521");
        config.addDataSourceProperty("databaseName", "xe");
        config.addDataSourceProperty("user", "music");
        config.addDataSourceProperty("password", "music");
        config.addDataSourceProperty("driverType", "thin");
        ds=new HikariDataSource(config);
        connection = ds.getConnection();
    }
    public static Connection getConnection() throws SQLException{
        if (instance==null)
            instance = new Database();
        return connection;
    }
}
