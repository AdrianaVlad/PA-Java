/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;

/**
 *
 * @author avjiu
 */
public class AddCommand implements CatalogManager{
    Catalog c;
    Document entry;
    public AddCommand(Catalog c, Document entry){
        this.c=c;
        this.entry=entry;
    }
    @Override
    public Catalog execute(){
        c.entries.add(entry);
        return c;
    }
}
