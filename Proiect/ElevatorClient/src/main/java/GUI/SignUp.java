/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import static java.awt.BorderLayout.CENTER;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Vlad Adriana
 */
public class SignUp extends JPanel{
    final MainFrame frame;
    JLabel loginLabel,usernameLabel,passwordLabel,failedLabel;
    JTextField usernameField = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);
    JButton signinButton,loginButton;
    boolean failedAttempt;
    public SignUp(MainFrame frame){
        this.frame=frame;
        init();
    }
    private void init(){
        loginLabel = new JLabel("Add new account information:");
        loginLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        signinButton = new JButton("Sign in");
        signinButton.addActionListener(this::checkAccount);
        loginButton = new JButton("Or go back to Log in");
        loginButton.addActionListener(this::moveToLogin);
        setLayout(new GridLayout(5, 2, 40, 50));
        add(loginLabel);
        add(new JLabel(" "));
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(signinButton);
        add(loginButton);
        if(failedAttempt){
            failedLabel = new JLabel("That username already matches an existing account!");
            failedLabel.setFont(new Font("Serif", Font.PLAIN, 15));
            failedLabel.setForeground(Color.RED);
            add(failedLabel);
        }
    }
    public void moveToLogin(ActionEvent e){
        frame.remove(this);
        frame.pack();
        frame.setVisible(true);
        LogIn login = new LogIn(frame,false);
        frame.add(login,CENTER);
        frame.pack();
        frame.repaint();
    }
    public void checkAccount(ActionEvent e){
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        boolean accountFound = frame.comms.checkPassword(username,password);
        if(!accountFound)
            createAccount(username,password);
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
    public void createAccount(String username,String password){
        if(frame.comms.createAccount(username,password,"client")){
            frame.remove(this);
            frame.pack();
            frame.setVisible(true);
            LogIn login = new LogIn(frame,true);
            frame.add(login,CENTER);
            frame.pack();
            frame.repaint();
        }
    }
}
