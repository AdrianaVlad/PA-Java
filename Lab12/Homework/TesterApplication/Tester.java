package com.mycompany.homework;

import annotation.Test;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vlad Adriana
 */
public final class Tester {
    public Tester(String[] args){
        Class clazz;
        try {
        if(args.length>1&&args[0].length()>8 && args[0].substring(0,8).equals("file:///")){
            URL[] urls = new URL[] { new URL(args[0])};
            URLClassLoader urlLoader = new URLClassLoader(urls,this.getClass().getClassLoader());
            clazz=urlLoader.loadClass(args[1]);
        } 
        else{
            if(args[0].contains("/")){  
                if(args[0].endsWith(".jar")){
                    URL[] urls = { new URL("jar:file:" + args[0]+"!/") };
                    URLClassLoader urlLoader = new URLClassLoader(urls,this.getClass().getClassLoader());
                    JarFile dir = new JarFile(args[0]);
                    searchClassesFromJar(dir,"",urlLoader);
                    clazz=null;
                }
                else{
                    URL[] urls = new URL[] { new URL("file:///"+args[0])};
                    URLClassLoader urlLoader = new URLClassLoader(urls,this.getClass().getClassLoader());
                    File dir = new File(args[0]);
                    for(File f : dir.listFiles()){
                        searchClassesFromFile(f,"",urlLoader);
                    }
                    clazz=null;
                }
            }
            else{
                clazz=Class.forName(args[0]);
            }
        }
        if(clazz!=null){
            int passed = 0, failed = 0;
            for (Method m : clazz.getDeclaredMethods()) {
                System.out.println("detected method "+m.getName()+" it's return type is "+m.getReturnType()+" and it has "+m.getParameterCount()+" parameters");
                if (m.isAnnotationPresent(Test.class) && m.getParameterCount()==0) {
                    try {
                        m.setAccessible(true);
                        m.invoke(null);
                        passed++;
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        System.out.printf("Test %s failed: %s %n",
                                m, ex.getCause());
                        failed++;
                    }
                }
            }
            System.out.println("tested class "+clazz.getSimpleName()+" from package "+clazz.getPackageName());
            System.out.printf("Passed: %d, Failed %d%n", passed, failed);
            }
        }
        catch (MalformedURLException | ClassNotFoundException ex) {
                Logger.getLogger(Homework.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    public void searchClassesFromJar(JarFile dir, String prefix, URLClassLoader urlLoader) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        Enumeration<JarEntry> e = dir.entries(); 
        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();
            if(je.isDirectory() || !je.getName().endsWith(".class"))
                continue;
            String className = je.getName().substring(0,je.getName().length()-6);
            className = className.replace('/', '.');
            Class clazz = urlLoader.loadClass(className);
            testMethods(clazz);
        }
    }
    public void searchClassesFromFile(File dir, String prefix, URLClassLoader urlLoader) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        if(dir.isDirectory()){
            String newPrefix = prefix + dir.getName() + ".";
            for(File f : dir.listFiles()){
                searchClassesFromFile(f,newPrefix,urlLoader);
            }
        }
        else if(dir.getName().endsWith(".class")){
            String className = prefix + dir.getName().substring(0,dir.getName().length()-6);
            Class clazz = urlLoader.loadClass(className);
            testMethods(clazz);
        }
    }
    public void testMethods(Class clazz) throws InstantiationException, IllegalAccessException{
        if(clazz.isAnnotationPresent(Test.class)){
            int passed = 0, failed = 0;
            var instance = clazz.newInstance();
            for (Method m : clazz.getDeclaredMethods()) {
                System.out.println("detected method "+m.getName()+" it's return type is "+m.getReturnType()+" and it has "+m.getParameterCount()+" parameters");
                if (m.isAnnotationPresent(Test.class)) {
                    try {
                        m.setAccessible(true);
                        if(m.getParameterCount()==0)
                            m.invoke(instance, (Object[]) null);
                        else{
                            Object[] params = new Object[m.getParameterCount()];
                            int len=0;
                            for(Parameter p : m.getParameters()){
                                if(p.getType() == String.class)
                                    params[len]="mockString";
                                else if(p.getType().isPrimitive())
                                    params[len]=10;
                                else
                                    params[len]=null;
                                len++;
                            }
                            m.invoke(instance, params);
                        }
                        passed++;
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        System.out.printf("Test %s failed: %s %n",
                                m, ex.getCause());
                        failed++;
                    }
                }
            }
            System.out.println("tested class "+clazz.getSimpleName()+" from package "+clazz.getPackageName());
            System.out.printf("Passed: %d, Failed %d%n", passed, failed);
        }
    }
}
