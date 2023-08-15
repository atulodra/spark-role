/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkrole.role;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import spark.Request;
import spark.Response;

/**
 *
 * @author AVShrez
 */
public class RoleController {

    private Dbdao db = new Dbdao();
//    private Role role;

    public String getRoles(Request request, Response response) {
        response.type("application/json");
//        int id = Integer.parseInt(request.params(":id"));
        List<Role> roles = db.getRoles();
//        Map<Role, List<Permissions>> rolePermissions = db.getBook(id);
        return new Gson().toJson(roles);
    }

    public String getRole(Request request, Response response) throws NoSuchElementException {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));
        Optional<Role> optRole = db.getRole(id);
        Role role = optRole.orElseThrow();
//        Map<Role, List<Permissions>> rolePermissions = db.getBook(id);
        return new Gson().toJson(role);
    }

}
