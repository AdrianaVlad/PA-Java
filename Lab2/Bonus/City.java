package com.mycompany.bonus;

/**
* City is a class that describes a type of location.
* As such, it extends the abstract class Location.
* In addition to the elements of the base class, a City
* object also contains information regarding the population
* of that location.
* <p>
* Getters and setters also implemented.
* 
* @author Vlad Adriana
*/
public class City extends Location {
    private int population;
    public City(int population, String type, float xCoord, float yCoord, String name) {
        this.population = population;
        this.type = type;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
    
}
