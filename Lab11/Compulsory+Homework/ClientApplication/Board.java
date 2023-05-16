/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clientapplication;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author Vlad Adriana
 */
public class Board extends JPanel{
    final MainFrame frame;
    final static int W = 610, H = 610;
    final static int nrLines=15;
    BufferedImage image;
    Graphics2D graphics;
    public Board(MainFrame frame){
        this.frame = frame;
        createOffscreenImage();
        initPanel();
        createBoard();
    }
    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 800, 600);
    }
     final public void initPanel() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
    }
    final void createBoard() {
        createOffscreenImage();
        drawLines();
        repaint();
    }
     private void drawLines() {
        graphics.setColor(Color.BLACK);
        int i,coord;
        for(i=0; i <= nrLines; i++){
            coord=i*(H/nrLines);
            graphics.drawLine(coord+5,5,coord+5,H-5);
            graphics.drawLine(5,coord+5,H-5,coord+5);
        }
    }
    public void place(int lin, int col, Color color){
        graphics.setColor(color);
        lin--;
        col--;
        graphics.fillOval(lin*(H/nrLines)+7,col*(H/nrLines)+7,H/nrLines-4, H/nrLines-4);
        repaint();
    }
    @Override
    public void update(Graphics g) {
    }
    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }
}
