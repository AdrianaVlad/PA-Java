/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Vlad Adriana
 */
public class PlaylistGenerator {
    private static HashSet<ArrayList<Album>> independentSets = new HashSet<>();
    public static List<ArrayList<Album>> generateAlbumSets(List<Album> albums) throws SQLException{
        HashMap<Album,List<Album>> related = generateEdges(albums);
        HashSet<Album> playlist = new HashSet<>();
        findUnrelatedAlbumSets(1,albums,related,playlist);
        return findMaximalSets();
    }
    private static HashMap<Album,List<Album>> generateEdges(List<Album> albums) throws SQLException{
        HashMap<Album,List<Album>> edges = new HashMap<>();
        int i,j;
        Album album1,album2;
        AlbumGenreDAO genres = new AlbumGenreDAO();
        List<AlbumGenre> album1Genres,album2Genres;
        for(i=0;i<albums.size();i++)
            edges.put(albums.get(i), new ArrayList<>());
        for(i=0;i<albums.size();i++){
            album1 = albums.get(i);
            for(j=i+1;j<albums.size();j++){
                album2=albums.get(j);
                if(album1.releaseYear==album2.releaseYear || album1.artistId==album2.artistId){
                    edges.get(album1).add(album2);
                    edges.get(album2).add(album1);
                    continue;
                }
                album1Genres = genres.findByIdAlbum(album1.id);
                album2Genres = genres.findByIdAlbum(album2.id);
                album1Genres.retainAll(album2Genres);
                if(!album1Genres.isEmpty()){
                    edges.get(album1).add(album2);
                    edges.get(album2).add(album1);
                }
                    
            }
        }
        return edges;
    }
    private static void findUnrelatedAlbumSets(int node, List<Album> albums, HashMap<Album,List<Album>> related, HashSet<Album> playlist){
        for(int i=node;i<albums.size();i++){
            if(isUnrelated(i,albums,related,playlist)){
                playlist.add(albums.get(i));
                findUnrelatedAlbumSets(i+1,albums,related,playlist);
                playlist.remove(albums.get(i));
            }           
        }
        independentSets.add(new ArrayList<Album>(playlist));
    }
    private static boolean isUnrelated(int node, List<Album> albums, HashMap<Album,List<Album>> related, HashSet<Album> playlist){
        for(Album album : playlist){
            if(related.get(album).contains(albums.get(node)))
                return false;
        }
        return true;
    }
    private static List<ArrayList<Album>> findMaximalSets(){
        int maxSize=0;
        for(ArrayList<Album> playlist : independentSets)
            if(playlist.size()>maxSize)
                maxSize=playlist.size();
        List<ArrayList<Album>> playlists = new ArrayList<>();
        for(ArrayList<Album> playlist : independentSets)
            if(playlist.size()==maxSize)
                playlists.add(playlist);
        return playlists;
    }
}
