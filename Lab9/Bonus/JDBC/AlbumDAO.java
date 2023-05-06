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
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vlad Adriana
 */
public class AlbumDAO extends TableDAO<AlbumJDBC>{

    @Override
    public void create(AlbumJDBC line) throws SQLException{
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into albums values (?,?,?,?)")) {
            pstmt.setInt(1, line.id);
            pstmt.setInt(2, line.releaseYear);
            pstmt.setString(3,line.title);
            pstmt.setInt(4,line.artistId);
            pstmt.executeUpdate();
            con.close();
        }
    }

    @Override
    public List<AlbumJDBC> findAll() throws SQLException{
        Connection con = Database.getConnection();
        List<AlbumJDBC> lines = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from albums")) {
            while(rs.next()){
                lines.add(new AlbumJDBC(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4)));
            }
            rs.close();
            con.close();
            return lines;
        }
    }
    
    @Override
    public AlbumJDBC findByName(String title) throws SQLException{
        Connection con = Database.getConnection();
        title = title.replaceAll("\'", "''");
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from albums where title='" + title + "'")) {
            if(rs.next()){
                var album =new AlbumJDBC(rs.getInt(1),rs.getInt(2),title,rs.getInt(4));
                rs.close();
                con.close();
                return album;
            }
            rs.close();
            con.close();
            return null;
        }
    }
    
    @Override
    public AlbumJDBC findById(int id) throws SQLException{
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from albums where id=" + id)) {
            if(rs.next()){
                var album =new AlbumJDBC(id,rs.getInt(2),rs.getString(3),rs.getInt(4));
                rs.close();
                con.close();
                return album;
            }
            rs.close();
            con.close();
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
        con.close();
        return newid+1;
        
    }
}
