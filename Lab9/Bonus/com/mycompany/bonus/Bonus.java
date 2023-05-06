/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.bonus;

import JDBC.ArtistDAO;
import JDBC.ArtistJDBC;
import JDBC.GenreDAO;
import JDBC.GenreJDBC;
import entities.Album;
import entities.Artist;
import entities.Genre;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import repositories.AlbumRepository;
import repositories.ArtistRepository;
import repositories.GenreRepository;

/**
 *
 * @author Vlad Adriana
 */
public class Bonus {
    public static void main(String args[]) {
        try {
            AbstractFactory factory = new AbstractFactory();
            ArtistDAO artists = (ArtistDAO)factory.getArtistRepository(false);
            ArtistRepository artistsJPA = (ArtistRepository) factory.getArtistRepository(true);
            artists.create(new ArtistJDBC(artists.findNextId(),"Pink Floyd"));
            artists.create(new ArtistJDBC(artists.findNextId(),"Michael Jackson"));
            GenreRepository genres =  (GenreRepository) factory.getGenreRepository(true);
            genres.create(new Genre(1,"Rock"));
            genres.create(new Genre(genres.findMaxId()+1,"Funk"));
            genres.create(new Genre(genres.findMaxId()+1,"Soul"));
            genres.create(new Genre(genres.findMaxId()+1,"Pop"));
            AlbumRepository albums = (AlbumRepository) factory.getAlbumRepository(true);
            Collection<Genre> genreList = new ArrayList<>();
            genreList.add(genres.findByName("Rock").get(0));
            albums.create(new Album(1,1979,"The Wall", genreList,artistsJPA.findByName("Pink Floyd").get(0)));
            genreList.clear();
            genreList.add(genres.findByName("Funk").get(0));
            genreList.add(genres.findByName("Soul").get(0));
            genreList.add(genres.findByName("Pop").get(0));
            albums.create(new Album(albums.findMaxId()+1,1982,"Thriller", genreList,artistsJPA.findByName("Michael Jackson").get(0)));
            System.out.println(albums.findAll());
            int i;
            long beforeQuery,afterQuery;
            for(i=0;i<500;i++){
                beforeQuery=System.currentTimeMillis();
                artists.create(new ArtistJDBC(artists.findNextId(),"Michael Jackson"));
                afterQuery=System.currentTimeMillis();
                System.out.println(afterQuery-beforeQuery + " millis");
            }
            for(i=0;i<500;i++){
                beforeQuery=System.currentTimeMillis();
                albums.create(new Album(albums.findMaxId()+1,1982,"Thriller", genreList,artistsJPA.findByName("Michael Jackson").get(0)));
                afterQuery=System.currentTimeMillis();
                System.out.println(afterQuery-beforeQuery + " millis");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bonus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
