/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compulsory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vlad Adriana
 */
public class Catalog {
    List<Document> entries = new ArrayList<>();;
    String name;
    public Catalog() {
    }
    public Catalog(String name) {
        this.name=name;
    }
    
    public Document findById(int id) {
        return entries.stream().filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Document> getEntries() {
        return entries;
    }

    public void setEntries(List<Document> entries) {
        this.entries = entries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
