/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonus;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



/**
 *
 * @author Vlad Adriana
 */
public class EntityManagerFactoryManager {
    private static EntityManagerFactory emf;
    private static EntityManagerFactoryManager instance = null;
    private EntityManagerFactoryManager(){
        emf = Persistence.createEntityManagerFactory("Lab9Persistence");
    }
    public static EntityManagerFactory getEntityManagerFactory(){
        if(instance==null)
            instance = new EntityManagerFactoryManager();
        return emf;
    }
}
