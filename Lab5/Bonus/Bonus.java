/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bonus;

import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tika.exception.TikaException;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.alg.coloring.GreedyColoring;
import org.xml.sax.SAXException;

/**
 *
 * @author Vlad Adriana
 */
public class Bonus {

    public static void main(String[] args){
        Catalog catalog = new Catalog("Lab5");
        Document book = new Document("Carte", "C:\\Users\\avjiu\\Documents\\speedrun.txt", 1);
        Document article  = new Document("Articol", "C:\\Users\\avjiu\\Documents\\ac9cd9.png", 2);
        Document book2 = new Document("Carte", "C:\\Users\\avjiu\\Documents\\speedrun.txt", 3);
        new AddCommand(catalog,book).execute();
        new AddCommand(catalog, article).execute();
        new AddCommand(catalog,book2).execute();
        try {
            new SaveCommand(catalog, "D:\\uaic\\catalog.json").execute();
        } catch (InvalidPathException ex) {
            Logger.getLogger(Bonus.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Catalog catalog2 = new LoadCommand("D:\\uaic\\catalog.json").execute();
            new ViewCommand(catalog2.findById(1)).execute();
            new ListCommand(catalog2).execute();
            new ReportCommand(catalog2, "D:\\uaic\\catalog.html").execute();
            new InfoCommand(catalog2).execute();
            new TagsCommand(catalog).execute();
        } catch (InvalidCatalogException | IOException | IllegalArgumentException | SAXException | TikaException | InvalidDocumentException | InvalidPathException ex) {
            Logger.getLogger(Bonus.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            Graph g = GraphBuilder.numVertices(catalog.entries.size()).buildGraph();
            for(int i=0;i<catalog.entries.size();i++)
                for(int j=i+1;j<catalog.entries.size();j++)
                    for(String key : catalog.entries.get(i).tags.keySet())
                        if(catalog.entries.get(i).tags.get(key).equals(catalog.entries.get(j).tags.get(key))){
                            g.addEdge(i,j);
                            break;
                        }
            System.out.println(g);
            GreedyColoring color = new GreedyColoring(g);
            System.out.println(color.findColoring());
        }
    }
}