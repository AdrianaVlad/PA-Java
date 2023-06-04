/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.WEST;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Vlad Adriana
 */
public class AddElevator extends JPanel{
    MainFrame frame;
    String buildingName;
    JLabel statement,statusLabel,lowestFloorLabel,highestFloorLabel,currentFloorLabel;
    JTextField statusField = new JTextField(20);
    JFormattedTextField lowestFloorField,highestFloorField,currentFloorField;
    JButton submit,back;
    int flag=0;
    public AddElevator(MainFrame frame, String buildingName){
        this.frame=frame;
        this.buildingName=buildingName;
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        NumberFormatter formatter = new NumberFormatter(numberFormat);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false);
        lowestFloorField = new JFormattedTextField(formatter);
        highestFloorField = new JFormattedTextField(formatter);
        currentFloorField = new JFormattedTextField(formatter);
        init();
    }
    private void init(){
        statement = new JLabel("Please add elevator details:");
        statement.setFont(new Font("Serif", Font.PLAIN, 30));
        statusLabel = new JLabel("Status:");
        statusLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        lowestFloorLabel = new JLabel("Lowest Floor:");
        lowestFloorLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        highestFloorLabel = new JLabel("Highest Floor:");
        highestFloorLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        currentFloorLabel = new JLabel("Current Floor:");
        currentFloorLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        submit = new JButton("submit");
        submit.addActionListener(this::submit);
        back = new JButton("back to building");
        back.addActionListener(this::toBuilding);
        setLayout(new GridLayout(6, 2, 40, 50));
        add(statement);
        add(new JLabel(" "));
        add(statusLabel);
        add(statusField);
        add(lowestFloorLabel);
        add(lowestFloorField);
        add(highestFloorLabel);
        add(highestFloorField);
        add(currentFloorLabel);
        add(currentFloorField);
        add(submit);
        add(back);
    }
    public void submit(ActionEvent e){
        String status = statusField.getText();
        int lowestFloor = (int) lowestFloorField.getValue();
        int highestFloor = (int) highestFloorField.getValue();
        int currentFloor = (int) currentFloorField.getValue();
        frame.comms.createElevator(status,lowestFloor,highestFloor,currentFloor,buildingName);
        flag=3;
        toBuilding(e); 
    }
    public void toBuilding (ActionEvent e){
        frame.getContentPane().removeAll();
        JLabel title = new JLabel("Building: "+buildingName);
        title.setFont(new Font("Serif", Font.PLAIN, 30));
        frame.add(title,NORTH);
        MenuInBuilding menu = new MenuInBuilding(frame,buildingName,flag);
        frame.add(menu,SOUTH);
        ElevatorGrid grid = new ElevatorGrid(frame,buildingName);
        new Thread(grid).start();
        frame.add(grid,CENTER);
        frame.add(grid.floorNumbers,WEST);
        frame.pack();
        frame.repaint();
    }
}
