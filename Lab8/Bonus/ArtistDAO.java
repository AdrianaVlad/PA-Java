/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vlad Adriana
 */
public class ArtistDAO extends TableDAO<Artist>{

    @Override
    public void create(Artist line) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into artists values (?,?)")) {
            pstmt.setInt(1, line.id);
            pstmt.setString(2, line.name);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Artist> findAll() throws SQLException {
        Connection con = Database.getConnection();
        List<Artist> lines = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from artists")) {
            while(rs.next()){
                lines.add(new Artist(rs.getInt(1),rs.getString(2)));
            }
            rs.close();
            return lines;
        }
    }

    @Override
    public Artist findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        name = name.replaceAll("\'", "''");
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from artists where name='" + name + "'")) {
            if(rs.next()){
                var artist =new Artist(rs.getInt(1),rs.getString(2));
                rs.close();
                return artist;
            }
            rs.close();
            return null;
        }
    }

    @Override
    public Artist findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from artists where id=" + id)) {
            if(rs.next()){
                var artist =new Artist(rs.getInt(1),rs.getString(2));
                rs.close();
                return artist;
            }
            rs.close();
            return null;
        }
    }

    @Override
    public int findNextId() throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        int newid;
        try (ResultSet rs = stmt.executeQuery("select max(id) from artists")) {
            rs.next();
            newid = rs.getInt(1);
        }
        return newid+1;
    }
}
