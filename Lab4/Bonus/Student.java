/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonus;
import java.util.*;
/**
 *
 * @author Vlad Adriana
 */
public class Student extends Node{
    List<Project> admissibleProjects = new ArrayList<>();
    
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