/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author Vlad Adriana
 */
public class ChangePassword extends JPanel{
    MainFrame frame;
    JLabel statement,passwordLabel;
    JPasswordField passwordField = new JPasswordField(20);
    JButton submit,back;
    public ChangePassword(MainFrame frame){
        this.frame=frame;
        init();
    }
    private void init(){
        statement = new JLabel("Please type new password:");
        statement.setFont(new Font("Serif", Font.PLAIN, 30));
        passwordLabel = new JLabel("password:");
        passwordLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        submit = new JButton("submit");
        submit.addActionListener(this::submit);
        back = new JButton("back to select");
        back.addActionListener(this::toSelect);
        setLayout(new GridLayout(3, 2, 40, 50));
        add(statement);
        add(new JLabel(" "));
        add(passwordLabel);
        add(passwordField);
        add(submit);
        add(back);
    }
    public void submit(ActionEvent e){
        String password = new String(passwordField.getPassword());
        frame.comms.changePassword(frame.account.getUsername(),password);
        toSelect(e);
    }
    public void toSelect (ActionEvent e){
        frame.getContentPane().removeAll();
        frame.login.moveToSelectBuilding(1);
    }
}
