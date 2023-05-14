/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serverapplication;

import java.awt.Color;
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
    private Game game;
    private final GameServer server;
    private int playerNr;
    BufferedReader in;
    PrintWriter out;
    TournamentManager tournament;
    public ClientThread(Socket socket,GameServer server){
        this.socket=socket;
        this.server=server;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void place(int lin, int col, Color color){
        char letter = (char) (lin+'A');
        col++;
        out.println("Placed "+letter+col+" "+color.getRGB());
        out.flush();
    }
    public void won(String response){
        out.println(response+" You can start a new game now");
        out.flush();
        game=null; 
    }
    @Override
    public void run(){
        try {
            String request,response;
            while(running){
                request = in.readLine();
                if(game!=null && game.outOfTime(playerNr) && game.isTurn(playerNr)){
                    server.gameList.remove(game);
                    game.setWon("Player"+game.turn+" ran out of time! Player"+(game.turn%2+1)+" won the game!");
                    response=null;
                }
                else{
                    if(request.equals("create tournament")){
                        if(game!=null){
                            response="Please do this when you're not in game, you're on a timer!";
                        }
                        else{
                            response="Please specify: number of players, max games per player per day, max days for tournament (ex: 10,5,3)";
                            out.println(response);
                            out.flush();
                            request = in.readLine();
                            String parts[] = request.split(",",3);
                            int n=Integer.parseInt(parts[0]),p=Integer.parseInt(parts[1]),d=Integer.parseInt(parts[2]);
                            tournament = new TournamentManager(n,p,d);
                            if(tournament.generateSchedule())
                                response="Tournament schedule: \n"+tournament.displaySchedule();
                            else response="Such a tournament cannot exist!";
                        }
                    }
                    else if(request.equals("get tournament rankings")){
                        tournament.generateOutcomes();
                        response="Based on random game outcomes:"+tournament.displayRankings();
                    }
                    else if(request.equals("stop")){
                        running=false;
                        response="Server stopped";
                    }
                    else if(request.equals("create game")){
                        if(game!=null)
                            response="You're already in a game!";
                        else{
                            response="Starting new game! Please enter a game name so others can join:";
                            out.println(response);
                            out.flush();
                            request = in.readLine();
                            game = server.createGame(request);
                            if(game==null)
                                response="There already is a game with that name! Try joining it or choosing a different name";
                            else{
                                game.setPlayer1(new Player(this,Color.RED));
                                playerNr=1;
                                response="Game started! You are Player1. You have 1 minute. It's your turn!";
                                game.getPlayer(playerNr).timer.start();
                                while(!game.isGameReady()){
                                    try {
                                    sleep(100);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                game.setTurn(1);
                            }
                        }
                    }
                    else if(request.equals("join game")){
                        if(game!=null)
                            response="You're already in a game!";
                        else{
                            response="Joining a game! Please enter the name of the game you want to join:";
                            out.println(response);
                            out.flush();
                            request = in.readLine();
                            game = server.findGame(request);
                            if(game==null)
                                response="There is no game with that name! Try creating it or choosing a different name";
                            else{
                                game.setPlayer2(new Player(this,Color.BLUE));
                                playerNr=2;
                                response="Game started! You are Player2. You will have 1 minute. Please wait your turn!";
                                game.getPlayer(playerNr).timer.start();
                                game.setTurn(1);
                            }
                        }
                    }
                    else if(request.equals("submit move")){
                        if(game==null)
                            response="You have to join a game first!";
                        else{
                            if(game.isTurn(playerNr)){
                                response="Please select position to place on (ex: A1):";
                                out.println(response);
                                out.flush();
                                request = in.readLine();
                                int lin=request.charAt(0)-'A'+1,col=Integer.parseInt(request.substring(1));
                                if(game.isFree(lin,col)){
                                    if(!game.setPiece(lin,col,playerNr)){
                                        game.setTurn(playerNr%2+1);
                                        response="Piece placed! Waiting for the other player's move..";
                                    }
                                    else{
                                        response="Player"+playerNr+" won the game!";
                                        game.won=true;
                                    }
                                }
                                else
                                    response="Position is already taken or is out of bounds";
                            }
                            else{
                                response="It's not your turn yet!";
                            }
                        }
                    }
                    else if(request.equals("leave game")){
                        if(game==null)
                            response="You aren't in a game yet!";
                        else{
                            server.gameList.remove(game);
                            game.setWon("Player"+playerNr+" left the game, so Player"+(playerNr%2+1)+" won! You can join a new game now");
                            response=null;
                        }
                    }
                    else
                        response="Server received the request '"+request+"' but it doesn't do anything";  
                }
                if(game!=null && game.won){
                    server.gameList.remove(game);
                    game.setWon("Player"+game.turn+" got 5 in a row and won the game!");
                    response=null;
                }
                if(response!=null){
                    out.println(response);
                    out.flush();
                }
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
