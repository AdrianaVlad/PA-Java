/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.compulsory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
/**
 *
 * @author Vlad Adriana
 */
public class Compulsory {
    public static void main(String args[]) {
        try {
            var artists = new ArtistDAO();
            artists.create("Pink Floyd");
            artists.create("Michael Jackson");
            var genres = new GenreDAO();
            genres.create("Rock");
            genres.create("Funk");
            genres.create("Soul");
            genres.create("Pop");
            Database.getConnection().commit();
            var albums = new AlbumDAO();
            albums.create(1979, "The Wall", "Pink Floyd", "Rock");
            albums.create(1982, "Thriller", "Michael Jackson", "Funk,Soul,Pop");
            Database.getConnection().commit();
            printAlbums(albums);
            Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e);
            try {
                Database.getConnection().rollback();
            } catch (SQLException ex) {
                Logger.getLogger(Compulsory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static void printAlbums(AlbumDAO albums) throws SQLException{
        Connection con=Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select id from albums");
        Album album;
        List<AlbumGenre> genreList;
        ArtistDAO artists = new ArtistDAO();
        GenreDAO genres = new GenreDAO();
        AlbumGenreDAO albumGenres = new AlbumGenreDAO();
        while(rs.next()){
            album = albums.findById(rs.getInt(1));
            System.out.println(album);
            System.out.println(artists.findById(album.id));
            genreList = albumGenres.findByIdAlbum(album.id);
            System.out.println(genreList);
            for(AlbumGenre ag : genreList){
                System.out.println(genres.findById(ag.idGenre));
            }
        }
    }
}
