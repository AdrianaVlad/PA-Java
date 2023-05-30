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
import mappers.Account;

/**
 *
 * @author Vlad Adriana
 */
public class DeleteAdmin extends JPanel{
    MainFrame frame;
    JLabel statement,usernameLabel,noticeLabel;
    JTextField usernameField = new JTextField(20);
    JButton submit,back;
    boolean failedAttempt;
    public DeleteAdmin(MainFrame frame){
        this.frame=frame;
        init();
    }
    private void init(){
        statement = new JLabel("Which admin do you want to delete?");
        statement.setFont(new Font("Serif", Font.PLAIN, 30));
        usernameLabel = new JLabel("Name:");
        usernameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        submit = new JButton("submit");
        submit.addActionListener(this::submit);
        back = new JButton("back to select");
        back.addActionListener(this::toSelect);
        setLayout(new GridLayout(5, 2, 40, 50));
        add(statement);
        add(new JLabel(" "));
        add(usernameLabel);
        add(usernameField);
        add(submit);
        add(back);
        if(failedAttempt){
            noticeLabel = new JLabel("No admin with that name!");
            noticeLabel.setFont(new Font("Serif", Font.PLAIN, 15));
            noticeLabel.setForeground(Color.RED);
            add(noticeLabel);
        }
    }
    public void submit(ActionEvent e){
        String username = usernameField.getText();
        Account admin = frame.comms.getAccountByName(username);
        if(admin!=null&&admin.getType().equals("admin")){
            frame.comms.deleteAccount(admin.getId());
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
        frame.login.moveToSelectBuilding(5);
    }
}
