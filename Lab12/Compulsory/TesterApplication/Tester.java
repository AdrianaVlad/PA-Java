/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compulsory;

import annotation.Test;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author avjiu
 */
public class Tester {
    public Tester(String[] args){
        Class clazz;
        try {
        if(args[0].length()>8 && args[0].substring(0,8).equals("file:///")){
            URL[] urls = new URL[] { new URL(args[0])};
            URLClassLoader urlLoader = new URLClassLoader(urls,this.getClass().getClassLoader());
            clazz=urlLoader.loadClass(args[1]);
        } 
        else{
            clazz=Class.forName(args[0]);
        }
        int passed = 0, failed = 0;
        for (Method m : clazz.getMethods()) {
            System.out.println("detected method "+m.getName()+" it's return type is "+m.getReturnType()+" and it has "+m.getParameterCount()+" parameters");
            if (m.isAnnotationPresent(Test.class)) {
                try {
                    m.invoke(null);
                    passed++;
                } catch (Throwable ex) {
                    System.out.printf("Test %s failed: %s %n",
                            m, ex.getCause());
                    failed++;
                }
            }
        }
        System.out.println("tested class "+clazz.getSimpleName()+" from package "+clazz.getPackageName());
        System.out.printf("Passed: %d, Failed %d%n", passed, failed);
        }
        catch (MalformedURLException | ClassNotFoundException ex) {
                Logger.getLogger(Compulsory.class.getName()).log(Level.SEVERE, null, ex);
            }     
    }
}
