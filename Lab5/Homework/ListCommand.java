/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;

/**
 *
 * @author avjiu
 */
public class ListCommand implements CatalogManager{
    Catalog c;
    public ListCommand(Catalog c){
        this.c=c;
    }
    @Override
    public Catalog execute(){
        System.out.println(c.entries);
        return c;
    }
}
