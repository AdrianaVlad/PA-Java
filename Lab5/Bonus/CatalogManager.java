
package com.mycompany.bonus;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.apache.tika.exception.TikaException;

/**
 *
 * @author avjiu
 */
public interface CatalogManager {
    public Catalog execute() throws InvalidCatalogException, InvalidPathException, InvalidDocumentException,FileNotFoundException, IOException, SAXException, TikaException;
}
