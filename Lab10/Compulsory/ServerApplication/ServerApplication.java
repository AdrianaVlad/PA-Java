/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.serverapplication;

import java.io.IOException;

/**
 *
 * @author Vlad Adriana
 */
public class ServerApplication {

    public static void main(String[] args) {
        try {
            GameServer server = new GameServer();
        } catch (IOException e) {
           System.err.println(e);
        }
    }
}
