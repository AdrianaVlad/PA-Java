/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bonus;
import com.github.javafaker.Faker;
import java.util.*;
import org.graph4j.*;
import org.graph4j.alg.matching.*;
/**
 *
 * @author Vlad Adriana
 */
public class Bonus {

    public static void main(String[] args) {
        Problem problem = new Problem();
        double averagePreferenceNr = problem.studentList.stream().mapToInt(s -> s.admissibleProjects.size()).average().getAsDouble();
        problem.studentList.stream().filter(s -> s.admissibleProjects.size() < averagePreferenceNr).forEach(System.out::println);
        generateFakeNames();
        System.out.println(problem.solveGreedy());
        Graph g = GraphBuilder.numVertices(6).addEdge(0,3).addEdge(0,4).addEdge(0,5).addEdge(1,3).addEdge(1,4).addEdge(2,3).buildGraph();
        HopcroftKarpMaximumMatching​ hk = new HopcroftKarpMaximumMatching​(g);
        System.out.println(hk.getMatching());
        System.out.println(hk.getMaximumStableSet());
        System.out.println(hk.getMinimumVertexCover());
    }
    public static void generateFakeNames(){
        Faker faker = new Faker();
        List<String> fakeStudNames = Arrays.asList(faker.name().fullName(),faker.name().fullName(),faker.name().fullName());
        List<String> fakeProjNames = Arrays.asList(faker.app().name(),faker.app().name(),faker.app().name());
        fakeStudNames.forEach(System.out::println);
        fakeProjNames.forEach(System.out::println);
    }
}