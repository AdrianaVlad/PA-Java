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
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vlad Adriana
 */
public class AlbumDAO extends TableDAO<Album>{

    @Override
    public void create(Album line) throws SQLException{
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into albums values (?,?,?,?)")) {
            pstmt.setInt(1, line.id);
            pstmt.setInt(2, line.releaseYear);
            pstmt.setString(3,line.title);
            pstmt.setInt(4,line.artistId);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Album> findAll() throws SQLException{
        Connection con = Database.getConnection();
        List<Album> lines = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from albums")) {
            while(rs.next()){
                lines.add(new Album(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4)));
            }
            rs.close();
            return lines;
        }
    }
    
    @Override
    public Album findByName(String title) throws SQLException{
        Connection con = Database.getConnection();
        title = title.replaceAll("\'", "''");
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from albums where title='" + title + "'")) {
            if(rs.next()){
                var album =new Album(rs.getInt(1),rs.getInt(2),title,rs.getInt(4));
                rs.close();
                return album;
            }
            rs.close();
            return null;
        }
    }
    
    @Override
    public Album findById(int id) throws SQLException{
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from albums where id=" + id)) {
            if(rs.next()){
                var album =new Album(id,rs.getInt(2),rs.getString(3),rs.getInt(4));
                rs.close();
                return album;
            }
            rs.close();
            return null;
        }
    }

    @Override
    public int findNextId() throws SQLException{
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        int newid;
        try (ResultSet rs = stmt.executeQuery("select max(id) from albums")) {
            rs.next();
            newid = rs.getInt(1);
        }
        return newid+1;
        
    }
    
    /*
    public void create(int releaseYear,String title, String artist, String genres) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into albums values (?,?,?,?)")) {
            pstmt.setInt(2, releaseYear);
            pstmt.setString(3,title);
            var artists = new ArtistDAO();
            pstmt.setInt(4,artists.findByName(artist).id);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(id) from albums");
            rs.next();
            int newid = rs.getInt(1);
            newid++;
            pstmt.setInt(1, newid);
            pstmt.executeUpdate();
            setGenres(newid,genres);
            rs.close();
        }
    }

    public Album findByTitle(String title) throws SQLException {
        Connection con = Database.getConnection();
        title = title.replaceAll("\'", "''");
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from albums where title='" + title + "'")) {
            if(rs.next()){
                var album =new Album(rs.getInt(1),rs.getInt(2),title,rs.getInt(4));
                rs.close();
                return album;
            }
            rs.close();
            return null;
        }
    }

    public Album findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from albums where id=" + id)) {
            if(rs.next()){
                var album =new Album(id,rs.getInt(2),rs.getString(3),rs.getInt(4));
                rs.close();
                return album;
            }
            rs.close();
            return null;
        }
    }
    public void setGenres(int album, String genres) throws SQLException{
        var genreDAO = new GenreDAO();
        var albumGenreDAO = new AlbumGenreDAO();
        StringTokenizer genre = new StringTokenizer(genres,",");
        while(genre.hasMoreTokens()){
            albumGenreDAO.create(album,genreDAO.findByName(genre.nextToken()).id);
        }
    }
*/
}
