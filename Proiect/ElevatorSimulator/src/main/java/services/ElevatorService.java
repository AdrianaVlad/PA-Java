/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.mycompany.elevatorsimulator.MoveRequests;
import entities.Building;
import entities.Elevator;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.BuildingRepository;
import repositories.ElevatorRepository;

/**
 *
 * @author VladAdriana
 */
@Service
public class ElevatorService {
    ElevatorRepository elevatorRepository = new ElevatorRepository();
    BuildingRepository buildingRepository = new BuildingRepository();
    MoveRequests moveRequests = new MoveRequests();
    @Autowired
    public ElevatorService(){
        
    }
    public boolean createElevator(String status, int lowestFloor, int highestFloor, int currentFloor, String buildingName){
        try {
            Building building = buildingRepository.findByName(buildingName);
            int id = elevatorRepository.findNextId();
            elevatorRepository.insert(new Elevator(id,lowestFloor,highestFloor,status,currentFloor,building));
            return true;
        }
        catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean deleteElevator(int column,String buildingName){
        try {
            Building building = buildingRepository.findByName(buildingName);
            List<Elevator> elevatorList = building.getElevatorsList();
            elevatorRepository.delete(elevatorList.get(column-1));
            moveRequests.delete(elevatorList.get(column-1).getId());
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean updateElevator(int column, String status, int lowestFloor, int highestFloor, int currentFloor, String buildingName){
        try {
            Building building = buildingRepository.findByName(buildingName);
            List<Elevator> elevatorList = building.getElevatorsList();
            elevatorRepository.update(elevatorList.get(column-1),status,lowestFloor,highestFloor,currentFloor);
            return true;
        }
        catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public List<Elevator> getElevatorsFor(String buildingName){
        try {
            elevatorRepository.refresh();
            buildingRepository.refresh();
            Building building = buildingRepository.findByName(buildingName);    
            return building.getElevatorsList();
        } catch (SQLException ex) {
            Logger.getLogger(ElevatorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public List<Elevator> getValidElevators(String buildingName, int floor){
        try {
            elevatorRepository.refresh();
            buildingRepository.refresh();
            Building building = buildingRepository.findByName(buildingName);    
            List<Elevator> elevatorList =  elevatorRepository.findCanReachFloor(floor, building);
            return elevatorList.stream().filter(e -> e.getStatus().equals("working") || e.getStatus().startsWith("moving")).collect(Collectors.toList());
        } catch (SQLException ex) {
            Logger.getLogger(ElevatorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Elevator getElevator(int id){
        try {
            elevatorRepository.refresh();
            return elevatorRepository.findById(id);
        } catch (SQLException ex) {
            Logger.getLogger(ElevatorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public boolean moveElevator(int id, int floor){
        try {
            Elevator elevator = elevatorRepository.findById(id);
            if(elevator!=null){
                moveRequests.request(id,floor);
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ElevatorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean brokenElevator(int id){
        try {
            Elevator elevator = elevatorRepository.findById(id);
            if(elevator!=null){
                moveRequests.remove(id);
                elevatorRepository.updateStatusById(id, "broken");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ElevatorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
