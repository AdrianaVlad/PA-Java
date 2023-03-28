/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonus;
/**
 *
 * @author avjiu
 */
public class ListCommand implements CatalogCommand{
    public static void list(Catalog c){
        System.out.println(c.entries);
    }
}
