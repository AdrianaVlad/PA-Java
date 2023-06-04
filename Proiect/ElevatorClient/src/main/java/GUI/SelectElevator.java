/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mappers.Elevator;

/**
 *
 * @author Vlad Adriana
 */
public class SelectElevator extends JPanel{
    MainFrame frame;
    List<Elevator> elevatorList;
    JButton back, button;
    int clientFloor;
    public SelectElevator(MainFrame frame, List<Elevator> elevatorList, int clientFloor){
        this.frame=frame;
        this.elevatorList=elevatorList;
        this.clientFloor=clientFloor;
        init();
    }
    private void init(){
        setLayout(new GridLayout(5, 3, 40, 50));
         for(Elevator elevator : elevatorList){
            button = new JButton(Integer.toString(elevator.getId())+": "+elevator.getStatus());
            button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    frame.getContentPane().removeAll();
                    frame.comms.startMoving(elevator.getCode(),clientFloor);
                    WaitForElevator display = new WaitForElevator(frame,elevator,clientFloor);
                    display.start();
                }
            });
            add(button);
        }
    }
    public void toSelect (ActionEvent e){
        frame.getContentPane().removeAll();
        frame.login.moveToSelectBuilding(0);
    }
}
