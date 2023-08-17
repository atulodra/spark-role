/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkrole.role;

import com.google.gson.Gson;
import java.sql.SQLException;
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

    private final Dbdao db = new Dbdao();

    public String getRoles(Request request, Response response) {
        response.type("application/json");
        List<Role> roles = db.getRoles();
        return new Gson().toJson(roles);
    }

    public String getRole(Request request, Response response) throws NoSuchElementException {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));
        Optional<Role> optRole = db.getRole(id);
        Role role = optRole.orElseThrow();
        return new Gson().toJson(role);
    }

    public String insertRole(Request request, Response response) {
        response.type("application/json");
        Role role = new Gson().fromJson(request.body(), Role.class);
        try {
            db.insertRole(role);
        } catch (SQLException e) {
            response.status(500);
            return "{ ERROR, Role Not Inserted! }";
        }
        response.status(200);
        return "{ SUCCESS, Role Inserted! }";
    }

    public String deleteRole(Request request, Response response) {
        response.type("application/json");
        int id = Integer.parseInt(request.params(":id"));
        try {
            db.deleteRole(id);
        } catch (SQLException e) {
            response.status(500);
            return "{ ERROR, Role Could Not Be Deleted }";
        }
        return "{ SUCCESS, Role Deleted }";
    }

}
