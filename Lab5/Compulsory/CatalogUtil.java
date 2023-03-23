/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compulsory;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Vlad Adriana
 */
public class CatalogUtil {
    public static void save(Catalog catalog, String path) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(path), catalog);
    }
    public static Catalog load(String path) throws InvalidCatalogException{
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(path), Catalog.class);
        } catch (IOException ex) {
            throw new InvalidCatalogException(ex);
        }
    }
    public static void view(Document doc) throws IOException,IllegalArgumentException{
        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File(doc.location));
    }
}
