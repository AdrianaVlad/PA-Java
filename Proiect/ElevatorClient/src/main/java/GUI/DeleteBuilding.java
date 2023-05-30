/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Vlad Adriana
 */
public class DeleteBuilding extends JPanel{
    MainFrame frame;
    JLabel statement,nameLabel,noticeLabel;
    JTextField nameField = new JTextField(20);
    JButton submit,back;
    boolean failedAttempt;
    public DeleteBuilding(MainFrame frame){
        this.frame=frame;
        init();
    }
    private void init(){
        statement = new JLabel("Which building do you want to delete?");
        statement.setFont(new Font("Serif", Font.PLAIN, 30));
        nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        submit = new JButton("submit");
        submit.addActionListener(this::submit);
        back = new JButton("back to select");
        back.addActionListener(this::toSelect);
        setLayout(new GridLayout(5, 2, 40, 50));
        add(statement);
        add(new JLabel(" "));
        add(nameLabel);
        add(nameField);
        add(submit);
        add(back);
        if(failedAttempt){
            noticeLabel = new JLabel("No building with that name!");
            noticeLabel.setFont(new Font("Serif", Font.PLAIN, 15));
            noticeLabel.setForeground(Color.RED);
            add(noticeLabel);
        }
    }
    public void submit(ActionEvent e){
        String name = nameField.getText();
        boolean buildingFound = frame.comms.checkName(name);
        if(buildingFound){
            frame.comms.deleteBuilding(name);
            toSelect(e);
        }
        else{
            failedAttempt = true;
            frame.remove(this);
            this.removeAll();
            this.init();
            frame.add(this);
            frame.pack();
        }  
    }
    public void toSelect (ActionEvent e){
        frame.getContentPane().removeAll();
        frame.login.moveToSelectBuilding(6);
    }
}
