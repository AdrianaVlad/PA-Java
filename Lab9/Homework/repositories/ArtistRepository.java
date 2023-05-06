/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import com.mycompany.homework.AbstractRepository;
import entities.Artist;
import java.util.List;

/**
 *
 * @author Vlad Adriana
 */
public class ArtistRepository extends AbstractRepository<Artist>{
    @Override
    public Artist findById(int id){
        return (Artist)em.createNamedQuery("Artist.findById",Artist.class).setParameter(1,id).getSingleResult();
    }
    @Override
    public List<Artist> findByName(String name){
        return (List<Artist>)em.createNamedQuery("Artist.findByName").setParameter(1,name).getResultList();
    }
    @Override
    public int findMaxId(){
        return (int)em.createNamedQuery("Artist.findMaxId",Integer.class).getSingleResult();
    }
    @Override
    public List<Artist> findAll(){
        return (List<Artist>)em.createNamedQuery("Artist.findAll").getResultList();
    }
}
