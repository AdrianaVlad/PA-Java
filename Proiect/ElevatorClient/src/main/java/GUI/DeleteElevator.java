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
import javax.swing.text.NumberFormatter;
import mappers.Elevator;

/**
 *
 * @author Vlad Adriana
 */
public class DeleteElevator extends JPanel{
    MainFrame frame;
    String buildingName;
    JLabel statement,numberLabel,failedLabel;
    JFormattedTextField numberField;
    JButton submit,back;
    boolean failedAttempt;
    int flag=0;
    public DeleteElevator(MainFrame frame, String buildingName){
        this.frame=frame;
        this.buildingName=buildingName;
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        NumberFormatter formatter = new NumberFormatter(numberFormat);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false);
        numberField = new JFormattedTextField(formatter);
        init();
    }
    private void init(){
        statement = new JLabel("Please add elevator details:");
        statement.setFont(new Font("Serif", Font.PLAIN, 30));
        numberLabel = new JLabel("Number (of column):");
        numberLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        submit = new JButton("submit");
        submit.addActionListener(this::submit);
        back = new JButton("back to building");
        back.addActionListener(this::toBuilding);
        setLayout(new GridLayout(5, 2, 40, 50));
        add(statement);
        add(new JLabel(" "));
        add(numberLabel);
        add(numberField);
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
        List<Elevator> elevatorList = frame.comms.getElevatorsFor(buildingName);
        int column = (int) numberField.getValue();
        if(elevatorList.size()>=column){
            frame.comms.deleteElevator(column,buildingName);
            flag=4;
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
