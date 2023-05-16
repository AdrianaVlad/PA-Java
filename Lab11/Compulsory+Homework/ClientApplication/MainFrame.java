/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clientapplication;

import static java.awt.BorderLayout.CENTER;
import javax.swing.JFrame;

/**
 *
 * @author avjiu
 */
public class MainFrame extends JFrame{
    Board board;
    public MainFrame(){
        board = new Board(this);
        add(board,CENTER);
        pack();
    }
}
