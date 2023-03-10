/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compulsory;
import java.util.*;
/**
 *
 * @author Vlad Adriana
 */
public class Person implements Node, Comparable{
    String name;
    List<Relationship> relList;
    String specialty;
    @Override
    public String getName(){
        return name;
    }

    public List<Relationship> getRelList() {
        return relList;
    }

    public void setRelList(List<Relationship> relList) {
        this.relList = relList;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
    
    public Person(String name) {
        this.name = name;
    }
    public void addRelationship(Relationship r){
        relList.add(r);
    }
    public void removeRelationship(Relationship r){
        relList.remove(r);
    }

    @Override
    public int compareTo(Object p) {
       if(p==null) throw new NullPointerException();
       if(!(p instanceof Person))
           throw new ClassCastException ("Uncomparable objects!");
       Person pers = (Person) p;
       return (this.name.compareTo(pers.name));
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", relList=" + relList + ", specialty=" + specialty + '}';
    }
    
}
