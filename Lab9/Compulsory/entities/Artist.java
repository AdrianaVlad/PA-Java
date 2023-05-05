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
@Table(name = "ARTISTS")
@NamedQueries({
    @NamedQuery(name = "Artist.findMaxId", query = "SELECT MAX(a.id) FROM Artist a"),
    @NamedQuery(name = "Artist.findAll", query = "SELECT a FROM Artist a"),
    @NamedQuery(name = "Artist.findById", query = "SELECT a FROM Artist a WHERE a.id = ?1"),
    @NamedQuery(name = "Artist.findByName", query = "SELECT a FROM Artist a WHERE a.name = ?1")})
public class Artist implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artistId")
    private Collection<Album> albumCollection;

    public Artist() {
    }

    public Artist(int id) {
        this.id = id;
    }

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Album> getAlbumCollection() {
        return albumCollection;
    }

    public void setAlbumCollection(Collection<Album> albumCollection) {
        this.albumCollection = albumCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Artist)) {
            return false;
        }
        Artist other = (Artist) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.mycompany.compulsory.Artist[ id=" + id + " ]";
    }
    
}
