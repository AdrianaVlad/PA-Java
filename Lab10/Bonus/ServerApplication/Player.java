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
    int id;
    String name;
    Color color;
    Timer timer = new Timer(this);
    public Player(String name, int id){
        this.name = name;
        this.id=id;
    }
    public Player(ClientThread thread,Color c){
        this.thread=thread;
        this.color=c;
    }
    public void setThread(ClientThread thread){
        this.thread=thread;
    }
    public void setColor(Color color){
        this.color=color;
    }
}
