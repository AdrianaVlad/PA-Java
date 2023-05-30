/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.elevatorclient;

import GUI.MainFrame;

/**
 *
 * @author avjiu
 */
public class ElevatorClient {

    public static void main(String[] args) {
        ServerCommunication comms = new ServerCommunication();
        MainFrame frame;
        frame = new MainFrame(comms);
        frame.setVisible(true);
    }
}
