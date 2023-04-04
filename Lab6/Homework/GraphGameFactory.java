/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;

import java.awt.Color;

/**
 *
 * @author Vlad Adriana
 */
public class GraphGameFactory implements Factory{
    @Override
    public GraphGame create(MainFrame frame){
        Player p1 = new UIPlayer(Color.RED); //maybe color here?
        Player p2 = new UIPlayer(Color.BLUE);
        GraphGame game = new GraphGame(frame,p1,p2);
        return game;
    }
}
