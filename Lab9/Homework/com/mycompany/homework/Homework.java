/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.homework;

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
public class Homework {
    public static void main(String args[]) {
        try {
            ArtistRepository artists = new ArtistRepository();
            Artist artist= new Artist();
            artist.setId(1);
            artist.setName("Pink Floyd");
            artists.create(artist);
            artists.create(new Artist(artists.findMaxId()+1,"Michael Jackson"));
            GenreRepository genres =  new GenreRepository();
            genres.create(new Genre(1,"Rock"));
            genres.create(new Genre(genres.findMaxId()+1,"Funk"));
            genres.create(new Genre(genres.findMaxId()+1,"Soul"));
            genres.create(new Genre(genres.findMaxId()+1,"Pop"));
            AlbumRepository albums = new AlbumRepository();
            Collection<Genre> genreList = new ArrayList<>();
            genreList.add(genres.findByName("Rock").get(0));
            albums.create(new Album(1,1979,"The Wall", genreList,artists.findByName("Pink Floyd").get(0)));
            genreList.clear();
            genreList.add(genres.findByName("Funk").get(0));
            genreList.add(genres.findByName("Soul").get(0));
            genreList.add(genres.findByName("Pop").get(0));
            albums.create(new Album(albums.findMaxId()+1,1982,"Thriller", genreList,artists.findByName("Michael Jackson").get(0)));
            System.out.println(albums.findAll());
            int i;
            long beforeQuery,afterQuery;
            for(i=0;i<500;i++){
                beforeQuery=System.currentTimeMillis();
                artists.create(new Artist(artists.findMaxId()+1,"Michael Jackson"));
                afterQuery=System.currentTimeMillis();
                System.out.println(afterQuery-beforeQuery + " millis");
            }
            for(i=0;i<500;i++){
                beforeQuery=System.currentTimeMillis();
                albums.create(new Album(albums.findMaxId()+1,1982,"Thriller", genreList,artists.findByName("Michael Jackson").get(0)));
                afterQuery=System.currentTimeMillis();
                System.out.println(afterQuery-beforeQuery + " millis");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Homework.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
