/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;


import com.mycompany.homework.AbstractEntity;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;

/**
 *
 * @author Vlad Adriana
 */
@Entity
@Table(name = "GENRES")
@NamedQueries({
    @NamedQuery(name = "Genre.findMaxId", query = "SELECT MAX(a.id) FROM Genre a"),
    @NamedQuery(name = "Genre.findAll", query = "SELECT g FROM Genre g"),
    @NamedQuery(name = "Genre.findById", query = "SELECT g FROM Genre g WHERE g.id = ?1"),
    @NamedQuery(name = "Genre.findByName", query = "SELECT g FROM Genre g WHERE g.name = ?1")})
public class Genre extends AbstractEntity implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @ManyToMany(mappedBy = "genreCollection")
    private Collection<Album> albumCollection;

    public Genre() {
    }

    public Genre(int id) {
        this.id = id;
    }

    public Genre(Integer id, String name) {
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
        if (!(object instanceof Genre)) {
            return false;
        }
        Genre other = (Genre) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Genre{" + "id=" + id + ", name=" + name + ", albumCollection=" + albumCollection + '}';
    }

    
    
}
