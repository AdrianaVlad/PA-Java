/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import static java.awt.BorderLayout.NORTH;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mappers.Elevator;

/**
 *
 * @author Vlad Adriana
 */
public class InElevator extends JPanel{
    MainFrame frame;
    Elevator elevator;
    JButton button, brokenButton, leaveButton;
    JLabel leaveLabel;
    WaitForElevator display;
    boolean broken;
    public InElevator(MainFrame frame, Elevator elevator){
        this.frame=frame;
        this.elevator=elevator;
        init();
    }
    private void init(){
        setLayout(new GridLayout(3,5,10,10));
        display = new WaitForElevator(frame,elevator);
        display.start();
        for(int i=elevator.getLowestFloor();i<=elevator.getHighestFloor();i++){
            button = new JButton(Integer.toString(i));
            button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    int floor=Integer.parseInt(e.getActionCommand());
                    frame.comms.startMoving(elevator.getId(), floor);
                }
            });
            add(button);
        }
        brokenButton=new JButton("BROKEN");
        leaveButton= new JButton("leave");
        brokenButton.addActionListener(this::brokenElevator);
        leaveButton.addActionListener(this::leave);
        add(brokenButton);
        add(leaveButton);
    }
    public void brokenElevator(ActionEvent e){
        if(!broken){
            frame.comms.brokenElevator(elevator.getId());
            leaveLabel= new JLabel("you can leave! it's just a simulation");
            add(leaveLabel);
        }
    }
    public void leave(ActionEvent e){
        elevator=frame.comms.getElevator(elevator.getId());   
        if(broken||(elevator!=null&&elevator.getStatus().equals("open"))){
            frame.getContentPane().removeAll();
            frame.getContentPane().removeAll();
            display.stops=false;
            frame.login.moveToSelectBuilding(0);
            frame.pack();
            frame.repaint();
        }
        else{
            leaveLabel= new JLabel("you can't leave, the elevator is still moving!");
        }  
    }
}
