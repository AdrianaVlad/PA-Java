/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compulsory;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vlad Adriana
 */
public class Robot implements Runnable{

    private final String name;
    private boolean running=true;
    Exploration explore;
    int row, col;
    int[] rowDirection = {-1,0,1,0};
    int[] colDirection = {0,1,0,-1};
    
    public Robot(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        explore.getMap().visit(row, col, this);
        int i;
        while (running) {
            for(i=0;i<4;i++)
                if(inBounds(rowDirection[i],colDirection[i]))
                    if(explore.getMap().visit(row+rowDirection[i], col+colDirection[i], this))
                        break;
            if(i==4){
                System.out.println("Robot " + name + " got stuck! We're turning it off");
                running=false;
            }
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Robot.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean inBounds(int rowChange,int colChange){
        return (row+rowChange>=0)&&(row+rowChange<explore.size)&&(col+colChange>=0)&&(col+colChange<explore.size);
    }

    
}
