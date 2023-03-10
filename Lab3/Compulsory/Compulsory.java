/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.compulsory;
import java.util.*;
/**
 *
 * @author Vlad Adriana
 */
public class Compulsory {

    public static void main(String[] args) {
        List<Node> nodeList =  new LinkedList<>();
        nodeList.add(new Person("Adriana"));
        nodeList.add(new Person("Elena"));
        nodeList.add(new Person("Vlad"));    
        nodeList.add(new Person("ingrid"));
        nodeList.add(new Company("Alphabet"));
        nodeList.add(new Company("Meta"));
        nodeList.add(new Company("Bitdefender"));
        System.out.println(nodeList);
    }
}