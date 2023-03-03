/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.compulsory;

/**
 *
 * @author Vlad Adriana
 */
public class Compulsory {

    public static void main(String[] args) {
        Location Iasi= new Location(Location.LocationType.CITY,10,20,"Iasi");
        Location Vaslui = new Location(Location.LocationType.CITY,10,30,"Vaslui");
        Road R1 = new Road(Road.RoadType.COUNTRY,10,60,Iasi,Vaslui);
        Location IasiAirport = new Location(Location.LocationType.AIRPORT,11,23,"Aeroportul Iasi");
        Road R2 = new Road(Road.RoadType.EXPRESS,2,40,Iasi,IasiAirport);
        System.out.println(Iasi);
        System.out.println(Vaslui);
        System.out.println(R1);
        System.out.println(IasiAirport);
        System.out.println(R2);
    }
}