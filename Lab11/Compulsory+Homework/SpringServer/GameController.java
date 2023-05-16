/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringServer;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author avjiu
 */
@RestController
@RequestMapping("/games")
public class GameController {
    public static List<Game> gameList = new ArrayList<>();
    @GetMapping()
    public List<Game> getGames() {
        return gameList;
    }
    @GetMapping("/{name}")
    public Game findByName(@PathVariable("name") String name){
        for(Game game:gameList){
            if(game.getName().equals(name))
                return game;
        }
        return null;
    }
    @PostMapping
    public Game createGame(@RequestParam String name){
        Game game = new Game(name);
        gameList.add(game);
        return game;
    }
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteGame(@PathVariable String name){
        Game game = findByName(name);
        if(game==null){
            return new ResponseEntity<>("Game not found",HttpStatus.NOT_FOUND);
        }
        gameList.remove(game);
        return new ResponseEntity<>("Game removed", HttpStatus.OK);
    }
}
