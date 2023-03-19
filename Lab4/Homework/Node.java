/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;

/**
 *
 * @author avjiu
 */
public abstract class Node implements Comparable{
    String name;
    @Override
    public int compareTo(Object obj) {
       if(obj==null) throw new NullPointerException();
       if(!(obj instanceof Student)&& !(obj instanceof Project))
           throw new ClassCastException ("Uncomparable objects!");
       return (this.name.compareTo(((Node)obj).name));
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
