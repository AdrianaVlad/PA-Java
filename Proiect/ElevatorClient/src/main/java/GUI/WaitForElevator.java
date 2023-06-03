/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import mappers.Elevator;

/**
 *
 * @author Vlad Adriana
 */
public class WaitForElevator extends Thread{
    MainFrame frame;
    Elevator elevator;
    int clientFloor;
    boolean stops,temp;
    public WaitForElevator(MainFrame frame, Elevator elevator, int clientFloor){
        this.frame=frame;
        this.elevator=elevator;
        this.clientFloor=clientFloor;
    }
    public WaitForElevator(MainFrame frame, Elevator elevator){
        this.frame=frame;
        this.elevator=elevator;
        stops=true;
        temp=true;
    }
    @Override
    public void run(){
        frame.remove(frame.title);
        frame.title=new JLabel("");
        frame.add(frame.title,NORTH);
        frame.pack();
        for(elevator = frame.comms.getElevator(elevator.getId());
                elevator.getCurrentFloor()!=clientFloor || stops;
                elevator = frame.comms.getElevator(elevator.getId())){
            try {
                if(elevator.getStatus().equals("open")||elevator.getStatus().equals("broken"))
                    frame.title.setText("Elevator stopped at floor:"+Integer.toString(elevator.getCurrentFloor()));
                else 
                    frame.title.setText("Elevator at floor:"+Integer.toString(elevator.getCurrentFloor()));
                frame.repaint();
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(WaitForElevator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(!temp){
            InElevator inside = new InElevator(frame,elevator);
            frame.add(inside,CENTER);
            frame.pack();
            frame.repaint();
        }
    }
}
