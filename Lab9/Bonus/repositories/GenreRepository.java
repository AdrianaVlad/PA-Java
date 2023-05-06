/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import com.mycompany.bonus.AbstractRepository;
import entities.Genre;
import java.util.List;

/**
 *
 * @author avjiu
 */
public class GenreRepository extends AbstractRepository<Genre> {
     @Override
    public Genre findById(int id){
        return (Genre)em.createNamedQuery("Genre.findById",Genre.class).setParameter(1,id).getSingleResult();
    }
     @Override
    public List<Genre> findByName(String name){
        return (List<Genre>)em.createNamedQuery("Genre.findByName").setParameter(1,name).getResultList();
    }
     @Override
    public int findMaxId(){
        return (int)em.createNamedQuery("Genre.findMaxId",Integer.class).getSingleResult();
    }
     @Override
     public List<Genre> findAll(){
        return (List<Genre>)em.createNamedQuery("Genre.findAll").getResultList();
    }
}
