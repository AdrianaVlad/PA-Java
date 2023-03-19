/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.homework;

import java.util.*;
import static java.util.stream.Collectors.*;

/**
 *
 * @author Vlad Adriana
 */

public class Problem {
    static final int INF = Integer.MAX_VALUE;
    List<Student> studentList;
    Set<Project> projectSet;
    Map<Student,Project> edges = new HashMap<>();
    Map<Student,Project> result;
    Map<Student,Integer> dist;
    public Problem(List<Student> studentList, Set<Project> projectSet) {
        this.studentList = studentList;
        this.projectSet = projectSet;
        studentList.forEach(s -> s.admissibleProjects.forEach(p -> edges.put(s,p)));
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
        studentList.forEach(s -> s.admissibleProjects.forEach(p -> edges.put(s,p)));
    }
    public Map<Student,Project> solveGreedy(){
        result = new HashMap<>();
        dist = new HashMap<>();
        while(bfsHK()){
            for(Student s : studentList){
                if(!result.containsKey(s))
                    dfsHK(s);
            }
        }
        return result;
    }
    private boolean bfsHK(){
        Queue<Student> Q = new LinkedList<>();
        for(Student s : studentList)
            if (!result.containsKey(s)){
                dist.put(s,0);
                Q.add(s);
            }
            else
                dist.put(s,INF);
        dist.put(null,INF);
        while (!Q.isEmpty()){
            Student s = Q.poll();
            if (dist.get(s) < dist.get(null))
                for(Project p : s.admissibleProjects)
                    if (dist.get(keyOfVal(p)) == INF){
                        dist.put(keyOfVal(p), dist.get(s)+1);
                        Q.add(keyOfVal(p));
                    }
        }
        return (dist.get(null) < INF);
    }
    private boolean dfsHK(Student s){
        if(s==null) return true;
        for(Project p : s.admissibleProjects)
            if (dist.get(keyOfVal(p)) ==  dist.get(s)+1)
                if (dfsHK(keyOfVal(p))){
                    result.put(s,p);
                    return true;
                }
        dist.put(s,INF);
        return false;
    }
    private Student keyOfVal(Project p){
        return result.entrySet().stream().filter(e -> Objects.equals(e.getValue(),p)).map(e -> e.getKey()).findFirst().orElse(null);
    }
}
