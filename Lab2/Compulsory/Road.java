/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compulsory;

/**
 *
 * @author avjiu
 */
enum RoadType{
    Highway, Express, Country;
}
public class Road{
    RoadType type;
    float length;
    float speedLimit;
    Location end1,end2;

    public Road(RoadType type, float length, float speedLimit, Location end1, Location end2) {
        this.type = type;
        this.length = length;
        this.speedLimit = speedLimit;
        this.end1 = end1;
        this.end2 = end2;
    }
    public RoadType getType() {
        return type;
    }
    public float getLength() {
        return length;
    }
    public float getSpeedLimit() {
        return speedLimit;
    }
    public Location getEnd1() {
        return end1;
    }
    public Location getEnd2() {
        return end2;
    }
    public void setType(RoadType type) {
        this.type = type;
    }
    public void setLength(float length) {
        this.length = length;
    }
    public void setSpeedLimit(float speedLimit) {
        this.speedLimit = speedLimit;
    }
    public void setEnd1(Location end1) {
        this.end1 = end1;
    }
    public void setEnd2(Location end2) {
        this.end2 = end2;
    }   
    @Override
    public String toString() {
        return "Road{" + "type=" + type + ", length=" + length + ", speedLimit=" + speedLimit + ", end1=" + end1 + ", end2=" + end2 + '}';
    }  
}