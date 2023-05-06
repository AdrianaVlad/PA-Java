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
public class GenreDAO extends TableDAO<GenreJDBC>{

    @Override
    public void create(GenreJDBC line) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into genres values (?,?)")) {
            pstmt.setInt(1, line.id);
            pstmt.setString(2, line.name);
            pstmt.executeUpdate();
            con.close();
        }
    }

    @Override
    public List<GenreJDBC> findAll() throws SQLException {
        Connection con = Database.getConnection();
        List<GenreJDBC> lines = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from genres")) {
            while(rs.next()){
                lines.add(new GenreJDBC(rs.getInt(1),rs.getString(2)));
            } 
            rs.close();
            con.close();
            return lines;
        }
    }

    @Override
    public GenreJDBC findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        name = name.replaceAll("\'", "''");
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from genres where name='" + name + "'")) {
            if(rs.next()){
                var genre =new GenreJDBC(rs.getInt(1),rs.getString(2));
                rs.close();
                con.close();
                return genre;
            }
            rs.close();
            con.close();
            return null;
        }
    }

    @Override
    public GenreJDBC findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select * from genres where id=" + id)) {
            if(rs.next()){
                var genre =new GenreJDBC(rs.getInt(1),rs.getString(2));
                rs.close();
                con.close();
                return genre;
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
        try (ResultSet rs = stmt.executeQuery("select max(id) from genres")) {
            rs.next();
            newid = rs.getInt(1);
        }
        con.close();
        return newid+1;
    }
}
