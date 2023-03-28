/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonus;

/**
 *
 * @author avjiu
 */
public class AddCommand implements CatalogCommand{
    public static void add(Catalog c, Document entry){
        c.entries.add(entry);
    }
}
