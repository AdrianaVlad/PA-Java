/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import com.mycompany.homework.AbstractRepository;
import entities.Album;
import java.util.List;

/**
 *
 * @author avjiu
 */
public class AlbumRepository extends AbstractRepository<Album>{
     @Override
    public Album findById(int id){
        return (Album)em.createNamedQuery("Album.findById",Album.class).setParameter(1,id).getSingleResult();
    }
     @Override
    public List<Album> findByName(String name){
        return (List<Album>)em.createNamedQuery("Album.findByName").setParameter(1,name).getResultList();
    }
     @Override
    public int findMaxId(){
        return (int)em.createNamedQuery("Album.findMaxId",Integer.class).getSingleResult();
    }
     @Override
    public List<Album> findAll(){
        return (List<Album>)em.createNamedQuery("Album.findAll").getResultList();
    }
}
