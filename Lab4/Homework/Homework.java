/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.homework;
import com.github.javafaker.Faker;
import java.util.*;
/**
 *
 * @author Vlad Adriana
 */
public class Homework {

    public static void main(String[] args) {
        Problem problem = new Problem();
        double averagePreferenceNr = problem.studentList.stream().mapToInt(s -> s.admissibleProjects.size()).average().getAsDouble();
        problem.studentList.stream().filter(s -> s.admissibleProjects.size() < averagePreferenceNr).forEach(System.out::println);
        generateFakeNames();
        System.out.println(problem.solveGreedy());
    }
    public static void generateFakeNames(){
        Faker faker = new Faker();
        List<String> fakeStudNames = Arrays.asList(faker.name().fullName(),faker.name().fullName(),faker.name().fullName());
        List<String> fakeProjNames = Arrays.asList(faker.app().name(),faker.app().name(),faker.app().name());
        fakeStudNames.forEach(System.out::println);
        fakeProjNames.forEach(System.out::println);
    }
}