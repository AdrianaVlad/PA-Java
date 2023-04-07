/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.compulsory;


/**
 *
 * @author Vlad Adriana
 */
public class Compulsory {

    public static void main(String args[]) {
        var explore = new Exploration(10);
        explore.addRobot(new Robot("Wall-E"));
        explore.addRobot(new Robot("R2D2"));
        explore.addRobot(new Robot("Optimus Prime"));
        explore.start();
        
    }
}