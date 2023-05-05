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
public class PlaylistDAO extends TableDAO<Playlist>{

    @Override
    public void create(Playlist line) throws SQLException {
       Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into playlists values (?,?,?)")) {
            pstmt.setInt(1, line.id);
            pstmt.setString(2, line.name);
            pstmt.setTimestamp(3,line.creationTimestamp);
            pstmt.executeUpdate();
            con.close();
        }
    }

    @Override
    public List<Playlist> findAll() throws SQLException {
        Connection con = Database.getConnection();
        List<Playlist> lines = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from playlists")) {
            while(rs.next()){
                lines.add(new Playlist(rs.getInt(1),rs.getString(2),rs.getTimestamp(3)));
            }
            rs.close();
            con.close();
            return lines;
        }
    }

    @Override
    public Playlist findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        name = name.replaceAll("\'", "''");
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from playlists where name='" + name + "'")) {
            if(rs.next()){
                var playlist =new Playlist(rs.getInt(1),name,rs.getTimestamp(3));
                rs.close();
                con.close();
                return playlist;
            }
            rs.close();
            con.close();
            return null;
        }
    }

    @Override
    public Playlist findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from playlists where id=" + id)) {
            if(rs.next()){
                var playlist =new Playlist(id,rs.getString(2),rs.getTimestamp(3));
                rs.close();
                con.close();
                return playlist;
            }
            rs.close();
            con.close();
            return null;
        }
    }

    @Override
    public int findNextId() throws SQLException {
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        int newid;
        try (ResultSet rs = stmt.executeQuery("select max(id) from playlists")) {
            rs.next();
            newid = rs.getInt(1);
        }
        con.close();
        return newid+1;
    }
    
}
