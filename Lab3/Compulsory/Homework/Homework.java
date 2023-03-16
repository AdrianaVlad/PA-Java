/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.homework;
import java.util.*;
/**
 *
 * @author Vlad Adriana
 */
public class Homework {

    public static void main(String[] args) {
        List<Node> nodeList =  new ArrayList<>();
        Person p1 = new Programmer("Adriana", new Date(30,5,2002));
        nodeList.add(p1);
        Person p2 = new Designer("Elena", new Date(20,5,2003));
        nodeList.add(p2);
        Person p3 = new Programmer("Vlad",  new Date(15,3,2001));
        nodeList.add(p3);
        Person p4 = new Designer("Ingrid", new Date(1,5,2002));
        nodeList.add(p4);
        Company c1 = new Company("Alphabet");
        nodeList.add(c1);
        Company c2 = new Company("Meta");
        nodeList.add(c2);
        Company c3 = new Company("Bitdefender");
        nodeList.add(c3);
        Network network = new Network(nodeList);
        p1.addRelationship(p2, "best friend");
        p2.addRelationship(p1, "best friend");
        p1.addRelationship(p4, "from high school");
        p4.addRelationship(p1, "from high school");
        p1.addRelationship(c1, "nu mai");
        p3.addRelationship(c3, "am idei");
        p2.addRelationship(c3, "hihi");
        p4.addRelationship(c2, "huhu");
        System.out.println(network);
    }
}