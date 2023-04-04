/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author avjiu
 */
public class DrawingPanel extends JPanel implements Serializable {

    final MainFrame frame;
    final static int W = 800, H = 600;
    private int numVertices;
    private double edgeProbability;
    private int[] x, y;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the tools needed to draw in the image
    List<Polygon> lines;
    Map<Polygon, Integer> takenLine  = new HashMap<>();
    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        initPanel();
        createBoard();
    }

    final public void initPanel() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(frame.game!=null && frame.game.whoWin==0){
                    for(Polygon line : lines){
                        if(line.contains(e.getX(),e.getY())&&takenLine.get(line)==null){
                            if(frame.game.whoTurn==1){
                                frame.game.player1.markLine(frame,line);
                                frame.game.whoTurn=2;
                                takenLine.put(line,1);
                            }
                            else{
                                frame.game.player2.markLine(frame,line);
                                frame.game.whoTurn=1;
                                takenLine.put(line,2);
                            }
                            
                            frame.game.checkTriangle(line);
                            break;
                        }
                    }
                if(frame.game.whoWin==0){
                    frame.game.onTurn();
                    frame.game.onHold();
                }
                else{
                    frame.game.onWin();
                    frame.game.onLoss();
                }
                repaint();
                }
            }
        });
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 800, 600);
    }

    final void createBoard() {
        lines = new ArrayList<>();
        numVertices = (Integer) frame.configPanel.dotsSpinner.getValue();
        edgeProbability = (Double) frame.configPanel.linesCombo.getSelectedItem();
        createOffscreenImage();
        createVertices();
        drawLines();
        drawVertices();
        repaint();
    }

    private void createVertices() {
        int x0 = W / 2;
        int y0 = H / 2;
        int radius = H / 2 - 10;
        double alpha = 2 * Math.PI / numVertices;
        x = new int[numVertices];
        y = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            x[i] = x0 + (int) (radius * Math.cos(alpha * i));
            y[i] = y0 + (int) (radius * Math.sin(alpha * i));
        }
    }

    private void drawLines() {
        graphics.setColor(Color.BLACK);
        int i,j,rand;
        for(i=0; i < numVertices; i++){
            for(j=i+1; j < numVertices; j++){
                rand = (int)(Math.random() * (101));
                if(rand<=edgeProbability*100){
                    int xPoints[] = { x[i]+5, x[i]+15, x[j]+15, x[j]+5 };
                    int yPoints[] = { y[i]+15, y[i]+5, y[j]+5, y[j]+15 };
                    lines.add(new Polygon(xPoints,yPoints,yPoints.length));
                    graphics.drawLine(x[i]+10,y[i]+10,x[j]+10,y[j]+10);
                }
            }
        }
    }

    private void drawVertices() {
        graphics.setColor(Color.BLACK);
        for(int i=0; i < numVertices; i++){
            graphics.fillOval(x[i],y[i],20,20);
        }
    }

    @Override
    public void update(Graphics g) {
    }
    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }
}