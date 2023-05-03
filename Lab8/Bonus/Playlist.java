/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonus;

import java.sql.Timestamp;

/**
 *
 * @author Vlad Adriana
 */
public class Playlist extends TableEntity{
    int id;
    String name;
    Timestamp creationTimestamp;

    public Playlist(int id, String name, Timestamp creationTimestamp) {
        this.id = id;
        this.name = name;
        this.creationTimestamp = creationTimestamp;
    }

    @Override
    public String toString() {
        return "Playlist{" + "id=" + id + ", name=" + name + ", creationTimestamp=" + creationTimestamp + '}';
    }

}
