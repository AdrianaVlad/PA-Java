/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.homework;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vlad Adriana
 */
public class Homework {

    public static void main(String[] args){
        Catalog catalog = new Catalog("Lab5");
        Document book = new Document("Carte", "C:\\Users\\avjiu\\Documents\\speedrun.txt", 1);
        Document article  = new Document("Articol", "C:\\Users\\avjiu\\Documents\\ac9cd9.png", 2);
        new AddCommand(catalog,book).execute();
        new AddCommand(catalog, article).execute();
        try {
            new SaveCommand(catalog, "D:\\uaic\\catalog.json").execute();
        } catch (InvalidPathException ex) {
            Logger.getLogger(Homework.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                Catalog catalog2 = new LoadCommand("D:\\uaic\\catalog.json").execute();
                new ViewCommand(catalog2.findById(1)).execute();
                new ListCommand(catalog2).execute();
                new ReportCommand(catalog2, "D:\\uaic\\catalog.html").execute();
            } catch (InvalidCatalogException | InvalidDocumentException | InvalidPathException ex) {
                Logger.getLogger(Homework.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}