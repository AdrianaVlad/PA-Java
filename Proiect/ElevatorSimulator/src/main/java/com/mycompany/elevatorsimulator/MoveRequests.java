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

/**
 *
 * @author Vlad Adriana
 */
public class MoveRequests {
    private static Map<Integer,LinkedHashSet<Integer>> moveRequests = new HashMap<>();
    private Set<Integer> threadsFor = new HashSet<>();
    public MoveRequests(){}
    public synchronized void request(int id, int floor){
        LinkedHashSet<Integer> floors = moveRequests.get(id);
        if(floors==null)
            floors= new LinkedHashSet<>();
        floors.add(floor);
        moveRequests.put(id, floors);
        if(!threadsFor.contains(id)){
            threadsFor.add(id);
            new MovingElevator(this, id).start();
        }
    }
    public synchronized void delete(int id){
        moveRequests.remove(id);
        for (Map.Entry<Integer, LinkedHashSet<Integer>> request : moveRequests.entrySet()) {
            int key = request.getKey();
            LinkedHashSet<Integer> value = request.getValue();
            if(key>id){
                moveRequests.remove(key);
                key--;
                moveRequests.put(key, value);
            }
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
        threadsFor.remove(id);
    }
}
