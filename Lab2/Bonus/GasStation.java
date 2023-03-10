package com.mycompany.bonus;

/**
* GasStation is a class that describes a type of location.
* As such, it extends the abstract class Location.
* In addition to the elements of the base class, a GasStation
* object also contains information regarding the price of the gas
* at that location.
* <p>
* Getters and setters also implemented.
* 
* @author Vlad Adriana
*/
public class GasStation extends Location {
    private float gasPrice;
    public GasStation(float gasPrice, String type, float xCoord, float yCoord, String name) {
        this.gasPrice = gasPrice;
        this.type = type;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.name = name;
    }

    public float getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(float gasPrice) {
        this.gasPrice = gasPrice;
    }
    
}
