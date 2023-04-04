/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonus;

import java.awt.Color;
import java.awt.Polygon;

/**
 *
 * @author Vlad Adriana
 */
public class UIPlayer extends Player{
    
    public UIPlayer(Color c){
        this.color=c;
    }
    @Override
    public void markLine(MainFrame frame, Polygon line) { //mouselistener in canvas
        frame.canvas.graphics.setColor(color);
        frame.canvas.graphics.drawLine(line.xpoints[0]+5,line.ypoints[0]-5,line.xpoints[3]+5,line.ypoints[3]-5);
        
    }
}
