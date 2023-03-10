/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compulsory;

/**
 *
 * @author Vlad Adriana
 */
public class Relationship {
    String type;
    Person withPerson;
    Company withCompany;

    public Relationship(String type, Person withPerson) {
        this.type = type;
        this.withPerson = withPerson;
    }

    public Relationship(String type, Company withCompany) {
        this.type = type;
        this.withCompany = withCompany;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Person getWithPerson() {
        return withPerson;
    }

    public void setWithPerson(Person withPerson) {
        this.withPerson = withPerson;
    }

    public Company getWithCompany() {
        return withCompany;
    }

    public void setWithCompany(Company withCompany) {
        this.withCompany = withCompany;
    }
    
    
}
