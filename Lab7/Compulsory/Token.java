/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compulsory;

/**
 *
 * @author Vlad Adriana
 */
public class Token {
    private final int number;
    
    public Token(int number) {
        this.number = number; 
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Token{" + "number=" + number + '}';
    }
    
}
