/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import static java.awt.BorderLayout.SOUTH;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Vlad Adriana
 */
public class MenuInBuilding extends JPanel{
    MainFrame frame;
    String buildingName;
    JButton back;
    JLabel noticeLabel;
    boolean addedRights,removedRights, addedElevator, removedElevator, updatedElevator;
    public MenuInBuilding(MainFrame frame, String buildingName, int flag){
        this.frame=frame;
        this.buildingName=buildingName;
        setFlags(flag);
        init();
    }
    private void init(){
        back = new JButton("back to building select");
        back.addActionListener(this::toSelect);
        noticeLabel=null;
        if(frame.account.getUsername().equals("overseer")){
            JButton addAdminRights = new JButton("add admin rights");
            addAdminRights.addActionListener(this::addAdminRights);
            JButton removeAdminRights = new JButton("remove admin rights");
            removeAdminRights.addActionListener(this::removeAdminRights);
            add(addAdminRights);
            add(removeAdminRights);
            if(addedRights)
                noticeLabel = new JLabel("rights added successfully!");
            else if(removedRights)
                noticeLabel = new JLabel("rights removed successfully!");
        }
        else if(frame.account.getType().equals("admin")){
            JButton addElevator = new JButton("add elevator");
            addElevator.addActionListener(this::addElevator);
            JButton removeElevator = new JButton("remove elevator");
            removeElevator.addActionListener(this::removeElevator);
            JButton updateElevator = new JButton("update elevator");
            updateElevator.addActionListener(this::updateElevator);
            add(addElevator);
            add(removeElevator);
            add(updateElevator);
            if(addedElevator)
                noticeLabel = new JLabel("Elevator added successfully!");
            else if(removedElevator)
                noticeLabel = new JLabel("Elevator removed successfully!");
            else if(updatedElevator)
                noticeLabel = new JLabel("Elevator updated successfully!");
        }
        else{
            
        }
        if(noticeLabel!=null){
            noticeLabel.setFont(new Font("Serif", Font.PLAIN, 15));
            noticeLabel.setForeground(Color.RED);
            add(noticeLabel,SOUTH);
        }
        add(back);
    }
    public void toSelect(ActionEvent e){
        frame.getContentPane().removeAll();
        frame.login.moveToSelectBuilding(0);
    }
    public void addAdminRights(ActionEvent e){
        frame.getContentPane().removeAll();
        AddAdminRights addAdmin = new AddAdminRights(frame,buildingName);
        frame.add(addAdmin);
        frame.pack();
        frame.repaint();
    }
    public void removeAdminRights(ActionEvent e){
        frame.getContentPane().removeAll();
        RemoveAdminRights removeAdmin = new RemoveAdminRights(frame,buildingName);
        frame.add(removeAdmin);
        frame.pack();
        frame.repaint();
    }
    public void addElevator(ActionEvent e){
        frame.getContentPane().removeAll();
        AddElevator addElevatorPanel = new AddElevator(frame,buildingName);
        frame.add(addElevatorPanel);
        frame.pack();
        frame.repaint();
    }
    public void removeElevator(ActionEvent e){
        frame.getContentPane().removeAll();
        DeleteElevator removeElevatorPanel = new DeleteElevator(frame,buildingName);
        frame.add(removeElevatorPanel);
        frame.pack();
        frame.repaint();
    }
    public void updateElevator(ActionEvent e){
        frame.getContentPane().removeAll();
        UpdateElevator updateElevatorPanel = new UpdateElevator(frame,buildingName);
        frame.add(updateElevatorPanel);
        frame.pack();
        frame.repaint();
    }
    private void setFlags(int flag){
        switch (flag) {
            case 1 -> addedRights=true;
            case 2 -> removedRights=true;
            case 3 -> addedElevator=true;
            case 4 -> removedElevator=true;
            case 5 -> updatedElevator=true;
            default -> {
            }
        }
    }
}
