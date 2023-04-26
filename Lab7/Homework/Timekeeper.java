/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;

/**
 *
 * @author avjiu
 */
public class Timekeeper implements Runnable{
    private long activatedAt;
    private int timer1Minute=60*1000;
    private Exploration parent;
    int runtime=0;
    public Timekeeper(Exploration parent){
        activatedAt = System.currentTimeMillis();
        this.parent=parent;
    }
    @Override
    public void run() {
        while(true){
            if(System.currentTimeMillis()-activatedAt>timer1Minute){
                System.out.println("Running for too long, we'll stop for now");
                for(Robot robot : parent.robots){
                    robot.done=true;
                }
            }
            if(System.currentTimeMillis()-activatedAt-runtime*1000>1000){
                runtime++;
                System.out.println("Running for " + runtime + " seconds");
            }
        }
    }
}
