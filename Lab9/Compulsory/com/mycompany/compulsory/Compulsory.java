/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.compulsory;

import entities.Album;
import entities.Artist;
import entities.Genre;
import java.util.ArrayList;
import java.util.Collection;
import repositories.AlbumRepository;
import repositories.ArtistRepository;
import repositories.GenreRepository;

/**
 *
 * @author Vlad Adriana
 */
public class Compulsory {
    public static void main(String args[]) {
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
       genreList.add(genres.findByName("Rock"));
       albums.create(new Album(1,1979,"The Wall", genreList,artists.findByName("Pink Floyd")));
       genreList.clear();
       genreList.add(genres.findByName("Funk"));
       genreList.add(genres.findByName("Soul"));
       genreList.add(genres.findByName("Pop"));
       albums.create(new Album(albums.findMaxId()+1,1982,"Thriller", genreList,artists.findByName("Michael Jackson")));
    }

}
