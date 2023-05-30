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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.AccountsRepository;
import repositories.BuildingsRepository;

/**
 *
 * @author Vlad Adriana
 */
@Service
public class BuildingsService {
    BuildingsRepository buildingsRepository = new BuildingsRepository();
    @Autowired
    public BuildingsService(){
        
    }
    public List<String> getAllNames(){
        try {
            List<Buildings> buildingsList = buildingsRepository.findAll();
            List<String> buildingsNames = new ArrayList<>();
            for(Buildings building : buildingsList)
                buildingsNames.add(building.getName());
            return buildingsNames;
        } catch (SQLException ex) {
            Logger.getLogger(BuildingsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public boolean checkName(String name){
        try {
            Buildings building = buildingsRepository.findByName(name);
            return building == null;
        } catch (SQLException ex) {
            Logger.getLogger(BuildingsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean createBuilding(String name){
        try {
            buildingsRepository.insert(new Buildings(buildingsRepository.findNextId(),name));
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BuildingsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean deleteBuilding(String name){
        try {
            Buildings building = buildingsRepository.findByName(name);
            buildingsRepository.delete(building);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AccountsService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean addRights(int id, String name){
        try {
            AccountsRepository accountsRepository = new AccountsRepository();
            Buildings building = buildingsRepository.findByName(name);
            Accounts account = accountsRepository.findById(id);
            accountsRepository.addRightsById(id,building);
            buildingsRepository.addRightsById(building.getId(),account);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BuildingsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean removeRights(int id, String name){
        try {
            AccountsRepository accountsRepository = new AccountsRepository();
            Buildings building = buildingsRepository.findByName(name);
            Accounts account = accountsRepository.findById(id);
            accountsRepository.removeRightsById(id,building);
            buildingsRepository.removeRightsById(building.getId(),account);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BuildingsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
