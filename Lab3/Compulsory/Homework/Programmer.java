/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;

/**
 *
 * @author Vlad Adriana
 */
public class Programmer extends Person{
    String[] languages;

    public Programmer(String name, Date birthDate) {
        super(name, birthDate);
        specialty="programmer";
    }
    
    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }
    
}
