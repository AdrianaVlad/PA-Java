/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Vlad Adriana
 */
public class SelectBuildingPanel extends JPanel{
    MainFrame frame;
    JButton button;
    List<String> buildingNames;
    public SelectBuildingPanel(MainFrame frame) {
        this.frame=frame;
        init();
    }
    private void init(){
        setLayout(new GridLayout(5, 3, 40, 50));
        if(frame.account.getType().equals("client"))
            buildingNames = frame.comms.getAllBuildingNames();
        else
            buildingNames = frame.comms.getBuildingNamesFor(frame.account.getId());
        for(String name : buildingNames){
            button = new JButton(name);
            button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    frame.getContentPane().removeAll();
                    JLabel title = new JLabel("Building: "+name);
                    title.setFont(new Font("Serif", Font.PLAIN, 30));
                    frame.add(title,NORTH);
                    MenuInBuilding menu = new MenuInBuilding(frame,name);
                    frame.add(menu,SOUTH);
                    frame.pack();
                }
            });
            add(button);
        }
    }
    
}
