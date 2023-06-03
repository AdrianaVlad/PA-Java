/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elevatorsimulator;
import entities.Elevator;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.ElevatorRepository;
/**
 *
 * @author Vlad Adriana
 */
public class MovingElevator extends Thread{
    MoveRequests moveRequests;
    @Autowired
    ElevatorRepository elevatorRepository  = new ElevatorRepository();
    int currentFloor;
    Elevator elevator;
    int elevatorId;
    public MovingElevator(MoveRequests moveRequests, int elevatorId){
        this.moveRequests=moveRequests;
        this.elevatorId=elevatorId;
    }
    public void run(){
        while(moveRequests.exists(elevatorId)){
            try {
                elevatorRepository.refresh(elevatorId);
                elevator = elevatorRepository.findById(elevatorId);
                currentFloor=elevator.getCurrentFloor();
                if(moveRequests.isDestination(elevatorId,elevator.getCurrentFloor())){
                    moveRequests.fulfilled(elevatorId,elevator.getCurrentFloor());
                    elevatorRepository.updateStatusById(elevatorId,"open");
                    sleep(8000);
                }
                else{
                    int destination = moveRequests.getFirst(elevatorId);
                    if(destination>currentFloor){
                        elevatorRepository.updateStatusById(elevatorId,"movingUp");
                        elevatorRepository.updateFloorById(elevatorId,currentFloor+1);
                    }
                    else{
                        elevatorRepository.updateStatusById(elevatorId,"movingDown");
                        elevatorRepository.updateFloorById(elevatorId,currentFloor-1);
                    }
                }
                sleep(2000);
            } catch (SQLException | InterruptedException ex) {
                Logger.getLogger(MovingElevator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            elevatorRepository.refresh(elevatorId);
            elevator = elevatorRepository.findById(elevatorId);
            if(!elevator.getStatus().equals("broken"))
                elevatorRepository.updateStatusById(elevatorId, "working");
            moveRequests.endedThread(elevatorId);
        } catch (SQLException ex) {
            Logger.getLogger(MovingElevator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
