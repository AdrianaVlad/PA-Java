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
import java.util.StringTokenizer;

/**
 *
 * @author Vlad Adriana
 */
public class AlbumDAO {
    public void create(int release_year,String title, String artist, String genres) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into albums values (?,?,?,?)")) {
            pstmt.setInt(2, release_year);
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
        }
    }

    public Album findByTitle(String title) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from albums where title='" + title + "'")) {
            if(rs.next()){
                return new Album(rs.getInt(1),rs.getInt(2),title,rs.getInt(4));
            }
            return null;
        }
    }

    public Album findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from albums where id=" + id)) {
            if(rs.next()){
                return new Album(id,rs.getInt(2),rs.getString(3),rs.getInt(4));
            }
            return null;
        }
    }
    public void setGenres(int album, String genres) throws SQLException{
        Connection con = Database.getConnection();
        var genreDAO = new GenreDAO();
        var albumGenreDAO = new AlbumGenreDAO();
        StringTokenizer genre = new StringTokenizer(genres,",");
        while(genre.hasMoreTokens()){
            albumGenreDAO.create(album,genreDAO.findByName(genre.nextToken()).id);
        }
    }
}
