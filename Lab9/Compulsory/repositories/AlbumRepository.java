/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import com.mycompany.compulsory.EntityManagerFactoryManager;
import entities.Album;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author avjiu
 */
public class AlbumRepository {
     EntityManagerFactory emf = EntityManagerFactoryManager.getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    public void create(Album album){
        try{
            em.getTransaction().begin();
            em.persist(album);
            em.getTransaction().commit();
        } catch (Exception e){
            System.err.println(e);
            em.getTransaction().rollback();
        }
    }
    public Album findById(int id){
        return (Album)em.createNamedQuery("Album.findById",Album.class).setParameter(1,id).getSingleResult();
    }
    public Album findByName(String name){
        return (Album)em.createNamedQuery("Album.findByName",Album.class).setParameter(1,name).getSingleResult();
    }
    public int findMaxId(){
        return (int)em.createNamedQuery("Album.findMaxId",Integer.class).getSingleResult();
    }
}
