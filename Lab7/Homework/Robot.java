/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vlad Adriana
 */
public class Robot implements Runnable{
    private long activatedAt=Long.MAX_VALUE;
    private int timer=Integer.MAX_VALUE;
    private final String name;
    boolean done=false;
    public boolean running;
    Exploration explore;
    int row, col, count=1, direction=0;
    int[] rowChange = {-1,0,1,0};
    int[] colChange = {0,1,0,-1};
    
    public Robot(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        explore.getMap().visit(row, col, this);
        int checkedDirections=0;
        System.out.println("Robot " + name + ": Placed on row " + row + " and col " + col);
        while (!done) {
            if(System.currentTimeMillis()-activatedAt>timer){
                running=!running;
                timer=Integer.MAX_VALUE;
                activatedAt=Long.MAX_VALUE;
            }
            if(running){
            while(checkedDirections<4 && !explore.exploredAll()){
                if(inBounds(rowChange[direction],colChange[direction])&&noRobots(rowChange[direction],colChange[direction])){
                    if((explore.getMap().visit(row+rowChange[direction], col+colChange[direction], this))){
                        System.out.println("Robot " + name + ": Cell on row " + row + " and col " + col + " visited successfully");
                        count++;
                        break;
                    }
                    checkedDirections=0;
                    row=row+rowChange[direction];
                    col=col+colChange[direction];
                }
                else{
                    direction=(direction+1)%4;
                    checkedDirections++;
                }
            }
            if(explore.exploredAll()){
                done=true;
                running=false;
            }
            }
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Robot.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    public void start(int time){
        if(running){
            System.out.println(name+ " already running!");
            return;
        }
        running=true;
        timer=time;
        if(time==Integer.MAX_VALUE)
            activatedAt=Long.MAX_VALUE;
        else
            activatedAt=System.currentTimeMillis();
    }
    public void pause(int time){
        if(!running){
            System.out.println(name+ " already stopped!");
            return;
        }
        running=false;
        timer=time;
        if(time==Integer.MAX_VALUE)
            activatedAt=Long.MAX_VALUE;
        else
            activatedAt=System.currentTimeMillis();
    }
    
    public boolean inBounds(int rowChange,int colChange){
        return (row+rowChange>=0)&&(row+rowChange<explore.size)&&(col+colChange>=0)&&(col+colChange<explore.size);
    }
    public boolean noRobots(int rowChange,int colChange){
        for(Robot robot : explore.robots){
            if((row+rowChange)==robot.row && (col+colChange)==robot.col)
                return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }
    
}
