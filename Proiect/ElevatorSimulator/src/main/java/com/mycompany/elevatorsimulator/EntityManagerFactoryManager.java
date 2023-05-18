/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elevatorsimulator;

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
        emf = Persistence.createEntityManagerFactory("com.mycompany_ElevatorSimulator_jar_1.0-SNAPSHOTPU");
    }
    public static EntityManagerFactory getEntityManagerFactory(){
        if(instance==null)
            instance = new EntityManagerFactoryManager();
        return emf;
    }
}
