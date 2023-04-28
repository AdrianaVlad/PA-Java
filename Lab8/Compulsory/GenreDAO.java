/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compulsory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Vlad Adriana
 */
public class GenreDAO {
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into genres values (?,?)")) {
            pstmt.setString(2, name);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(id) from genres");
            rs.next();
            int newid = rs.getInt(1);
            newid++;
            pstmt.setInt(1, newid);
            pstmt.executeUpdate();
        }
    }

    public Genre findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select id from genres where name='" + name + "'")) {
            if(rs.next()){
                return new Genre(rs.getInt(1),name);
            }
            return null;
        }
    }

    public Genre findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select name from genres where id=" + id)) {
            if(rs.next()){
                return new Genre(id,rs.getString(1));
            }
            return null;
        }
    }

}
