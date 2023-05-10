/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serverapplication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Vlad Adriana
 */
public class GameServer {
    public static final int PORT = 2434;
    public GameServer() throws IOException{
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            while(true){
                System.out.println("Waiting for a client...");
                Socket socket = serverSocket.accept();
                new ClientThread(socket).start();
            }
        } catch (IOException e){
            System.err.println("Ooops :( " +e);
        }
    }
}
