/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compulsory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author avjiu
 */
public class Cell {
    private List<Token> nrList;
    public Cell(){
        this.nrList = new ArrayList<>();
    }
    
    public List<Token> getNrList() {
        return nrList;
    }
    
    public void setNrList(List<Token> nrList) {
        this.nrList=nrList;
    }
    
    @Override
    public String toString() {
        return "Cell{" + "nrList=" + nrList + '}';
    }
    
}
