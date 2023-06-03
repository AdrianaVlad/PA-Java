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
    int elevatorCode;
    public MovingElevator(MoveRequests moveRequests, int elevatorCode){
        this.moveRequests=moveRequests;
        this.elevatorCode=elevatorCode;
    }
    @Override
    public void run(){
        while(moveRequests.exists(elevatorCode)){
            try {
                elevatorRepository.refresh(elevatorCode);
                elevator = elevatorRepository.findByCode(elevatorCode);
                currentFloor=elevator.getCurrentFloor();
                if(moveRequests.isDestination(elevatorCode,elevator.getCurrentFloor())){
                    moveRequests.fulfilled(elevatorCode,elevator.getCurrentFloor());
                    elevatorRepository.updateStatusByCode(elevatorCode,"open");
                    sleep(8000);
                }
                else{
                    int destination = moveRequests.getFirst(elevatorCode);
                    if(destination>currentFloor){
                        elevatorRepository.updateStatusByCode(elevatorCode,"movingUp");
                        elevatorRepository.updateFloorByCode(elevatorCode,currentFloor+1);
                    }
                    else{
                        elevatorRepository.updateStatusByCode(elevatorCode,"movingDown");
                        elevatorRepository.updateFloorByCode(elevatorCode,currentFloor-1);
                    }
                }
                sleep(2000);
            } catch (SQLException | InterruptedException ex) {
                Logger.getLogger(MovingElevator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            elevatorRepository.refresh(elevatorCode);
            elevator = elevatorRepository.findByCode(elevatorCode);
            if(!elevator.getStatus().equals("broken"))
                elevatorRepository.updateStatusByCode(elevatorCode, "working");
            moveRequests.endedThread(elevatorCode);
        } catch (SQLException ex) {
            Logger.getLogger(MovingElevator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
