/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;
/**
 *
 * @author Vlad Adriana
 */
public class Company implements Node, Comparable{
    String name;
    String location;
    @Override
    public String getName(){
        return name;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    
    public Company(String name) {
        this.name = name;
    }
    @Override
    public int compareTo(Object c) {
       if(c==null) throw new NullPointerException();
       if(!(c instanceof Company))
           throw new ClassCastException ("Uncomparable objects!");
       Company comp = (Company) c;
       return (this.name.compareTo(comp.name));
    }

    @Override
    public String toString() {
        return "Company{" + "name=" + name + ", location=" + location + '}';
    }
 
}
