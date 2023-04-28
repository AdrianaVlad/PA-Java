/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compulsory;

/**
 *
 * @author Vlad Adriana
 */
public class Album {
    final int id,idArtist,releaseYear;
    final String title;
    public Album(int id, int releaseYear, String title, int idArtist){
        this.id=id;
        this.idArtist=idArtist;
        this.releaseYear=releaseYear;
        this.title=title;
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + id  + ", releaseYear=" + releaseYear + ", title=" + title + ", idArtist=" + idArtist + '}';
    }
    
}
