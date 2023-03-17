/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compulsory;
/**
 *
 * @author Vlad Adriana
 */
public class Project implements Comparable {
    String name;
    @Override
    public int compareTo(Object obj) {
       if(obj==null) throw new NullPointerException();
       if(!(obj instanceof Student)&& !(obj instanceof Project))
           throw new ClassCastException ("Uncomparable objects!");
       if(obj instanceof Student s){
           return (this.name.compareTo(s.name));
       }
       else{
           Project p = (Project) obj;
           return (this.name.compareTo(p.name));
       }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Project{" + "name=" + name + '}';
    }
    
}
