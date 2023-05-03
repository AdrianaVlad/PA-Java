/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonus;

/**
 *
 * @author Vlad Adriana
 */
public class AlbumGenre extends TableEntity{
    final int albumId,genreId;

    public AlbumGenre(int albumId, int genreId) {
        this.albumId= albumId;
        this.genreId= genreId;
    }

    @Override
    public String toString() {
        return "AlbumGenre{" + "albumid=" + albumId + ", genreId=" + genreId + '}';
    }
    
}
