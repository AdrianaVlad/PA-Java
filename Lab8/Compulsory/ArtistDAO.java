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
public class ArtistDAO {
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into artists values (?,?)")) {
            pstmt.setString(2, name);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(id) from artists");
            rs.next();
            int newid = rs.getInt(1);
            newid++;
            pstmt.setInt(1, newid);
            pstmt.executeUpdate();
        }
    }

    public Artist findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select id from artists where name='" + name + "'")) {
            if(rs.next()){
                return new Artist(rs.getInt(1),name);
            }
            return null;
        }
    }

    public Artist findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select name from artists where id=" + id)) {
            if(rs.next()){
                return new Artist(id,rs.getString(1));
            }
            return null;
        }
    }

}
