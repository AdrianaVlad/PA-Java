/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.bonus;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Vlad Adriana
 */
public class Bonus {
    public static void main(String args[]) {
        try {
            var artists = new ArtistDAO();
            artists.create(new Artist(artists.findNextId(),"Pink Floyd"));
            artists.create(new Artist(artists.findNextId(),"Michael Jackson"));
            var genres = new GenreDAO();
            genres.create(new Genre(genres.findNextId(),"Rock"));
            genres.create(new Genre(genres.findNextId(),"Funk"));
            genres.create(new Genre(genres.findNextId(),"Soul"));
            genres.create(new Genre(genres.findNextId(),"Pop"));
            var albums = new AlbumDAO();
            var albumGenres = new AlbumGenreDAO();
            int newId = albums.findNextId();
            albums.create(new Album(newId,1979, "The Wall", artists.findByName("Pink Floyd").id));
            albumGenres.create(new AlbumGenre(newId,genres.findByName("Rock").id));
            newId = albums.findNextId();
            albums.create(new Album( newId,1982, "Thriller", artists.findByName("Michael Jackson").id));
            albumGenres.create(new AlbumGenre(newId,genres.findByName("Funk").id));
            albumGenres.create(new AlbumGenre(newId,genres.findByName("Soul").id));
            albumGenres.create(new AlbumGenre(newId,genres.findByName("Pop").id));
            String databasePath = "C:\\Users\\avjiu\\Documents\\albumlist.csv";
            var inserter = new DBInserter();
            inserter.insert(databasePath);
            System.out.println(albums.findAll());
            var playlists = new PlaylistDAO();
            var playlistAlbums = new PlaylistAlbumsDAO();
            List<ArrayList<Album>> albumSets = PlaylistGenerator.generateAlbumSets(albums.findAll().subList(0, 25));
            for(int i=0;i<albumSets.size();i++){
                newId = playlists.findNextId();
                playlists.create(new Playlist(newId,"Generated playlist nr " + (i+1),new Timestamp(System.currentTimeMillis())));
                for(Album album : albumSets.get(i))
                    playlistAlbums.create(new PlaylistAlbums(newId,album.id));
            }
            System.out.println(playlists.findAll());
        } catch (SQLException e) {
            System.err.println(e);
            try {
                Database.getConnection().rollback();
            } catch (SQLException ex) {
                Logger.getLogger(Bonus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
