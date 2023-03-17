/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.compulsory;
import java.util.*;
import static java.util.stream.Collectors.*;
/**
 *
 * @author Vlad Adriana
 */
public class Compulsory {

    public static void main(String[] args) {
        List<String> studNames = Arrays.asList("S1","S2","S0");
        List<String> projNames = Arrays.asList("P0","P1","P2");
        List<Student> students = studNames.stream().map(name -> new Student(name)).sorted().collect(toCollection(LinkedList::new));
        Set<Project> projects = projNames.stream().map(name -> new Project(name)).sorted().collect(toCollection(TreeSet::new));
        Project projArray[] = new Project[3];
        projects.toArray(projArray);
        for(int i=0;i<3;i++){
            students.get(0).addAdmissibleProjects(projArray[i]);
        }
        students.get(1).addAdmissibleProjects(projArray[0]);
        students.get(1).addAdmissibleProjects(projArray[1]);
        students.get(2).addAdmissibleProjects(projArray[0]);
        System.out.println(students);
        System.out.println(projects);
    }
}