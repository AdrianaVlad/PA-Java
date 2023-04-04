/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonus;

import java.awt.Color;
import java.awt.Polygon;
import java.io.Serializable;

/**
 *
 * @author Vlad Adriana
 */
public abstract class Player implements Serializable{
    Color color;
    abstract public void markLine(MainFrame frame, Polygon line);
}
