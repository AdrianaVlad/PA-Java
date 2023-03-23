/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.compulsory;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vlad Adriana
 */
public class Compulsory {

    public static void main(String[] args) throws IOException {
        Catalog catalog = new Catalog("Lab5");
        Document book = new Document("Carte", "C:\\Users\\avjiu\\Documents\\speedrun.txt", 1);
        Document article  = new Document("Articol", "C:\\Users\\avjiu\\Documents\\ac9cd9.png", 2);
        catalog.add(book);
        catalog.add(article);
        try {
            CatalogUtil.save(catalog, "D:\\uaic\\catalog.json");
        } catch (IOException ex) {
            Logger.getLogger(Compulsory.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                Catalog catalog2 = CatalogUtil.load("D:\\uaic\\catalog.json");
                CatalogUtil.view(catalog2.findById(1));
            } catch (InvalidCatalogException ex) {
                Logger.getLogger(Compulsory.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (IOException ex) {
                Logger.getLogger(Compulsory.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (IllegalArgumentException ex){
                Logger.getLogger(Compulsory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}