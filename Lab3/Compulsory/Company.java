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
public class Company implements Node, Comparable{
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
    
    public Company(String name) {
        this.name = name;
    }
    public void addRelationship(Relationship r){
        relList.add(r);
    }
    public void removeRelationship(Relationship r){
        relList.remove(r);
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
        return "Company{" + "name=" + name + ", relList=" + relList + ", specialty=" + specialty + '}';
    }
    
}
