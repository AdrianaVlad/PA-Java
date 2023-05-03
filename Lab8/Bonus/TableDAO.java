/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bonus;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Vlad Adriana
 * @param <T>
 */
public abstract class TableDAO <T extends TableEntity> {
    public abstract void create(T line) throws SQLException;
    public abstract List<T> findAll() throws SQLException;
    public abstract T findByName(String name) throws SQLException;
    public abstract T findById(int id) throws SQLException;
    public abstract int findNextId() throws SQLException;
}
