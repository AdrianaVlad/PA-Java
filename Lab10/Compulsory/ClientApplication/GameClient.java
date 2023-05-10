/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clientapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vlad Adriana
 */
public class GameClient extends Thread{
    private final String serverAdress = "127.0.0.1";
    private final int PORT = 2434;
    private boolean running=true;
    @Override
    public void run(){
        try{
            Socket socket = new Socket(serverAdress,PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String request,response;
            Scanner systemIn = new Scanner(System.in);
            while(running){
                request = systemIn.nextLine();
                if(request.equals("exit"))
                    running=false;
                out.println(request);
                response = in.readLine();
                System.out.println(response);
            }
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        } catch (IOException ex) {
            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
