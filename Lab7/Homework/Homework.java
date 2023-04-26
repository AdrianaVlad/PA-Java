/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.homework;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Vlad Adriana
 */
public class Homework {

    public static void main(String args[]){
        var explore = new Exploration(10);
        explore.addRobot(new Robot("Wall-E"));
        explore.addRobot(new Robot("R2D2"));
        explore.addRobot(new Robot("Optimus Prime"));
        explore.initialize();
        Scanner in = new Scanner(System.in);
        while(!explore.done())try {
            if(System.in.available()>0)
                testInput(explore,in);
        } catch (IOException ex) {
            Logger.getLogger(Homework.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(Robot robot : explore.robots)
            System.out.println("Robot " + robot.getName() + " explored " + robot.count + " cells!");
    }
    public static void testInput(Exploration explore, Scanner in) throws IOException{
        String cmd = in.nextLine();
        String first5 = cmd.substring(0,Math.min(cmd.length(),5));
        String who=cmd.substring(Math.min(cmd.length(), 6), Math.min(cmd.length(),8));
        String timeString=cmd.substring(Math.min(cmd.length(), 9));
        int time = Integer.MAX_VALUE;
            if(timeString.length()!=0)
                time=Integer.parseInt(timeString);
        if(first5.equals("start"))
            switch(who){
                case("ex") -> {
                    explore.start(time);
                }
                case("r1") -> {
                    explore.robots.get(0).start(time);
                }
                case("r2") -> {
                    explore.robots.get(1).start(time);
                }
                case("r3") -> {
                    explore.robots.get(2).start(time);
                }
            }
        else if(first5.equals("pause"))
            switch(who){
                case("ex") -> {
                    explore.pause(time);
                }
                case("r1") -> {
                    explore.robots.get(0).pause(time);
                }
                case("r2") -> {
                    explore.robots.get(1).pause(time);
                }
                case("r3") -> {
                    explore.robots.get(2).pause(time);
                }
        }
    }
}