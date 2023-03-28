/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author avjiu
 */
public class ReportCommand implements CatalogCommand{
    public static void report(Catalog c, String path) throws InvalidCatalogException, InvalidPathException{
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
            cfg.setDirectoryForTemplateLoading(new File("C:\\Users\\avjiu\\Documents\\NetBeansProjects\\Compulsory\\src\\main\\java\\com\\mycompany\\compulsory\\templates"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            Template temp = cfg.getTemplate("report.ftl");
            Writer out = new FileWriter(new File(path));
            try {
                temp.process(c, out);
            } catch (TemplateException ex) {
                throw new InvalidCatalogException(ex);
            }
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(path));
        } catch (IOException ex) {
            throw new InvalidPathException(ex);
        }
    }
}
