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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Vlad Adriana
 */
public class AddAdmin extends JPanel{
    MainFrame frame;
    JLabel statement,usernameLabel,passwordLabel,failedLabel;
    JTextField usernameField = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);
    JButton submit,back;
    boolean failedAttempt;
    public AddAdmin(MainFrame frame){
        this.frame=frame;
        init();
    }
    private void init(){
        statement = new JLabel("Please add admin details:");
        statement.setFont(new Font("Serif", Font.PLAIN, 30));
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        submit = new JButton("submit");
        submit.addActionListener(this::submit);
        back = new JButton("back to select");
        back.addActionListener(this::toSelect);
        setLayout(new GridLayout(5, 2, 40, 50));
        add(statement);
        add(new JLabel(" "));
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(submit);
        add(back);
        if(failedAttempt){
            failedLabel = new JLabel("That information already matches an existing account!");
            failedLabel.setFont(new Font("Serif", Font.PLAIN, 15));
            failedLabel.setForeground(Color.RED);
            add(failedLabel);
        }
    }
    public void submit(ActionEvent e){
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        boolean accountFound = frame.comms.checkPassword(username,password);
        if(!accountFound){
            frame.comms.createAccount(username,password,"admin");
            toSelect(e);
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
    public void toSelect (ActionEvent e){
        frame.getContentPane().removeAll();
        frame.login.moveToSelectBuilding(3);
    }
}
