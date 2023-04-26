/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Vlad Adriana
 */
public class SharedMemory {

    private final List<Token> nrList= new ArrayList<>();

    public SharedMemory(int n) {
        for(int i=1;i<n*n*n;i++)
            nrList.add(new Token(i));
        Collections.shuffle(nrList);
    }

    public synchronized List<Token> extractTokens(int howMany) {
        List<Token> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            if (nrList.isEmpty()) {
                break;
            }
            extracted.add(nrList.get(0));
            nrList.remove(0);
        }
        return extracted;
    }

}
