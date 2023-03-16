/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;
import java.util.*;
/**
 *
 * @author Vlad Adriana
 */
public abstract class Person implements Node, Comparable{
    String name;
    Map<Node,String> relMap = new HashMap<>();
    String specialty;
    Date birthDate;
    @Override
    public String getName(){
        return name;
    }

    public com.mycompany.homework.Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(com.mycompany.homework.Date birthDate) {
        this.birthDate = birthDate;
    }

    public Map<Node, String> getRelMap() {
        return relMap;
    }

    public void setRelMap(Map<Node, String> relMap) {
        this.relMap = relMap;
    }
    
    public String getSpecialty() {
        return specialty;
    }

    public Person(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
 
    public void addRelationship(Node node, String reason){
        relMap.put(node,reason);
    }
    public void removeRelationship(Node node, String reason){
        relMap.remove(node,reason);
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
        return "Person{" + "name=" + name + ", specialty=" + specialty + ", birthDate=" + birthDate + '}';
    }

    
    
}
