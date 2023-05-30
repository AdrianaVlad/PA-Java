/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import com.mycompany.elevatorclient.ServerCommunication;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import mappers.Account;

/**
 *
 * @author Vlad Adriana
 */
public class MainFrame extends JFrame{
    Account account;
    ServerCommunication comms;
    JLabel title;
    LoginPanel login;
    final static int W=800, H=600;
    public MainFrame(ServerCommunication comms){
        this.comms=comms;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        title = new JLabel("Welcome to our Elevator Simulator");
        title.setFont(new Font("Serif", Font.PLAIN, 40));
        login = new LoginPanel(this,false);
        add(title,NORTH);
        add(login,CENTER);
        setPreferredSize(new Dimension(W, H));
        pack();
    }
}
