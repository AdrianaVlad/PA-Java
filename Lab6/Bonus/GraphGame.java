/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonus;

import java.awt.Font;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.io.Serializable;
import java.util.Objects;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author avjiu
 */
public class GraphGame extends JPanel implements Game, Serializable{
    final MainFrame frame;
    int whoTurn=1, whoWin=0;
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
        winLabel = new JLabel();
        lossLabel = new JLabel();
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
        this.removeAll();
        winLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        if(whoWin==1){
            winLabel.setForeground(player1.color);
            winLabel.setText("PLAYER 1 WON!");
        }
        else{
            winLabel.setForeground(player2.color);
            winLabel.setText("PLAYER 2 WON!");
        }  
    }

    @Override
    public void onLoss() {
       turnLabel.setFont(new Font("Serif", Font.PLAIN, 10));
       if(whoWin==1){
            lossLabel.setForeground(player2.color);
            lossLabel.setText("better luck next time, player 2");
        }
        else{
            lossLabel.setForeground(player1.color);
            lossLabel.setText("better luck next time, player 1");
        }
        add(winLabel);
        add(lossLabel);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void checkTriangle(Polygon newLine){
        //line.xpoints[0]+5,line.ypoints[0]-5,line.xpoints[3]+5,line.ypoints[3]-5
        //int xPoints[] = { x[i]+5, x[i]+15, x[j]+15, x[j]+5 };
        //int yPoints[] = { y[i]+15, y[i]+5, y[j]+5, y[j]+15 };
        for(Polygon line1 : frame.canvas.lines){
            if(Objects.equals(frame.canvas.takenLine.get(line1), frame.canvas.takenLine.get(newLine)) && line1!=newLine){
                if(commonEnd(line1,newLine))
                    for(Polygon line2 : frame.canvas.lines)
                        if(Objects.equals(frame.canvas.takenLine.get(line2), frame.canvas.takenLine.get(newLine)) && line2!=newLine && line2!=line1){
                            if(commonEnd(line2,newLine)){
                                if(commonEnd(line1,line2)){
                                    if(frame.canvas.takenLine.get(newLine)==1)
                                        whoWin=1;
                                    else
                                        whoWin=2;
                                    return;
                                }
                            }
                        }
                }
        }
    }
    private boolean commonEnd(Polygon line1, Polygon line2){
        if((line2.xpoints[0]==line1.xpoints[0]&&line2.ypoints[0]==line1.ypoints[0])||(line2.xpoints[0]==line1.xpoints[3]&&line2.ypoints[0]==line1.ypoints[3])||
                        (line2.xpoints[3]==line1.xpoints[0]&&line2.ypoints[3]==line1.ypoints[0])||(line2.xpoints[3]==line1.xpoints[3]&&line2.ypoints[3]==line1.ypoints[3]))
            return true;
        return false;
    }
}