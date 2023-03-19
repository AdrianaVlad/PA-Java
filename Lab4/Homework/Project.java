/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;
/**
 *
 * @author Vlad Adriana
 */
public class Project extends Node{

    public Project(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Project{" + "name=" + name + '}';
    }
    
}