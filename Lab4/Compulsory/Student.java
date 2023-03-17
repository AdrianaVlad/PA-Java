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
public class Student implements Comparable{
    String name;
    List<Project> admissibleProjects = new ArrayList<>();
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

    public Student(String name) {
        this.name = name;
    }

    public List<Project> getAdmissibleProjects() {
        return admissibleProjects;
    }

    public void setAdmissibleProjects(List<Project> admissibleProjects) {
        this.admissibleProjects = admissibleProjects;
    }
    public void addAdmissibleProjects(Project p ){
        admissibleProjects.add(p);
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", admissibleProjects=" + admissibleProjects + '}';
    }
    
}
