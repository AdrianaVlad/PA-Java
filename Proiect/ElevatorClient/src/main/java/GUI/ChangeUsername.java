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
import javax.swing.JTextField;

/**
 *
 * @author Vlad Adriana
 */
public class ChangeUsername extends JPanel{
    MainFrame frame;
    JLabel statement,usernameLabel;
    JTextField usernameField = new JTextField(20);
    JButton submit,back;
    public ChangeUsername(MainFrame frame){
        this.frame=frame;
        init();
    }
    private void init(){
        statement = new JLabel("Please type new username:");
        statement.setFont(new Font("Serif", Font.PLAIN, 30));
        usernameLabel = new JLabel("username:");
        usernameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        submit = new JButton("submit");
        submit.addActionListener(this::submit);
        back = new JButton("back to select");
        back.addActionListener(this::toSelect);
        setLayout(new GridLayout(3, 2, 40, 50));
        add(statement);
        add(new JLabel(" "));
        add(usernameLabel);
        add(usernameField);
        add(submit);
        add(back);
    }
    public void submit(ActionEvent e){
        String username = usernameField.getText();
        frame.comms.changeUsername(frame.account.getUsername(),username);
        toSelect(e);
    }
    public void toSelect (ActionEvent e){
        frame.getContentPane().removeAll();
        frame.login.moveToSelectBuilding(2);
    }
}
