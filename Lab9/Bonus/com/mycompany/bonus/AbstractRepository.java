/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonus;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Vlad Adriana
 * @param <T>
 */
public abstract class AbstractRepository <T extends AbstractEntity> implements Repository{
    public EntityManagerFactory emf = EntityManagerFactoryManager.getEntityManagerFactory();
    public EntityManager em = emf.createEntityManager();
    public void create(T line) throws SQLException{
        try{
            em.getTransaction().begin();
            em.persist(line);
            em.getTransaction().commit();
        } catch (Exception e){
            System.err.println(e);
            em.getTransaction().rollback();
        }
    }
    public abstract List<T> findAll() throws SQLException;
    public abstract List<T> findByName(String name) throws SQLException;
    public abstract T findById(int id) throws SQLException;
    public abstract int findMaxId() throws SQLException;
}
