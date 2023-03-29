
package com.mycompany.homework;

/**
 *
 * @author avjiu
 */
public interface CatalogManager {
    public Catalog execute() throws InvalidCatalogException, InvalidPathException, InvalidDocumentException;
}
