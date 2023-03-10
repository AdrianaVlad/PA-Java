/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bonus;

/**
* Bonus contains the main method of this project.
* It is used to test the functionality of the classes and methods,
* by implementing various examples of use cases.
* 
* @author Vlad Adriana
*/
public class Bonus{

    public static void main(String[] args) {
        Problem p= new Problem();
        if(p.existsPath()==true){
            System.out.println("You can get from " + p.locationList[0].name + " to " + p.locationList[6].name);
        }
        Solution s1 = (p.findShortestPath());
        System.out.println(s1);
        Solution s2 = (p.findFastestPath());
        System.out.println(s2);
    }

}