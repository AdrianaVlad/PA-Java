/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serverapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vlad Adriana
 */
public class ClientThread extends Thread{
    private Socket socket = null;
    private boolean running=true;
    public ClientThread(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String request,response;
            while(running){
                request = in.readLine();
                if(request.equals("stop")){
                    running=false;
                    response="Server stopped";
                }
                else
                    response="Server received the request: "+request;
                out.println(response);
                out.flush();
            }
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        } catch (IOException e) {
            System.err.println("Communication error..."+e);
        } 
    }
}
