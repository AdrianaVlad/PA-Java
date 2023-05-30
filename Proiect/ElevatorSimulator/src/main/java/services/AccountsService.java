/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Accounts;
import entities.Buildings;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.AccountsRepository;

/**
 *
 * @author Vlad Adriana
 */
@Service
public class AccountsService {
    AccountsRepository accountsRepository = new AccountsRepository();
    @Autowired
    public AccountsService(){
        
    }
    public boolean checkPassword(String username, String password){
        try {
            Accounts account = accountsRepository.findByName(username);
            return account.getPassword().equals(password);
        }
        catch (NoResultException ex){
            return false;
        }
        catch (SQLException ex) {
            Logger.getLogger(AccountsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean createAccount(String username, String password, String type){
        try {
            accountsRepository.insert(new Accounts(accountsRepository.findNextId(),username,password,type));
            return true;
        }
        catch (SQLException ex) {
            Logger.getLogger(AccountsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public Accounts getAccountByName(String username){
        try {
            Accounts account = accountsRepository.findByName(username);
            return account;
        } catch (SQLException ex) {
            Logger.getLogger(AccountsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public List<String> getBuildingsById(int id){
        try {
            List<Buildings> buildingsList = accountsRepository.findById(id).getBuildingsList();
            List<String> buildingsNames = new ArrayList<>();
            for(Buildings building : buildingsList){
                buildingsNames.add(building.getName());
            }
            return buildingsNames;
        } catch (SQLException ex) {
            Logger.getLogger(AccountsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public boolean deleteAccount(int id){
        try {
            Accounts account = accountsRepository.findById(id);
            accountsRepository.delete(account);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AccountsService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean updateUsername(int id,String username){
        try {
            accountsRepository.updateNameById(id,username);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AccountsService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean updatePassword(int id,String username){
        try {
            accountsRepository.updatePasswordById(id,username);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AccountsService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
