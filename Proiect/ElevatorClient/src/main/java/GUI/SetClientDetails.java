/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import static java.awt.BorderLayout.SOUTH;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.NumberFormatter;
import mappers.Elevator;

/**
 *
 * @author Vlad Adriana
 */
public class SetClientDetails extends JPanel{
    MainFrame frame;
    String buildingName;
    JLabel statement, floorLabel,failedLabel;
    JButton back,submit;
    JFormattedTextField floorField;
    boolean failedAttempt;
    public SetClientDetails(MainFrame frame, String buildingName){
        this.buildingName=buildingName;
        this.frame=frame;
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        NumberFormatter formatter = new NumberFormatter(numberFormat);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false);
        floorField = new JFormattedTextField(formatter);
        init();
    }
    private void init(){
        back = new JButton("back to building select");
        statement = new JLabel("Please add details:");
        floorLabel = new JLabel("What floor are you on?");
        submit = new JButton("submit");
        submit.addActionListener(this::getElevators);
        back.addActionListener(this::toSelect);
        setLayout(new GridLayout(4, 2, 40, 50));
        add(statement);
        add(new JLabel(""));
        add(floorLabel);
        add(floorField);
        add(submit);
        add(back);
        if(failedAttempt){
            failedLabel = new JLabel("No working elevators for that floor!");
            failedLabel.setFont(new Font("Serif", Font.PLAIN, 15));
            failedLabel.setForeground(Color.RED);
            add(failedLabel);
        }
    }
    public void toSelect (ActionEvent e){
        frame.getContentPane().removeAll();
        frame.login.moveToSelectBuilding(0);
    }
    public void getElevators(ActionEvent e){
        int floor = (int) floorField.getValue();
        List<Elevator> elevatorList= frame.comms.getValidElevators(floor,buildingName);
        if(elevatorList==null){
            failedAttempt = true;
            frame.remove(this);
            this.removeAll();
            this.init();
            frame.add(this);
            frame.pack();
            frame.repaint();
        }
        else{
            SelectElevator select = new SelectElevator(frame,elevatorList,floor);
            frame.add(select,SOUTH);
            frame.pack();
            frame.repaint();
        }
    }
}
