/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import com.mycompany.compulsory.EntityManagerFactoryManager;
import entities.Genre;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author avjiu
 */
public class GenreRepository {
     EntityManagerFactory emf = EntityManagerFactoryManager.getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    public void create(Genre genre){
        try{
            em.getTransaction().begin();
            em.persist(genre);
            em.getTransaction().commit();
        } catch (Exception e){
            System.err.println(e);
            em.getTransaction().rollback();
        }
    }
    public Genre findById(int id){
        return (Genre)em.createNamedQuery("Genre.findById",Genre.class).setParameter(1,id).getSingleResult();
    }
    public Genre findByName(String name){
        return (Genre)em.createNamedQuery("Genre.findByName",Genre.class).setParameter(1,name).getSingleResult();
    }
    public int findMaxId(){
        return (int)em.createNamedQuery("Genre.findMaxId",Integer.class).getSingleResult();
    }
}
