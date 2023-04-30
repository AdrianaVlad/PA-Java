/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;

/**
 *
 * @author Vlad Adriana
 */
public class Artist extends TableEntity{
    final int id;
    final String name;
    public Artist(int id, String name){
        this.id=id;
        this.name=name;
    }

    @Override
    public String toString() {
        return "Artist{" + "id=" + id + ", name=" + name + '}';
    }
    
}
