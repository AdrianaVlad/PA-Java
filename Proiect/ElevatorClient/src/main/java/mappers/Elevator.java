/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

/**
 *
 * @author Vlad Adriana
 */
public class Elevator {
    int id,lowestFloor,highestFloor,currentFloor;
    String status;

    public Elevator() {
    }

    public Elevator(int id, int lowestFloor, int highestFloor, int currentFloor, String status) {
        this.id = id;
        this.lowestFloor = lowestFloor;
        this.highestFloor = highestFloor;
        this.currentFloor = currentFloor;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLowestFloor() {
        return lowestFloor;
    }

    public void setLowestFloor(int lowestFloor) {
        this.lowestFloor = lowestFloor;
    }

    public int getHighestFloor() {
        return highestFloor;
    }

    public void setHighestFloor(int highestFloor) {
        this.highestFloor = highestFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
