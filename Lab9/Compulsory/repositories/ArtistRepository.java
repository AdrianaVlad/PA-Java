/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import com.mycompany.compulsory.EntityManagerFactoryManager;
import entities.Artist;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vlad Adriana
 */
public class ArtistRepository {
    EntityManagerFactory emf = EntityManagerFactoryManager.getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    public void create(Artist artist){
        try{
            em.getTransaction().begin();
            em.persist(artist);
            em.getTransaction().commit();
        } catch (Exception e){
            System.err.println(e);
            em.getTransaction().rollback();
        }
    }
    public Artist findById(int id){
        return (Artist)em.createNamedQuery("Artist.findById",Artist.class).setParameter(1,id).getSingleResult();
    }
    public Artist findByName(String name){
        return (Artist)em.createNamedQuery("Artist.findByName",Artist.class).setParameter(1,name).getSingleResult();
    }
    public int findMaxId(){
        return (int)em.createNamedQuery("Artist.findMaxId",Integer.class).getSingleResult();
    }
}
