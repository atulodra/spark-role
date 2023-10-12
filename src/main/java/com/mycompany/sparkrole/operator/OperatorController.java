/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkrole.operator;

import com.google.gson.Gson;
import com.mycompany.sparkrole.permissions.Permissions;
import com.mycompany.sparkrole.utils.validators.EmailValidator;
import com.mycompany.sparkrole.utils.validators.OperatorValidator;
import com.mycompany.sparkrole.utils.validators.PasswordValidator;
import java.util.List;
//import java.util.NoSuchElementException;
import java.util.Optional;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

/**
 *
 * @author AVShrez
 */
public class OperatorController {

    private final OperatorDbdao opDb = new OperatorDbdao();

    public String login(Request request, Response response) {
        response.type("application/json");
        JSONObject loginCredentials = new JSONObject(request.body());
        if (loginCredentials.keySet()
                .size() != 2 || (!loginCredentials.has("email") && !loginCredentials
                                 .has(
                                 "password"))) {
            response.status(400);
            return new Gson().toJson("Invalid request format");
        }
        String email = loginCredentials.get("email")
                .toString();
        String password = loginCredentials.get("password")
                .toString();
        if (email.equals("")) {
            response.status(422);
            return new Gson().toJson("Empty email field");
        }
        if (!EmailValidator.isEmailValid(email)) {
            response.status(422);
            return new Gson().toJson("Invalid email format");
        }
        if (password.equals("")) {
            response.status(422);
            return new Gson().toJson("Empty password field");
        }
        int loginStatus = opDb.login(email, password);
        switch (loginStatus) {
            case -1:
                return new Gson().toJson(
                        "You are disabled from logging in! Please contact an admin");
            case 0:
                return new Gson().toJson(
                        "Your email or password is incorrect");
            default:
                Optional<Operator> optOperator = opDb.getOperator(email);
                Operator loggedInOperator = optOperator.orElseThrow();
                request.session(true);
                request.session()
                        .attribute("loggedInOperator", loggedInOperator);
                return new Gson().toJson(loggedInOperator);
        }

    }
//    public String login(Request request, Response response) {
//        response.type("application/json");
//        Operator op = new Gson().fromJson(request.body(), Operator.class);
//        List<String> violationMessages = OperatorValidator.validate(op);
//        if (violationMessages.contains("Ok")) {
//            String email = op.getEmail();
//            String password = op.getPassword();
//            int loginStatus = opDb.login(email, password);
//            switch (loginStatus) {
//                case -1:
//                    return new Gson().toJson(
//                            "You are disabled from logging in! Please contact an admin");
//                case 0:
//                    return new Gson().toJson(
//                            "Your email or password is incorrect");
//                default:
//                    Optional<Operator> optOperator = opDb
//                            .getOperator(email);
//                    Operator loggedInOperator = optOperator.orElseThrow();
//                    request.session(true);
//                    request.session()
//                            .attribute("loggedInOperator", loggedInOperator);
//                    return new Gson().toJson(loggedInOperator);
//            }
//        }
//        return new Gson().toJson(violationMessages);
//    }
    //    public String getOperator(Request request, Response response) {
    //        int permissionId = 2001;
    //        response.type("application/json");
    //        Operator loggedInOperator = request.session()
    //                .attribute("loggedInOperator");
    //        if (!loggedInOperator.getRole()
    //                .permissionChecker(permissionId)) {
    //            return new Gson().toJson("You don't have the permission!");
    //        }
    //        int id = Integer.parseInt(request.params(":id"));
    //        Optional<Operator> optOperator = opDb.getOperator(id);
    //        Operator operator = optOperator.orElseThrow();
    //        return new Gson().toJson(operator);
    //    }

    public String getOperators(Request request, Response response) {
        int permissionId = 2001;
        response.type("application/json");
        Operator loggedInOperator = request.session()
                .attribute("loggedInOperator");
        if (!loggedInOperator.getRole()
                .permissionChecker(permissionId)) {
            return new Gson().toJson("You don't have the permission!");
        }
        if (request.body()
                .equals("")) {
            List<Operator> operators = opDb.getOperators();
            return new Gson().toJson(operators);
        }
        Operator op = new Gson().fromJson(request.body(), Operator.class);
        Optional<Operator> optOperator = opDb.getOperator(op.getEmail());
        Operator operator = optOperator.orElseThrow();
        return new Gson().toJson(operator);
    }

    public String insertOperator(Request request, Response response) {
        return "insertOp";
    }

}
