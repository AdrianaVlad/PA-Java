/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JDBC;

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
            con.close();
        }
    }
    public List<AlbumGenre> findByIdAlbum(int albumId) throws SQLException {
        Connection con = Database.getConnection();
        List<AlbumGenre> lines = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from album_genre where album_id=" + albumId)) {
            while(rs.next()){
                lines.add(new AlbumGenre(albumId,rs.getInt(2)));
            }
            rs.close();
            con.close();
            return lines;
        }
    }
    public List<AlbumGenre> findByIdGenre(int genreId) throws SQLException {
        Connection con = Database.getConnection();
         List<AlbumGenre> lines = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from album_genre where genre_id=" + genreId)) {
            if(rs.next()){
                lines.add(new AlbumGenre(rs.getInt(1),genreId));
            }
            rs.close();
            con.close();
            return lines;
        }
    }
}
