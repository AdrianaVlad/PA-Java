package com.example.SpringServer;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Vlad Adriana
 */
@RestController
@RequestMapping("/players")
public class PlayerController {
    public static List<Player> playerList = new ArrayList<>();
    @GetMapping()
    public List<Player> getPlayers() {
        return playerList;
    }
    @GetMapping("/count")
    public int countPlayers(){
        return playerList.size();
    }
    @GetMapping("/{name}")
    public Player findByName(@PathVariable("name") String name){
        for(Player player:playerList){
            if(player.name.equals(name))
                return player;
        }
        return null;
    }
    @GetMapping("/{id}")
    public Player findById(@PathVariable("id") int id){
        for(Player player:playerList){
            if(player.id == id)
                return player;
        }
        return null;
    }
    @PostMapping
    public Player createPlayer(@RequestParam String name){
        int id = 1+playerList.size();
        Player player = new Player(name,id);
        playerList.add(player);
        return player;
    }
    @PostMapping(value = "/obj", consumes="application/json")
        public ResponseEntity<String> createPlayer(@RequestBody Player player) {
        playerList.add(player);
        return new ResponseEntity<>("Player created successfully", HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlayer(@PathVariable int id, @RequestParam String name){
        Player player = findById(id);
        if(player == null){
            return new ResponseEntity<>("Player not found",HttpStatus.NOT_FOUND);
        }
        player.setName(name);
        return new ResponseEntity<>("Player updated successfully",HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable int id){
        Player player = findById(id);
        if(player==null){
            return new ResponseEntity<>("Player not found",HttpStatus.NOT_FOUND);
        }
        playerList.remove(player);
        return new ResponseEntity<>("Player removed", HttpStatus.OK);
    }
}
