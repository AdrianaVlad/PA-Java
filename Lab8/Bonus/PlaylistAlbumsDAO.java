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
public class PlaylistAlbumsDAO {
    public void create(PlaylistAlbums line) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into playlist_albums values (?,?)")) {
            pstmt.setInt(1, line.playlistId);
            pstmt.setInt(2, line.albumId);
            pstmt.executeUpdate();
        }
    }
    public List<PlaylistAlbums> findByIdAlbum(int albumId) throws SQLException {
        Connection con = Database.getConnection();
        List<PlaylistAlbums> lines = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from playlist_albums where album_id=" + albumId)) {
            while(rs.next()){
                lines.add(new PlaylistAlbums(rs.getInt(1),albumId));
            }
            rs.close();
            return lines;
        }
    }
    public List<PlaylistAlbums> findByIdPlaylist(int idPlaylist) throws SQLException {
        Connection con = Database.getConnection();
        List<PlaylistAlbums> lines = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from playlist_albums where id_genre=" + idPlaylist)) {
            if(rs.next()){
                lines.add(new PlaylistAlbums(idPlaylist,rs.getInt(2)));
            }
            rs.close();
            return lines;
        }
    }
}
