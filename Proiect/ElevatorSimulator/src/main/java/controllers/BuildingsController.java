/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.BuildingsService;

/**
 *
 * @author Vlad Adriana
 */
@RestController
@RequestMapping("/buildings")
public class BuildingsController {
    @Autowired
    private BuildingsService buildingsService;
    public BuildingsController(){
        
    }
    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllNames(){
        return new ResponseEntity<>(buildingsService.getAllNames(),HttpStatus.FOUND);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<String> checkName(@PathVariable("name") String name){
        if(!buildingsService.checkName(name))
            return new ResponseEntity<>("Mathing building found", HttpStatus.FOUND);
        return new ResponseEntity<>("Mathing building not found", HttpStatus.NOT_FOUND);
    }
    @PostMapping(value="/create",  consumes="application/x-www-form-urlencoded")
    public ResponseEntity<String> createBuilding(@RequestParam String name){
        if(buildingsService.createBuilding(name))
            return new ResponseEntity<>("Building created successfully", HttpStatus.CREATED);
        return new ResponseEntity<>("Failed to create building", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value = "/{name}")
    public ResponseEntity<String> deleteBuilding(@PathVariable("name") String name){
        if(!buildingsService.deleteBuilding(name))
            return new ResponseEntity<>("Building not found", HttpStatus.GONE);
        return new ResponseEntity<>("Building deleted successfully", HttpStatus.OK);
    }
    @PostMapping(value="/addRights",  consumes="application/x-www-form-urlencoded")
    public ResponseEntity<String> addRights(@RequestParam int id, @RequestParam String name){
        if(buildingsService.addRights(id,name))
            return new ResponseEntity<>("Rights added successfully", HttpStatus.CREATED);
        return new ResponseEntity<>("Failed to add rights", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping(value="/removeRights/id/{id}/name/{name}")
    public ResponseEntity<String> removeRights(@PathVariable("id") int id, @PathVariable("name") String name){
        if(buildingsService.removeRights(id,name))
            return new ResponseEntity<>("Rights removed successfully", HttpStatus.OK);
        return new ResponseEntity<>("Failed to remove rights", HttpStatus.GONE);
    }
}
