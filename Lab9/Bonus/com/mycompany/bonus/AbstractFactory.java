/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonus;

import JDBC.AlbumDAO;
import JDBC.ArtistDAO;
import JDBC.GenreDAO;
import repositories.AlbumRepository;
import repositories.ArtistRepository;
import repositories.GenreRepository;

/**
 *
 * @author avjiu
 */
public class AbstractFactory {
    public Repository getAlbumRepository(boolean isJPA){
        if(isJPA)
            return new AlbumRepository();
        else
            return new AlbumDAO();
    }
    public Repository getGenreRepository(boolean isJPA){
        if(isJPA)
            return new GenreRepository();
        else
            return new GenreDAO();
    }
    public Repository getArtistRepository(boolean isJPA){
        if(isJPA)
            return new ArtistRepository();
        else
            return new ArtistDAO();
    }
}
