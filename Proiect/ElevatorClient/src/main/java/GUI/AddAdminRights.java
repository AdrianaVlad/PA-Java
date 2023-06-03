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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import mappers.Account;

/**
 *
 * @author Vlad Adriana
 */
public class AddAdminRights extends JPanel{
    MainFrame frame;
    String buildingName;
    JLabel statement,usernameLabel,failedLabel;
    JTextField usernameField = new JTextField(20);
    JButton submit,back;
    int flag=0;
    boolean failedAttempt;
    public AddAdminRights(MainFrame frame, String buildingName){
        this.frame=frame;
        this.buildingName=buildingName;
        init();
    }
    private void init(){
        statement = new JLabel("Please add admin details:");
        statement.setFont(new Font("Serif", Font.PLAIN, 30));
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        submit = new JButton("submit");
        submit.addActionListener(this::submit);
        back = new JButton("back to building");
        back.addActionListener(this::toBuilding);
        setLayout(new GridLayout(5, 2, 40, 50));
        add(statement);
        add(new JLabel(" "));
        add(usernameLabel);
        add(usernameField);
        add(submit);
        add(back);
        if(failedAttempt){
            failedLabel = new JLabel("No admin with that username!");
            failedLabel.setFont(new Font("Serif", Font.PLAIN, 15));
            failedLabel.setForeground(Color.RED);
            add(failedLabel);
        }
    }
    public void submit(ActionEvent e){
        String username = usernameField.getText();
        Account account = frame.comms.getAccountByName(username);
        if(account!=null&&account.getType().equals("admin")){
            frame.comms.addRights(account.getId(),buildingName);
            flag=1;
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
        new Thread(grid).start();
        if(grid.floorNumbers!=null)
            frame.add(grid.floorNumbers,WEST);
        frame.add(grid,CENTER);
        frame.pack();
        frame.repaint();
    }
}
