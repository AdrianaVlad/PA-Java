/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Account;
import entities.Building;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.AccountRepository;

/**
 *
 * @author Vlad Adriana
 */
@Service
public class AccountService {
    AccountRepository accountRepository = new AccountRepository();
    @Autowired
    public AccountService(){
        
    }
    public boolean checkPassword(String username, String password){
        try {
            Account account = accountRepository.findByName(username);
            return account.getPassword().equals(password);
        }
        catch (NoResultException ex){
            return false;
        }
        catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean createAccount(String username, String password, String type){
        try {
            accountRepository.insert(new Account(accountRepository.findNextId(),username,password,type));
            return true;
        }
        catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public Account getAccountByName(String username){
        try {
            Account account = accountRepository.findByName(username);
            return account;
        } catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public List<String> getBuildingsByName(String username){
        try {
            accountRepository.refresh();
            List<Building> buildingsList = accountRepository.findByName(username).getBuildingsList();
            List<String> buildingsNames = new ArrayList<>();
            for(Building building : buildingsList){
                buildingsNames.add(building.getName());
            }
            return buildingsNames;
        } catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public boolean deleteAccount(String username){
        try {
            Account account = accountRepository.findByName(username);
            accountRepository.delete(account);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean updateUsername(String oldName,String username){
        try {
            accountRepository.updateNameById(accountRepository.findByName(oldName).getId(),username);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean updatePassword(String username,String password){
        try {
            accountRepository.updatePasswordById(accountRepository.findByName(username).getId(),password);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
