/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serverapplication;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vlad Adriana
 */
public class Timer extends Thread{
    Player player;
    boolean done=false;
    boolean running=false;
    long startedAt;
    public Timer(Player player){
        this.player=player;
    }
    @Override
    public void run(){
        startedAt=System.currentTimeMillis();
        while(!done){
            while(running){
                if(System.currentTimeMillis()-startedAt>60000){
                    running=false;
                    done=true;
                }   
            }
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
