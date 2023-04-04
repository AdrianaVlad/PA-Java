/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonus;

import static java.awt.BorderLayout.EAST;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author avjiu
 */
public class ControlPanel extends JPanel {

    final MainFrame frame;
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");
    JButton exportBtn = new JButton("Export png");
    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 4));
        add(loadBtn);
        add(saveBtn);
        add(resetBtn);
        add(exitBtn);
        add(exportBtn);
        exportBtn.addActionListener(this::exportGame);
        loadBtn.addActionListener(this::loadGame);
        saveBtn.addActionListener(this::saveGame);
        resetBtn.addActionListener(this::resetGame);
        exitBtn.addActionListener(this::exitGame);
    }
    
    private void loadGame(ActionEvent e){
        if(frame.game==null){
            frame.game = new GraphGameFactory().create(frame);
            frame.add(frame.game,EAST);
            frame.revalidate();
            frame.pack();
        }
        try {
            FileInputStream fisLines = new FileInputStream("saveLines");
            ObjectInputStream oisLines = new ObjectInputStream(fisLines);
            frame.canvas.lines = (List<Polygon>) oisLines.readObject();
            FileInputStream fisTaken = new FileInputStream("saveTaken");
            ObjectInputStream oisTaken = new ObjectInputStream(fisTaken);
            frame.canvas.takenLine = (Map<Polygon, Integer>) oisTaken.readObject();
            FileInputStream fisPlayer1 = new FileInputStream("savePlayer1");
            ObjectInputStream oisPlayer1 = new ObjectInputStream(fisPlayer1);
            frame.game.player1 = (Player) oisPlayer1.readObject();
            FileInputStream fisPlayer2 = new FileInputStream("savePlayer2");
            ObjectInputStream oisPlayer2 = new ObjectInputStream(fisPlayer2);
            frame.game.player2 = (Player) oisPlayer2.readObject();
            FileInputStream fisTurn = new FileInputStream("saveTurn");
            ObjectInputStream oisTurn = new ObjectInputStream(fisTurn);
            frame.game.whoTurn = (int) oisTurn.readObject();
            frame.canvas.image = ImageIO.read(new File("D:\\test.png"));
            frame.canvas.graphics = frame.canvas.image.createGraphics();
            frame.canvas.graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            frame.game.onTurn();
            frame.game.onHold();
            repaint();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ControlPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        frame.canvas.repaint();
    }
    private void saveGame(ActionEvent e){
        if(frame.game!=null){
        try {
            ImageIO.write(frame.canvas.image,"png",new File("D:\\test.png"));
            FileOutputStream fosLines = new FileOutputStream("saveLines");
            ObjectOutputStream oosLines = new ObjectOutputStream(fosLines);
            oosLines.writeObject(frame.canvas.lines);
            FileOutputStream fosTaken = new FileOutputStream("saveTaken");
            ObjectOutputStream oosTaken = new ObjectOutputStream(fosTaken);
            oosTaken.writeObject(frame.canvas.takenLine);
            FileOutputStream fosPlayer1 = new FileOutputStream("savePlayer1");
            ObjectOutputStream oosPlayer1 = new ObjectOutputStream(fosPlayer1);
            oosPlayer1.writeObject(frame.game.player1);
            FileOutputStream fosPlayer2 = new FileOutputStream("savePlayer2");
            ObjectOutputStream oosPlayer2 = new ObjectOutputStream(fosPlayer2);
            oosPlayer2.writeObject(frame.game.player2);
            FileOutputStream fosTurn = new FileOutputStream("saveTurn");
            ObjectOutputStream oosTurn = new ObjectOutputStream(fosTurn);
            oosTurn.writeObject(frame.game.whoTurn);
        } catch (IOException ex) {
            Logger.getLogger(ControlPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    private void resetGame(ActionEvent e){
        frame.canvas.graphics.setColor(Color.WHITE);
        frame.canvas.graphics.fillRect(0, 0, 800, 600);
        frame.canvas.repaint();
    }
    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
    private void exportGame(ActionEvent e){
        try {
            ImageIO.write(frame.canvas.image,"png",new File("D:\\test.png"));
        } catch (IOException ex) {
            Logger.getLogger(ControlPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
