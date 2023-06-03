/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import entities.Elevator;
import java.util.List;
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
import services.ElevatorService;

/**
 *
 * @author VladAdriana
 */
@RestController
@RequestMapping("/elevators")
public class ElevatorController {
    @Autowired
    ElevatorService elevatorService;
    public ElevatorController(){
        
    }
    @PostMapping(value="/create",  consumes="application/x-www-form-urlencoded")
    public ResponseEntity<String> createElevator(@RequestParam String status, @RequestParam int lowestFloor, @RequestParam int highestFloor, @RequestParam int currentFloor, @RequestParam String buildingName){
        if(elevatorService.createElevator(status,lowestFloor,highestFloor,currentFloor,buildingName))
            return new ResponseEntity<>("Account created successfully", HttpStatus.CREATED);
        return new ResponseEntity<>("Failed to create account", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value = "/number/{number}/buildingName/{buildingName}")
    public ResponseEntity<String> deleteElevator(@PathVariable("number") int number, @PathVariable("buildingName") String buildingName){
        if(!elevatorService.deleteElevator(number,buildingName))
            return new ResponseEntity<>("Elevator not found", HttpStatus.GONE);
        return new ResponseEntity<>("Elevator deleted successfully", HttpStatus.OK);
    }
    @PutMapping(value="/update",  consumes="application/x-www-form-urlencoded")
    public ResponseEntity<String> updateElevator(@RequestParam int column, @RequestParam String status, @RequestParam int lowestFloor, @RequestParam int highestFloor, @RequestParam int currentFloor, @RequestParam String buildingName){
        if(!elevatorService.updateElevator(column,status,lowestFloor,highestFloor,currentFloor,buildingName))
            return new ResponseEntity<>("Elevator not found", HttpStatus.GONE);
        return new ResponseEntity<>("Elevator deleted successfully", HttpStatus.OK);
    }
    @GetMapping("/allFor/{name}")
    public ResponseEntity<List<Elevator>> getElevatorsFor(@PathVariable("name") String buildingName){
        return new ResponseEntity<>(elevatorService.getElevatorsFor(buildingName),HttpStatus.FOUND);
    }
    @GetMapping("/validFor/{name}/{floor}")
    public ResponseEntity<List<Elevator>> getElevatorsFor(@PathVariable("name") String buildingName, @PathVariable("floor") int floor){
        return new ResponseEntity<>(elevatorService.getValidElevators(buildingName,floor),HttpStatus.FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Elevator> getElevator(@PathVariable("id")int id){
        return new ResponseEntity<>(elevatorService.getElevator(id), HttpStatus.OK);
    }
    @PutMapping(value="/move",  consumes="application/x-www-form-urlencoded")
    public ResponseEntity<String> moveElevator(@RequestParam int id, @RequestParam int floor){
        if(!elevatorService.moveElevator(id,floor))
            return new ResponseEntity<>("Elevator not found", HttpStatus.GONE);
        return new ResponseEntity<>("Elevator deleted successfully", HttpStatus.OK);
    }
    @PutMapping(value="/broken",  consumes="application/x-www-form-urlencoded")
    public ResponseEntity<String> brokenElevator(@RequestParam int id){
        if(!elevatorService.brokenElevator(id))
            return new ResponseEntity<>("Elevator not found", HttpStatus.GONE);
        return new ResponseEntity<>("Elevator deleted successfully", HttpStatus.OK);
    }
}