/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkrole.role;

import com.google.gson.Gson;
import com.mycompany.sparkrole.operator.Operator;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import spark.Request;
import spark.Response;

/**
 *
 * @author AVShrez
 */
public class RoleController {

    private final RoleDbdao db = new RoleDbdao();

    public String getRoles(Request request, Response response) {
        int permissionId = 1001;
        response.type("application/json");
        Operator loggedInOperator = request.session()
                .attribute("loggedInOperator");
        if (!loggedInOperator.getRole()
                .permissionChecker(permissionId)) {
            return new Gson().toJson("You don't have the permission!");
        }
        List<Role> roles = db.getRoles();
        return new Gson().toJson(roles);
    }

    public String getRole(Request request, Response response) throws NoSuchElementException {
        int permissionId = 1001;
        response.type("application/json");
        Operator loggedInOperator = request.session()
                .attribute("loggedInOperator");
        if (!loggedInOperator.getRole()
                .permissionChecker(permissionId)) {
            return new Gson().toJson("You don't have the permission!");
        }
        int id = Integer.parseInt(request.params(":id"));
        Optional<Role> optRole = db.getRole(id);
        Role role = optRole.orElseThrow();
        return new Gson().toJson(role);
    }

    public String insertRole(Request request, Response response) {
        int permissionId = 1002;
        response.type("application/json");
        Operator loggedInOperator = request.session()
                .attribute("loggedInOperator");
        if (!loggedInOperator.getRole()
                .permissionChecker(permissionId)) {
            return new Gson().toJson("You don't have the permission!");
        }
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
        int permissionId = 1003;
        response.type("application/json");
        Operator loggedInOperator = request.session()
                .attribute("loggedInOperator");
        if (!loggedInOperator.getRole()
                .permissionChecker(permissionId)) {
            return new Gson().toJson("You don't have the permission!");
        }
        int id = Integer.parseInt(request.params(":id"));
        try {
            db.deleteRole(id);
        } catch (SQLException e) {
            response.status(500);
            return "{ ERROR, Role Could Not Be Deleted }";
        }
        return "{ SUCCESS, Role Deleted }";
    }

    public String editRole(Request request, Response response) {
        int permissionId = 1004;
        response.type("application/json");
        Operator loggedInOperator = request.session()
                .attribute("loggedInOperator");
        if (!loggedInOperator.getRole()
                .permissionChecker(permissionId)) {
            return new Gson().toJson("You don't have the permission!");
        }
        int id = Integer.parseInt(request.params(":id"));
        Role role = new Gson().fromJson(request.body(), Role.class);
        try {
            db.editRole(id, role);
        } catch (SQLException e) {
            response.status(500);
            return "{ ERROR, Role Could Not Be Deleted }";
        }
        return "{ SUCCESS, Role Deleted }";
    }

}
