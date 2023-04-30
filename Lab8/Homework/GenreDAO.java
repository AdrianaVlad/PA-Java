/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;

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
public class GenreDAO extends TableDAO<Genre>{

    @Override
    public void create(Genre line) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into genres values (?,?)")) {
            pstmt.setInt(1, line.id);
            pstmt.setString(2, line.name);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Genre> findAll() throws SQLException {
        Connection con = Database.getConnection();
        List<Genre> lines = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from genres")) {
            while(rs.next()){
                lines.add(new Genre(rs.getInt(1),rs.getString(2)));
            }
            rs.close();
            return lines;
        }
    }

    @Override
    public Genre findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        name = name.replaceAll("\'", "''");
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from genres where name='" + name + "'")) {
            if(rs.next()){
                var genre =new Genre(rs.getInt(1),rs.getString(2));
                rs.close();
                return genre;
            }
            rs.close();
            return null;
        }
    }

    @Override
    public Genre findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from genres where id=" + id)) {
            if(rs.next()){
                var genre =new Genre(rs.getInt(1),rs.getString(2));
                rs.close();
                return genre;
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
        try (ResultSet rs = stmt.executeQuery("select max(id) from genres")) {
            rs.next();
            newid = rs.getInt(1);
        }
        return newid+1;
    }
    /*
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
            rs.close();
        }
    }

    public Genre findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        name = name.replaceAll("\'", "''");
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select id from genres where name='" + name + "'")) {
            if(rs.next()){
                var genre = new Genre(rs.getInt(1),name);
                rs.close();
                return genre;
            }
            rs.close();
            return null;
        }
    }

    public Genre findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select name from genres where id=" + id)) {
            if(rs.next()){
                var genre = new Genre(id,rs.getString(1));
                rs.close();
                return genre;
            }
            rs.close();
            return null;
        }
    }
*/
}
