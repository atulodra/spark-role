/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkrole.role;

/**
 *
 * @author AVShrez
 */
public class Permissions {

    int id;
    String section;
    String operation;
    String description;

    Permissions() {
    }

    Permissions(int id, String section, String operation,
            String description) {
        this.id = id;
        this.section = section;
        this.operation = operation;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getSection() {
        return section;
    }

    public String getOperation() {
        return operation;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
