/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import entities.Accounts;
import java.util.List;
import services.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Vlad Adriana
 */
@RestController
@RequestMapping("/accounts")
public class AccountsController {
    @Autowired
    private AccountsService accountsService;
    public AccountsController(){
        
    }
    @GetMapping("/username/{username}/password/{password}")
    public ResponseEntity<String> checkPassword(@PathVariable("username") String username, @PathVariable("password") String password){
        if(accountsService.checkPassword(username,password))
            return new ResponseEntity<>("Mathing user found", HttpStatus.FOUND);
        return new ResponseEntity<>("Mathing user not found", HttpStatus.NOT_FOUND);
    }
    @PostMapping(value="/create",  consumes="application/x-www-form-urlencoded")
    public ResponseEntity<String> createAccount(@RequestParam String username, @RequestParam String password, @RequestParam String type){
        if(accountsService.createAccount(username,password,type))
            return new ResponseEntity<>("Account created successfully", HttpStatus.CREATED);
        return new ResponseEntity<>("Failed to create account", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/allinfo/{username}")
    public ResponseEntity<Accounts> getAccountByName(@PathVariable("username") String username){
        Accounts account = accountsService.getAccountByName(username);
        if(account!=null)
            return new ResponseEntity<>(account,HttpStatus.FOUND);
        else
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
    @GetMapping("/buildingNames/{id}")
    public ResponseEntity<List<String>> getBuildingsById(@PathVariable("id") int id){
        return new ResponseEntity<>(accountsService.getBuildingsById(id),HttpStatus.FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") int id){
        if(!accountsService.deleteAccount(id))
            return new ResponseEntity<>("Account not found", HttpStatus.GONE);
        return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
    }
    @PutMapping(value="/{id}/username",consumes="application/x-www-form-urlencoded")
    public ResponseEntity<String> updateUsername(@PathVariable int id, @RequestParam String username) {
        if(!accountsService.updateUsername(id,username))
             return new ResponseEntity<>("Account not found", HttpStatus.GONE);
        return new ResponseEntity<>("Account updated successfully", HttpStatus.OK);
    }
    @PutMapping(value="/{id}/password",consumes="application/x-www-form-urlencoded")
    public ResponseEntity<String> updatePassword(@PathVariable int id, @RequestParam String password) {
        if(!accountsService.updatePassword(id,password))
             return new ResponseEntity<>("Account not found", HttpStatus.GONE);
        return new ResponseEntity<>("Account updated successfully", HttpStatus.OK);
    }
}
