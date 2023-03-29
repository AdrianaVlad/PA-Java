/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonus;

/**
 *
 * @author avjiu
 */
public class ToStringCommand implements CatalogManager{
    Catalog c;
    String representation;
    public ToStringCommand(Catalog c){
        this.c=c;
    }
    @Override
    public Catalog execute() {
        representation="Catalog{" + "entries=" + c.entries + ", name=" + c.name + '}';
        return c;
    }
    public String get(){
        return representation;
    }
}
