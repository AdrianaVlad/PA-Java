/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compulsory;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vlad Adriana
 */
public class Exploration {
    int size;
    private final SharedMemory mem;
    private final ExplorationMap map;
    private final List<Robot> robots = new ArrayList<>();
    
    public Exploration(int size) {
        this.size = size;
        mem = new SharedMemory(size);
        map = new ExplorationMap(size);
    }

    public void start() {
        for (Robot robot : robots) {    
            robot.row=(int) (Math.random() * size);
            robot.col=(int) (Math.random() * size);
            if(!map.isEmpty(robot.row,robot.col)){
                for(int i=0;i<size;i++)
                    for(int j=0;j<size;j++)
                        if(map.isEmpty(i,j)){
                            robot.row=i;
                            robot.col=j;
                            break;
                        }
                if(!map.isEmpty(robot.row,robot.col)){
                    System.out.println("Map is full! Can't place any more robots");
                }
            }
            new Thread(robot).start();
            try {
                sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Exploration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void addRobot(Robot robot){
        robots.add(robot);
        robot.explore=this;
    }

    public SharedMemory getMem() {
        return mem;
    }

    public ExplorationMap getMap() {
        return map;
    }
    
}
