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
public class MenuOnSelect extends JPanel{
    MainFrame frame;
    JButton back;
    JLabel noticeLabel;
    boolean deletedAccount,changedPassword,changedUsername,addAdmin,addBuilding,deletedAdmin,deletedBuilding;
    public MenuOnSelect(MainFrame frame, int flag){
        this.frame=frame;
        setFlag(flag);
        init();
    }
    private void init(){
        back = new JButton("log out");
        back.addActionListener(this::logOut);
        if(frame.account.getType().equals("client")){
            JButton changeUsername = new JButton("change username");
            changeUsername.addActionListener(this::changeUsername);
            JButton changePassword = new JButton("change passsword");
            changePassword.addActionListener(this::changePassword);
            JButton deleteAccount = new JButton("delete account");
            deleteAccount.addActionListener(this::deleteAccount);
            add(changeUsername);
            add(changePassword);
            add(deleteAccount);
        }
        else if(frame.account.getUsername().equals("overseer")){
            JButton addAdmin = new JButton("add admin");
            addAdmin.addActionListener(this::addAdmin);
            JButton addBuilding = new JButton("add building");
            addBuilding.addActionListener(this::addBuilding);
            JButton deleteAdmin = new JButton("delete admin");
            deleteAdmin.addActionListener(this::deleteAdmin);
            JButton deleteBuilding = new JButton("delete building");
            deleteBuilding.addActionListener(this::deleteBuilding);
            add(addAdmin);
            add(addBuilding);
            add(deleteAdmin);
            add(deleteBuilding);
        }
        add(back);
        if(changedPassword){
            noticeLabel = new JLabel("password changed successfully!");
            noticeLabel.setFont(new Font("Serif", Font.PLAIN, 15));
            noticeLabel.setForeground(Color.RED);
            add(noticeLabel,SOUTH);
        }
        else if(changedUsername){
            noticeLabel = new JLabel("username changed successfully!");
            noticeLabel.setFont(new Font("Serif", Font.PLAIN, 15));
            noticeLabel.setForeground(Color.RED);
            add(noticeLabel,SOUTH);
        }
        else if (addAdmin){
            noticeLabel = new JLabel("admin added successfully!");
            noticeLabel.setFont(new Font("Serif", Font.PLAIN, 15));
            noticeLabel.setForeground(Color.RED);
            add(noticeLabel,SOUTH);
        }
        else if (addBuilding){
            noticeLabel = new JLabel("building added successfully!");
            noticeLabel.setFont(new Font("Serif", Font.PLAIN, 15));
            noticeLabel.setForeground(Color.RED);
            add(noticeLabel,SOUTH);
        }
        else if (deletedAdmin){
            noticeLabel = new JLabel("admin deleted successfully!");
            noticeLabel.setFont(new Font("Serif", Font.PLAIN, 15));
            noticeLabel.setForeground(Color.RED);
            add(noticeLabel,SOUTH);
        }
        else if (deletedBuilding){
            noticeLabel = new JLabel("building deleted successfully!");
            noticeLabel.setFont(new Font("Serif", Font.PLAIN, 15));
            noticeLabel.setForeground(Color.RED);
            add(noticeLabel,SOUTH);
        }
    }
    public void logOut(ActionEvent e){
        frame.account=null;
        frame.getContentPane().removeAll();
        frame.add(frame.title);
        frame.login.setFlags(deletedAccount);
        frame.add(frame.login);
        frame.pack();
    }
    public void changeUsername(ActionEvent e){
        frame.getContentPane().removeAll();
        ChangeUsername changeUsername = new ChangeUsername(frame);
        frame.add(changeUsername);
        frame.pack();
    }
    public void changePassword(ActionEvent e){
        frame.getContentPane().removeAll();
        ChangePassword changePassword = new ChangePassword(frame);
        frame.add(changePassword);
        frame.pack();
    }
    public void deleteAccount(ActionEvent e){
        frame.comms.deleteAccount(frame.account.getId());
        deletedAccount=true;
        logOut(e);
    }
    public void addAdmin(ActionEvent e){
        frame.getContentPane().removeAll();
        AddAdmin addAdmin = new AddAdmin(frame);
        frame.add(addAdmin);
        frame.pack();
    }
    public void addBuilding(ActionEvent e){
        frame.getContentPane().removeAll();
        AddBuilding addBuilding = new AddBuilding(frame);
        frame.add(addBuilding);
        frame.pack();
    }
    public void deleteAdmin(ActionEvent e){
        frame.getContentPane().removeAll();
        DeleteAdmin deleteAdmin = new DeleteAdmin(frame);
        frame.add(deleteAdmin);
        frame.pack();
    }
    public void deleteBuilding(ActionEvent e){
        frame.getContentPane().removeAll();
        DeleteBuilding deleteBuilding = new DeleteBuilding(frame);
        frame.add(deleteBuilding);
        frame.pack();
    }
    private void setFlag(int flag){
        switch (flag) {
            case 1 -> changedPassword=true;
            case 2 -> changedUsername=true;
            case 3 -> addAdmin=true;
            case 4 -> addBuilding=true;
            case 5 -> deletedAdmin=true;
            case 6 -> deletedBuilding=true;
            default -> {
            }
        }
    }
}
