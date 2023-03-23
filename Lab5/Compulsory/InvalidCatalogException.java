/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compulsory;

/**
 *
 * @author avjiu
 */
class InvalidCatalogException extends Exception {
    public InvalidCatalogException(Exception ex) {
        super("Invalid catalog file.", ex);
    }
}
