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
    boolean addedRights,removedRights;
    public MenuInBuilding(MainFrame frame, String buildingName){
        this.frame=frame;
        this.buildingName=buildingName;
        init();
    }
    private void init(){
        back = new JButton("beck to building select");
        back.addActionListener(this::toSelect);
        if(frame.account.getUsername().equals("overseer")){
            JButton addAdminRights = new JButton("add admin rights");
            addAdminRights.addActionListener(this::addAdminRights);
            JButton removeAdminRights = new JButton("remove admin rights");
            removeAdminRights.addActionListener(this::removeAdminRights);
            add(addAdminRights);
            add(removeAdminRights);
            if(addedRights){
                noticeLabel = new JLabel("rights added successfully!");
                noticeLabel.setFont(new Font("Serif", Font.PLAIN, 15));
                noticeLabel.setForeground(Color.RED);
                add(noticeLabel,SOUTH);
            }
            else if(removedRights){
                noticeLabel = new JLabel("rights removed successfully!");
                noticeLabel.setFont(new Font("Serif", Font.PLAIN, 15));
                noticeLabel.setForeground(Color.RED);
                add(noticeLabel,SOUTH);
            }
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
    }
    public void removeAdminRights(ActionEvent e){
        frame.getContentPane().removeAll();
        RemoveAdminRights removeAdmin = new RemoveAdminRights(frame,buildingName);
        frame.add(removeAdmin);
        frame.pack();
    }
    public void setFlags(int flag){
        if(flag==1){
            addedRights=true;
            removedRights=false;
        }
        else{
            removedRights=true;
            addedRights=false;
        }
    }
}
