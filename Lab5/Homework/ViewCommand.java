/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Vlad Adriana
 */
public class ViewCommand implements CatalogCommand{  
    public static void view(Document doc) throws InvalidDocumentException{
        Desktop desktop = Desktop.getDesktop();
        try{
        desktop.open(new File(doc.location));}
        catch (IOException ex){
            throw new InvalidDocumentException(ex);
        }
    }
}
