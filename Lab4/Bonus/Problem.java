/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonus;

import java.util.*;
import static java.util.stream.Collectors.*;

/**
 *
 * @author Vlad Adriana
 */

public class Problem {
    List<Student> studentList;
    Set<Project> projectSet;
    
    public Problem(List<Student> studentList, Set<Project> projectSet) {
        this.studentList = studentList;
        this.projectSet = projectSet;
    }

    public Problem() {
        List<String> studNames = Arrays.asList("S1","S2","S0");
        List<String> projNames = Arrays.asList("P0","P1","P2");
        studentList = studNames.stream().map(name -> new Student(name)).sorted().collect(toCollection(LinkedList::new));
        projectSet = projNames.stream().map(name -> new Project(name)).sorted().collect(toCollection(TreeSet::new));
        Project projArray[] = new Project[3];
        projectSet.toArray(projArray);
        for(int i=0;i<3;i++){
            studentList.get(0).addAdmissibleProjects(projArray[i]);
        }
        studentList.get(1).addAdmissibleProjects(projArray[0]);
        studentList.get(1).addAdmissibleProjects(projArray[1]);
        studentList.get(2).addAdmissibleProjects(projArray[0]);
    }
    public Map<Student,Project> solveGreedy(){
        Map<Student,Project> result = new HashMap<>();
        Map<Project,Boolean> used = new HashMap<>();
        for(Student s : studentList){
            for(Project p : s.admissibleProjects)
                if(projectSet.contains(p) && used.get(p)==null){
                    result.put(s, p);
                    used.put(p,true);
                    break;
                }
        }
        return result;
    }
}
