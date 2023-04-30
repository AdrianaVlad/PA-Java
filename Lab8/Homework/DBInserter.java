/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author avjiu
 */
public class DBInserter {
    public void insert(String filePath) throws SQLException{
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));
            lineReader.readLine();
            String line;
            int i,j;
            var artists = new ArtistDAO();
            var genres = new GenreDAO();
            var albums = new AlbumDAO();
            var albumGenres = new AlbumGenreDAO();
            int albumId,genreId,artistId,releaseYear;
            String genreName,artistName,title;
            while((line = lineReader.readLine())!=null){
                albumId=albums.findNextId();
                String[] collumns = line.split(",");
                releaseYear = Integer.parseInt(collumns[1]);
                for(i=3;i<collumns.length;i++){
                    if(collumns[i].charAt(0)==' ')
                        collumns[2]=collumns[2]+collumns[i];
                    else break;
                }
                title = collumns[2];
                for(j=i+1;j<collumns.length;j++){
                    if(collumns[j].charAt(0)==' ')
                        collumns[i]=collumns[i]+collumns[j];
                    else break;
                }
                artistName = collumns[i];
                Artist artist = artists.findByName(artistName);
                if(artist==null){
                    artistId=artists.findNextId();
                    artists.create(new Artist(artistId,artistName));
                }
                else artistId=artist.id;
                albums.create(new Album(albumId,releaseYear,title,artistId));
                genreName=collumns[j];
                if(genreName.charAt(0)=='"')
                    genreName=genreName.substring(1);
                genreName=genreName.replaceAll("\'", "''");
                Genre genre = genres.findByName(genreName);
                if(genre==null){
                    genreId=genres.findNextId();
                    genres.create(new Genre(genreId,genreName));
                }
                else genreId=genre.id;
                albumGenres.create(new AlbumGenre(albumId,genreId));
                for(j=i+1;j<collumns.length;j++){
                    if(collumns[j].charAt(0)==' '){
                        genreName=collumns[j];
                        if(genreName.charAt(genreName.length()-1)=='"')
                            genreName=genreName.substring(1,genreName.length()-1);
                        else genreName=genreName.substring(1);
                        collumns[j]=collumns[j].replaceAll("\'", "''");
                        genre = genres.findByName(genreName);
                        if(genre==null){
                            genreId=genres.findNextId();
                            genres.create(new Genre(genreId,genreName));
                        }
                        else genreId=genre.id;
                        albumGenres.create(new AlbumGenre(albumId,genreId));
                    }
                    else break;
                }
                Database.getConnection().commit();
            }
            lineReader.close();
        } catch (IOException ex) {
            Logger.getLogger(DBInserter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
