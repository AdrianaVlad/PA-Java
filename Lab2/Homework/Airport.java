package com.mycompany.homework;

/**
* Airport is a class that describes a type of location.
* As such, it extends the abstract class Location.
* In addition to the elements of the base class, an Airport
* object also contains information regarding the number of
* terminals at that location.
* <p>
* Getters and setters also implemented.
* 
* @author Vlad Adriana
*/
public class Airport extends Location {
    private int terminals;
    public Airport(int terminals, String type, float xCoord, float yCoord, String name) {
        this.terminals = terminals;
        this.type = type;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.name = name;
    }
    public int getTerminals() {
        return terminals;
    }
    public void setTerminals(int terminals) {
        this.terminals = terminals;
    }
}
