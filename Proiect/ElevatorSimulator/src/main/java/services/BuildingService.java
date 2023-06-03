/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.mycompany.elevatorsimulator.MoveRequests;
import entities.Account;
import entities.Building;
import entities.Elevator;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.AccountRepository;
import repositories.BuildingRepository;

/**
 *
 * @author Vlad Adriana
 */
@Service
public class BuildingService {
    BuildingRepository buildingRepository = new BuildingRepository();
    @Autowired
    MoveRequests moveRequests;
    @Autowired
    public BuildingService(){
        
    }
    public List<String> getAllNames(){
        try {
            List<Building> buildingsList = buildingRepository.findAll();
            List<String> buildingsNames = new ArrayList<>();
            for(Building building : buildingsList)
                buildingsNames.add(building.getName());
            return buildingsNames;
        } catch (SQLException ex) {
            Logger.getLogger(BuildingService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public boolean checkName(String name){
        try {
            Building building = buildingRepository.findByName(name);
            return building == null;
        } catch (SQLException ex) {
            Logger.getLogger(BuildingService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean createBuilding(String name){
        try {
            buildingRepository.insert(new Building(buildingRepository.findNextId(),name));
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BuildingService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean deleteBuilding(String name){
        try {
            Building building = buildingRepository.findByName(name);
            List<Elevator> elevatorList = building.getElevatorsList();
            for(Elevator elevator:elevatorList){
                moveRequests.remove(elevator.getCode());
            }
            buildingRepository.delete(building);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean addRights(int id, String name){
        try {
            AccountRepository accountsRepository = new AccountRepository();
            Building building = buildingRepository.findByName(name);
            Account account = accountsRepository.findById(id);
            accountsRepository.addRightsById(id,building);
            buildingRepository.addRightsById(building.getId(),account);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BuildingService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean removeRights(int id, String name){
        try {
            AccountRepository accountsRepository = new AccountRepository();
            Building building = buildingRepository.findByName(name);
            Account account = accountsRepository.findById(id);
            accountsRepository.removeRightsById(id,building);
            buildingRepository.removeRightsById(building.getId(),account);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BuildingService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
