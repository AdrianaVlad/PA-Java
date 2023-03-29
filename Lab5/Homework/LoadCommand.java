/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author avjiu
 */
public class LoadCommand implements CatalogManager{
    String path;
    public LoadCommand(String path){
        this.path=path;
    }
    @Override
    public Catalog execute() throws InvalidCatalogException{
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(path), Catalog.class);
        } catch (IOException ex) {
            throw new InvalidCatalogException(ex);
        }
    }
}
