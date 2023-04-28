/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compulsory;

/**
 *
 * @author Vlad Adriana
 */
public class AlbumGenre {
    final int idAlbum,idGenre;

    public AlbumGenre(int idAlbum, int idGenre) {
        this.idAlbum = idAlbum;
        this.idGenre= idGenre;
    }

    @Override
    public String toString() {
        return "AlbumGenre{" + "idAlbum=" + idAlbum + ", idGenre=" + idGenre + '}';
    }
    
}
