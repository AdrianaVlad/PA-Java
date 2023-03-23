/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compulsory;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vlad Adriana
 */
public class Document {
    String name, location;
    Integer id;
    Map<String,Object> tags = new HashMap<>();

    public Document(String name, String location, int id) {
        this.name = name;
        this.location = location;
        this.id = id;
    }

    public Document() {
    }
    
    public void addTag(String key, Object obj) {
        tags.put(key, obj);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }

    
    
    @Override
    public String toString() {
        return "Document{" + "name=" + name + ", location=" + location + ", id=" + id + ", tags=" + tags + '}';
    }

}
