/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;

import java.io.Serializable;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @authorVlad Adriana
 */
public class GraphGame extends JPanel implements Game, Serializable{
    final MainFrame frame;
    int whoTurn=1, whoWin;
    JLabel turnLabel, holdLabel, winLabel, lossLabel;
    Player player1, player2;
    public GraphGame(MainFrame frame, Player p1,Player p2){
        this.frame=frame;
        this.player1=p1;
        this.player2=p2;
        turnLabel = new JLabel("It's player1's turn!");
        turnLabel.setForeground(player1.color); 
        holdLabel = new JLabel("player 2 on hold");
        holdLabel.setForeground(player2.color);
        add(turnLabel);
        add(holdLabel);
    }
    @Override
    final public void onTurn() {
        if(whoTurn==1){
            turnLabel.setForeground(player1.color);
            turnLabel.setText("It's player1's turn!");
        }
        else{
            turnLabel.setForeground(player2.color);
            turnLabel.setText("It's player2's turn!");
        }  
    }

    @Override
    final public void onHold() {
        if(whoTurn==1){
            holdLabel.setForeground(player2.color);
            holdLabel.setText("player 2 on hold");
        }
        else{
            holdLabel.setForeground(player1.color);
            holdLabel.setText("player 1 on hold");
        }
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void onWin() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void onLoss() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
