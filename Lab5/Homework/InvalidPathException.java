/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;

import java.io.IOException;

/**
 *
 * @author avjiu
 */
public class InvalidPathException extends Exception {

    public InvalidPathException(Exception ex) {
        super("Invalid specified path.", ex);
    }
}
