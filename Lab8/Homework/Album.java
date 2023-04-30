/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;

/**
 *
 * @author Vlad Adriana
 */
public class Album extends TableEntity{
    final int id,artistId,releaseYear;
    final String title;
    public Album(int id, int releaseYear, String title, int artistId){
        this.id=id;
        this.artistId=artistId;
        this.releaseYear=releaseYear;
        this.title=title;
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + id  + ", releaseYear=" + releaseYear + ", title=" + title + ", artistId=" + artistId + '}';
    }
    
}
