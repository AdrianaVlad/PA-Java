/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonus;

/**
 *
 * @author Vlad Adriana
 */
public class PlaylistAlbums extends TableEntity{
    int playlistId, albumId;

    public PlaylistAlbums(int playlistId, int albumId) {
        this.playlistId = playlistId;
        this.albumId = albumId;
    }

    @Override
    public String toString() {
        return "PlaylistAlbums{" + "playlistId=" + playlistId + ", albumId=" + albumId + '}';
    }
    
}
