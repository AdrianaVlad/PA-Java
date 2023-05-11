/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serverapplication;

import java.awt.Color;

/**
 *
 * @author Vlad Adriana
 */
public class Player {
    ClientThread thread;
    Color color;
    long startedAt;
    Timer timer = new Timer(this);
    public Player(ClientThread thread,Color c){
        this.thread=thread;
        this.color=c;
        startedAt=System.currentTimeMillis();
    }
}
