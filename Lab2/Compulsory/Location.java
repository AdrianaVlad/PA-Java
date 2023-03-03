/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compulsory;

/**
 *
 * @author avjiu
 */
public class Location{
    LocationType type;
    float xCoord,yCoord;
    String name;
    public enum LocationType{
    CITY, AIRPORT, GAS_STATION;
}
    public Location(LocationType type, float xCoord, float yCoord, String name) {
        this.type = type;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public LocationType getType() {
        return type;
    }
    public float getxCoord() {
        return xCoord;
    }
    public float getyCoord() {
        return yCoord;
    }
    public void setType(LocationType type) {
        this.type = type;
    }
    public void setxCoord(float xCoord) {
        this.xCoord = xCoord;
    }
    public void setyCoord(float yCoord) {
        this.yCoord = yCoord;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Location{" + "type=" + type + ", xCoord=" + xCoord + ", yCoord=" + yCoord + ", name=" + name + '}';
    }
}
