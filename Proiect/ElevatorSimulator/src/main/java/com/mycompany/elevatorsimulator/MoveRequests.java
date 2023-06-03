/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elevatorsimulator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Vlad Adriana
 */
@Component
public class MoveRequests {
    private static Map<Integer,LinkedHashSet<Integer>> moveRequests = new HashMap<>();
    private Map<Integer,MovingElevator> threads = new HashMap<>();
    public MoveRequests(){}
    public synchronized void request(int id, int floor){
        LinkedHashSet<Integer> floors = moveRequests.get(id);
        if(floors==null)
            floors= new LinkedHashSet<>();
        floors.add(floor);
        moveRequests.put(id, floors);
        if(!threads.containsKey(id)){
            MovingElevator thread = new MovingElevator(this, id);
            thread.start();
            threads.put(id,thread);
        }
    }
    public synchronized void remove(int id){
        moveRequests.remove(id);
    }
    public synchronized void fulfilled(int id, int floor){
        LinkedHashSet<Integer> floors = moveRequests.get(id);
        floors.remove((Integer)floor);
        moveRequests.put(id, floors);
    }
    public synchronized boolean exists(int id){
        return !moveRequests.get(id).isEmpty();
    }
    public synchronized boolean isDestination(int id, int floor){
        return moveRequests.get(id).contains(floor);
    }
    public synchronized int getFirst(int id){
        return moveRequests.get(id).stream().findFirst().get();
    }
    public synchronized void endedThread(int id){
        threads.remove(id);
    }
}
