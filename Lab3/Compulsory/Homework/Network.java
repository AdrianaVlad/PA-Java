/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;
import java.util.*;
/**
 *
 * @author Vlad Adriana
 */
public class Network {
    List<Node> nodeList = new ArrayList<>();

    public Network() {
        nodeList.add(new Programmer("Adriana", new Date(30,5,2002)));
        nodeList.add(new Designer("Elena", new Date(20,5,2003)));
        nodeList.add(new Programmer("Vlad",  new Date(15,3,2001)));    
        nodeList.add(new Designer("Ingrid", new Date(1,5,2002)));
        nodeList.add(new Company("Alphabet"));
        nodeList.add(new Company("Meta"));
        nodeList.add(new Company("Bitdefender"));
    }
    public Network(List<Node> nodeList){
        this.nodeList=nodeList;
    }
    public void addNode(Node node){
        nodeList.add(node);
    }
    public void removeNode(Node node){
        nodeList.remove(node);
    }
    public Integer Importance(Node node){
        if(!nodeList.contains(node)) return 0; 
        int nrConnections=0;
        if(node instanceof Person person){  
             for(Node rel : person.relMap.keySet())
                 if(nodeList.contains(rel))
                     nrConnections++;
        }
        else{
            for(Node i : nodeList){
                if(i instanceof Person person){
                    if(person.relMap.containsKey(node))
                        nrConnections++;
                }
            }
        }
        return nrConnections;
    }

    @Override
    public String toString() {
        //sort nodeList on Importance
        nodeList.sort((Node n1, Node n2) -> {return Importance(n1).compareTo(Importance(n2));});
        return "Network{" + "nodeList=" + nodeList + '}';
    }
    
}
