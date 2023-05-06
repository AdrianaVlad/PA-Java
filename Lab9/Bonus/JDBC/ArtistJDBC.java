/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JDBC;

/**
 *
 * @author Vlad Adriana
 */
public class ArtistJDBC extends TableEntity{
    final int id;
    final String name;
    public ArtistJDBC(int id, String name){
        this.id=id;
        this.name=name;
    }

    @Override
    public String toString() {
        return "Artist{" + "id=" + id + ", name=" + name + '}';
    }
    
}
