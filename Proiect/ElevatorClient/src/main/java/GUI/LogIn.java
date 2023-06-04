/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;


import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
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
public class LogIn extends JPanel{
    final MainFrame frame;
    JLabel loginLabel,usernameLabel,passwordLabel,noticeLabel;
    JTextField usernameField = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);
    JButton loginButton,signinButton;
    boolean failedAttempt, newAccount, deletedAccount;
    public LogIn(MainFrame frame, boolean newAccount){
        this.frame=frame;
        this.newAccount=newAccount;
        init();
    }
    private void init(){
        loginLabel = new JLabel("Please log in:");
        loginLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        loginButton = new JButton("Log in");
        signinButton = new JButton("or create a new account");
        loginButton.addActionListener(this::checkAccount);
        signinButton.addActionListener(this::moveToSign);
        setLayout(new GridLayout(5, 2, 40, 50));
        add(loginLabel);
        add(new JLabel(" "));
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        if(failedAttempt){
            noticeLabel = new JLabel("That username and/or password does not match any account!");
            noticeLabel.setFont(new Font("Serif", Font.PLAIN, 15));
            noticeLabel.setForeground(Color.RED);
            add(noticeLabel);
            add(new JLabel(" "));
        }
        else if(newAccount){
            noticeLabel = new JLabel("New account created successfully!");
            noticeLabel.setFont(new Font("Serif", Font.PLAIN, 15));
            noticeLabel.setForeground(Color.RED);
            add(noticeLabel);
            add(new JLabel(" "));
        }
        else if(deletedAccount){
            noticeLabel = new JLabel("Account successfully deleted!");
            noticeLabel.setFont(new Font("Serif", Font.PLAIN, 15));
            noticeLabel.setForeground(Color.RED);
            add(noticeLabel);
            add(new JLabel(" "));
        }
        add(loginButton);
        add(signinButton);
    }
    public void moveToSelectBuilding(int flag){
        frame.getContentPane().removeAll();
        frame.pack();
        frame.setVisible(true);
        JLabel selectLabel;
        if(frame.account.getType().equals("client"))
            selectLabel = new JLabel("Please select the building you are in");
        else
            selectLabel = new JLabel("Please select the building you wish to look at");
        selectLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        frame.add(selectLabel, NORTH);
        SelectBuilding building = new SelectBuilding(frame);
        frame.add(building,CENTER);
        MenuOnSelect menu = new MenuOnSelect(frame,flag);
        frame.add(menu,SOUTH);
        frame.pack();
        frame.repaint();
    }
    private void moveToSign( ActionEvent e){
        frame.remove(this);
        frame.pack();
        frame.setVisible(true);
        SignUp signin = new SignUp(frame);
        frame.add(signin,CENTER);
        frame.pack();
        frame.repaint();
    }
    public void checkAccount(ActionEvent e){
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        boolean accountFound = frame.comms.checkPassword(username,password);
        if(accountFound){
            frame.account=frame.comms.getAccountByName(username);
            moveToSelectBuilding(0);
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
    public void setFlags(boolean flag){
        deletedAccount=flag;
        failedAttempt=false;
        newAccount=false;
        frame.remove(this);
        this.removeAll();
        this.init();
        frame.add(this);
        frame.pack();
        frame.repaint();
    }
}
