/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

/**
 *
 * @author avjiu
 */
public class TagsCommand implements CatalogCommand{
    public static void addMetaTags(Catalog c) throws FileNotFoundException, IOException, SAXException, TikaException{
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();
        FileInputStream inputstream;
        File file;
        for(Document d: c.entries){
            file = new File(d.location);
            inputstream = new FileInputStream(file);
            parser.parse(inputstream, handler, metadata, context);
            System.out.println(handler.toString());
            String[] metadataNames = metadata.names();
            for(String name : metadataNames) {		        
               d.tags.put(name, metadata.get(name));
            }
        }
    }
}
