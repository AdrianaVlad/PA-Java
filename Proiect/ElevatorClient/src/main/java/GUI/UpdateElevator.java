/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.WEST;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;
import mappers.Elevator;

/**
 *
 * @author Vlad Adriana
 */
public class UpdateElevator extends JPanel{
    MainFrame frame;
    String buildingName;
    JLabel failedLabel,statement,statusLabel,lowestFloorLabel,highestFloorLabel,currentFloorLabel,columnLabel;
    JTextField statusField = new JTextField(20);
    JFormattedTextField lowestFloorField,highestFloorField,currentFloorField,columnField;
    JButton submit,back;
    boolean failedAttempt;
    int flag=0;
    public UpdateElevator(MainFrame frame, String buildingName){
        this.frame=frame;
        this.buildingName=buildingName;
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        NumberFormatter formatter = new NumberFormatter(numberFormat);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false);
        lowestFloorField = new JFormattedTextField(formatter);
        highestFloorField = new JFormattedTextField(formatter);
        currentFloorField = new JFormattedTextField(formatter);
        columnField = new JFormattedTextField(formatter);
        init();
    }
    private void init(){
        statement = new JLabel("Please add elevator details:");
        statement.setFont(new Font("Serif", Font.PLAIN, 30));
        columnLabel = new JLabel("Number (of column):");
        columnLabel.setFont(new Font("Serif", Font.PLAIN, 20));
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
        setLayout(new GridLayout(8, 2, 40, 50));
        add(statement);
        add(new JLabel(" "));
        add(columnLabel);
        add(columnField);
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
        if(failedAttempt){
            failedLabel = new JLabel("There aren't that many elevators in the building!");
            failedLabel.setFont(new Font("Serif", Font.PLAIN, 15));
            failedLabel.setForeground(Color.RED);
            add(failedLabel);
        }
    }
    public void submit(ActionEvent e){
        String status = statusField.getText();
        int column = (int) columnField.getValue();
        int lowestFloor = (int) lowestFloorField.getValue();
        int highestFloor = (int) highestFloorField.getValue();
        int currentFloor = (int) currentFloorField.getValue();
        List<Elevator> elevatorList = frame.comms.getElevatorsFor(buildingName);
        if(elevatorList.size()>=column){
            frame.comms.updateElevator(column,status,lowestFloor,highestFloor,currentFloor,buildingName);
            flag = 5;
            toBuilding(e);
        }
        else{
            failedAttempt = true;
            frame.remove(this);
            this.removeAll();
            this.init();
            frame.add(this);
            frame.pack();
            frame.repaint();
        }
    }
    public void toBuilding (ActionEvent e){
        frame.getContentPane().removeAll();
        JLabel title = new JLabel("Building: "+buildingName);
        title.setFont(new Font("Serif", Font.PLAIN, 30));
        frame.add(title,NORTH);
        MenuInBuilding menu = new MenuInBuilding(frame,buildingName,flag);
        frame.add(menu,SOUTH);
        ElevatorGrid grid = new ElevatorGrid(frame,buildingName);
        frame.add(grid,CENTER);
        frame.add(grid.floorNumbers,WEST);
        frame.pack();
        frame.repaint();
    }
}
