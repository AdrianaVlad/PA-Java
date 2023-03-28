/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;

/**
 *
 * @author avjiu
 */
public class ToStringCommand implements CatalogCommand{
    public static String toString(Catalog c) {
        return "Catalog{" + "entries=" + c.entries + ", name=" + c.name + '}';
    }
}
