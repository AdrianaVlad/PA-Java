package com.mycompany.homework;

import java.util.Objects;

/**
* Location is the abstract base class for all the location types
* used for this problem. A Location object represents a location
* on a map, characterized by:
* <ul>
* <li> its x and y coordinates on a map
* <li> its name
* <li> its type: city, airport, gas station etc.
* </ul>
* <p>
* Getters and setters also implemented.
* 
* @author Vlad Adriana
*/
abstract public class Location{
    protected float xCoord,yCoord;
    protected String name, type;
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public float getxCoord() {
        return xCoord;
    }
    public float getyCoord() {
        return yCoord;
    }
    public void setType(String type) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Float.floatToIntBits(this.xCoord);
        hash = 37 * hash + Float.floatToIntBits(this.yCoord);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.type);
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
        final Location other = (Location) obj;
        if (Float.floatToIntBits(this.xCoord) != Float.floatToIntBits(other.xCoord)) {
            return false;
        }
        if (Float.floatToIntBits(this.yCoord) != Float.floatToIntBits(other.yCoord)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.type, other.type);
    }

 
    
}