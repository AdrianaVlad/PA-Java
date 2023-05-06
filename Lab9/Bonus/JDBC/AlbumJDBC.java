/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JDBC;


/**
 *
 * @author Vlad Adriana
 */
public class AlbumJDBC extends TableEntity{
    final int id,artistId,releaseYear;
    final String title;
    public AlbumJDBC(int id, int releaseYear, String title, int artistId){
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
