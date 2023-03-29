/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonus;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author avjiu
 */
public class SaveCommand implements CatalogManager{
    Catalog c;
    String path;
    public SaveCommand(Catalog c, String path){
        this.c=c;
        this.path=path;
    }
    @Override
    public Catalog execute() throws InvalidPathException{
        ObjectMapper objectMapper = new ObjectMapper();
        try{
        objectMapper.writeValue(new File(path), c);}
        catch(IOException ex){
            throw new InvalidPathException(ex);
        }
        return c;
    }
}
