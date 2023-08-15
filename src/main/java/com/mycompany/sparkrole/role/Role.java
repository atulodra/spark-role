/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkrole.role;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AVShrez
 */
public class Role {

    private int id;
    private String name;
    private List<Permissions> permissions = new ArrayList<>();
//    private List<Integer> permIds = new ArrayList<>();

    Role() {

    }

    Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    Role(String name) {
        this.name = name;
    }

    Role(int id, String name, Permissions permission) {
        this.id = id;
        this.name = name;
        this.permissions.add(permission);
    }

    Role(String name, Permissions permission) {
        this.name = name;
        this.permissions.add(permission);
    }

    Role(String name, List<Permissions> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    Role(int id, String name, List<Permissions> permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPermissions(List<Permissions> permissions) {
        this.permissions = permissions;
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }

//    public List<Integer> getPermissionIds() {
//        for (Permissions permission : permissions) {
//            permIds.add(permission.getId());
//        }
//        return permIds;
//    }
    public void addToPermissions(Permissions permission) {
        this.permissions.add(permission);
    }
}
