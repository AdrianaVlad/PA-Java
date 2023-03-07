package com.mycompany.homework;

import java.util.Objects;

/**
* Road is the class that represents all road types for
* this problem. A Road object represents a road
* on a map, characterized by:
* <ul>
* <li> its length
* <li> its speed limit
* <li> the 2 locations (ends) it connects
* <li> its type: via an <code>enum</code> with the elements: HIGHWAY, EXPRESS 
* and COUNTRY
* </ul>
* <p>
* Getters and setters also implemented.
* 
* @author Vlad Adriana
*/

public class Road{
    protected RoadType type;
    protected float length, speedLimit;
    protected Location end1,end2;
    public enum RoadType{
    HIGHWAY, EXPRESS, COUNTRY;
}
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.type);
        hash = 37 * hash + Float.floatToIntBits(this.length);
        hash = 37 * hash + Float.floatToIntBits(this.speedLimit);
        hash = 37 * hash + Objects.hashCode(this.end1);
        hash = 37 * hash + Objects.hashCode(this.end2);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Road other = (Road) obj;
        if (Float.floatToIntBits(this.length) != Float.floatToIntBits(other.length)) {
            return false;
        }
        if (Float.floatToIntBits(this.speedLimit) != Float.floatToIntBits(other.speedLimit)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.end1, other.end1)) {
            return false;
        }
        return Objects.equals(this.end2, other.end2);
    }
    
}