/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import entities.Account;
import java.util.List;
import services.AccountService;
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
public class AccountController {
    @Autowired
    private AccountService accountService;
    public AccountController(){
        
    }
    @GetMapping("/username/{username}/password/{password}")
    public ResponseEntity<String> checkPassword(@PathVariable("username") String username, @PathVariable("password") String password){
        if(accountService.checkPassword(username,password))
            return new ResponseEntity<>("Mathing user found", HttpStatus.FOUND);
        return new ResponseEntity<>("Mathing user not found", HttpStatus.NOT_FOUND);
    }
    @PostMapping(value="/create",  consumes="application/x-www-form-urlencoded")
    public ResponseEntity<String> createAccount(@RequestParam String username, @RequestParam String password, @RequestParam String type){
        if(accountService.createAccount(username,password,type))
            return new ResponseEntity<>("Account created successfully", HttpStatus.CREATED);
        return new ResponseEntity<>("Failed to create account", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/allinfo/{username}")
    public ResponseEntity<Account> getAccountByName(@PathVariable("username") String username){
        Account account = accountService.getAccountByName(username);
        if(account!=null)
            return new ResponseEntity<>(account,HttpStatus.FOUND);
        else
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
    @GetMapping("/buildingNames/{username}")
    public ResponseEntity<List<String>> getBuildingsById(@PathVariable("username") String username){
        return new ResponseEntity<>(accountService.getBuildingsByName(username),HttpStatus.FOUND);
    }
    @DeleteMapping(value = "/{username}")
    public ResponseEntity<String> deleteAccount(@PathVariable("username") String username){
        if(!accountService.deleteAccount(username))
            return new ResponseEntity<>("Account not found", HttpStatus.GONE);
        return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
    }
    @PutMapping(value="/{oldName}/username",consumes="application/x-www-form-urlencoded")
    public ResponseEntity<String> updateUsername(@PathVariable String oldName, @RequestParam String username) {
        if(!accountService.updateUsername(oldName,username))
             return new ResponseEntity<>("Account not found", HttpStatus.GONE);
        return new ResponseEntity<>("Account updated successfully", HttpStatus.OK);
    }
    @PutMapping(value="/{username}/password",consumes="application/x-www-form-urlencoded")
    public ResponseEntity<String> updatePassword(@PathVariable String username, @RequestParam String password) {
        if(!accountService.updatePassword(username,password))
             return new ResponseEntity<>("Account not found", HttpStatus.GONE);
        return new ResponseEntity<>("Account updated successfully", HttpStatus.OK);
    }
}
