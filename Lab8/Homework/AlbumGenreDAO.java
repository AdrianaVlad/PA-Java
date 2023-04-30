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
public class AlbumGenreDAO {
    public void create(AlbumGenre line) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into album_genre values (?,?)")) {
            pstmt.setInt(1, line.albumId);
            pstmt.setInt(2, line.genreId);
            pstmt.executeUpdate();
        }
    }
    public List<AlbumGenre> findByIdAlbum(int idAlbum) throws SQLException {
        Connection con = Database.getConnection();
        List<AlbumGenre> lines = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from album_genre where id_album=" + idAlbum)) {
            while(rs.next()){
                lines.add(new AlbumGenre(idAlbum,rs.getInt(2)));
            }
            rs.close();
            return lines;
        }
    }
    public List<AlbumGenre> findByIdGenre(int idGenre) throws SQLException {
        Connection con = Database.getConnection();
         List<AlbumGenre> lines = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from album_genre where id_genre=" + idGenre)) {
            if(rs.next()){
                lines.add(new AlbumGenre(rs.getInt(1),idGenre));
            }
            rs.close();
            return lines;
        }
    }
}
