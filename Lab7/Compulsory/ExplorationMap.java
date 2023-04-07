/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compulsory;

import java.util.List;

/**
 *
 * @author Vlad Adriana
 */
public class ExplorationMap {

    private final Cell[][] matrix;
    //Cell should be a wrapper or alias for List<Token>

    public ExplorationMap(int n) {
        matrix = new Cell[n][n];
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                matrix[i][j]=new Cell();
    }
    
    public boolean visit( int row, int col, Robot robot) {
        synchronized (matrix[row][col]) {
            if (matrix[row][col].getNrList().isEmpty()) {
                List<Token> extracted = robot.explore.getMem().extractTokens(robot.explore.size);
                matrix[row][col].setNrList(extracted);
                System.out.println("Cell on row " + row + " and col " + col + " visited successfully!");
                return true;
            }
        return false;
        }   
    }
    public boolean isEmpty(int row, int col){
        return matrix[row][col].getNrList().isEmpty();
    }
    @Override
    public String toString() {
        return "ExplorationMap{" + "matrix=" + matrix + '}';
    }
    
}
