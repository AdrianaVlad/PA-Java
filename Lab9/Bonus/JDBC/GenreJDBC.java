/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JDBC;


/**
 *
 * @author Vlad Adriana
 */
public class GenreJDBC extends TableEntity{
    final int id;
    final String name;
    public GenreJDBC(int id, String name){
        this.id=id;
        this.name=name;
    }

    @Override
    public String toString() {
        return "Genre{" + "id=" + id + ", name=" + name + '}';
    }
    
}
