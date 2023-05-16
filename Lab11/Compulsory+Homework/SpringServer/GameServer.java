/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vlad Adriana
 */
public class GameServer {
    public static final int PORT = 2434;
    public PlayerController playerController  = new PlayerController();
    public GameController gameController = new GameController();
    public GameServer() throws IOException{
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            while(true){
                System.out.println("Waiting for a client...");
                Socket socket = serverSocket.accept();
                new ClientThread(socket,this).start();
            }
        } catch (IOException e){
            System.err.println("Ooops :( " +e);
        }
    }
    public Game createGame(String name){
        if(gameController.findByName(name)!=null)
            return null;
        Game game = gameController.createGame(name);
        return game;
    }
    public Player createPlayer(String name){
        if(playerController.findByName(name)!=null)
            return null;
        Player player = playerController.createPlayer(name);
        return player;
    }
}
