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
public class LoadCommand implements CatalogCommand{
    public static Catalog load(String path) throws InvalidCatalogException{
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(path), Catalog.class);
        } catch (IOException ex) {
            throw new InvalidCatalogException(ex);
        }
    }
}
