/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Vlad Adriana
 */
public class GridFloors extends JPanel{
    MainFrame frame;
    int minFloor, maxFloor;
    public GridFloors(MainFrame frame, int minFloor, int maxFloor){
        this.frame=frame;
        this.minFloor=minFloor;
        this.maxFloor=maxFloor;
        init();
    }
    private void init(){
        int nrFloors=maxFloor-minFloor+1;
        setLayout(new GridLayout(nrFloors, 1, 450/nrFloors, 0));
        for(int i=0;i<nrFloors;i++)
            add(new JLabel(String.valueOf(maxFloor-i)));
    }
    public void updateValues(int minFloor, int maxFloor){
        if(minFloor!=this.minFloor || maxFloor!=this.maxFloor){
            this.removeAll();
            this.minFloor=minFloor;
            this.maxFloor=maxFloor;
            init();
        }
    }
}
