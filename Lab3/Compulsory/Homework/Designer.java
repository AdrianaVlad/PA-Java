/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;

/**
 *
 * @author Vlad Adriana
 */
public class Designer extends Person{
    String style;

    public Designer(String name, Date birthDate) {
        super(name, birthDate);
        specialty="programmer";
    }
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
    
    
}
