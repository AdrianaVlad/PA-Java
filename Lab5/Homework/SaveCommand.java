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
public class SaveCommand implements CatalogCommand{
    public static void save(Catalog catalog, String path) throws InvalidPathException{
        ObjectMapper objectMapper = new ObjectMapper();
        try{
        objectMapper.writeValue(new File(path), catalog);}
        catch(IOException ex){
            throw new InvalidPathException(ex);
        }
    }
}