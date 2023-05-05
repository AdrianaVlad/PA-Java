/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Vlad Adriana
 */
@Entity
@Table(name = "ALBUMS")
@NamedQueries({
    @NamedQuery(name = "Album.findMaxId", query = "SELECT MAX(a.id) FROM Album a"),
    @NamedQuery(name = "Album.findAll", query = "SELECT a FROM Album a"),
    @NamedQuery(name = "Album.findById", query = "SELECT a FROM Album a WHERE a.id = ?1"),
    @NamedQuery(name = "Album.findByTitle", query = "SELECT a FROM Album a WHERE a.title = ?1")})
public class Album implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "RELEASE_YEAR")
    private Integer releaseYear;
    @Column(name = "TITLE")
    private String title;
    @JoinTable(name = "ALBUM_GENRE", joinColumns = {
        @JoinColumn(name = "ALBUM_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "GENRE_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Genre> genreCollection;
    @JoinColumn(name = "ARTIST_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Artist artistId;

    public Album() {
    }

    public Album(Integer id, Integer releaseYear, String title, Collection<Genre> genreCollection, Artist artistId) {
        this.id = id;
        this.releaseYear = releaseYear;
        this.title = title;
        this.genreCollection = genreCollection;
        this.artistId = artistId;
    }
    
    public Album(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<Genre> getGenreCollection() {
        return genreCollection;
    }

    public void setGenreCollection(Collection<Genre> genreCollection) {
        this.genreCollection = genreCollection;
    }

    public Artist getArtistId() {
        return artistId;
    }

    public void setArtistId(Artist artistId) {
        this.artistId = artistId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Album)) {
            return false;
        }
        Album other = (Album) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.mycompany.compulsory.Album[ id=" + id + " ]";
    }
    
}
