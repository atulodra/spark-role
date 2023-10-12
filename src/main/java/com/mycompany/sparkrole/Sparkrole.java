/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.sparkrole;

/**
 *
 * @author AVShrez
 */
import com.google.gson.Gson;
import com.mycompany.sparkrole.operator.OperatorController;
import com.mycompany.sparkrole.role.RoleController;
import java.io.IOException;
import static spark.Spark.*;

public class Sparkrole {

    private final static RoleController roleController = new RoleController();
    private final static OperatorController operatorController = new OperatorController();

    public static void main(String[] args) throws IOException {

        port(8116);
        path("/api", () -> {

         options("/*", (request, response) -> {
             String accessControlRequestHeaders = request.headers(
                     "Access-Control-Request-Headers");
             if (accessControlRequestHeaders != null) {
                 response.header("Access-Control-Allow-Headers",
                                 accessControlRequestHeaders);
             }
             String accessControlRequestMethod = request.headers(
                     "Access-Control-Request-Method");
             if (accessControlRequestMethod != null) {
                 response.header(accessControlRequestHeaders,
                                 accessControlRequestMethod);
             }
             return "OK";
         });
         before("/*", (request, response) -> {
            response.header("Access-Control-Allow-Origin",
                            "http://localhost:5173");
            response.header("Access-Control-Allow-Methods",
                            "GET, PUT, POST, DELETE");
            response.header("Access-Control-Allow-Headers", "Accept");
            response.header("Access-Control-Allow-Credentials", "true");

            if (!request.pathInfo()
                    .equals("/api/login")) {
                if (request.session()
                        .attribute("loggedInOperator") == null) {
                    halt(401, "You are not authenticated!");
                }
            }
        });
         path("/login", () -> {
          post("", (req, res) -> operatorController.login(req, res));
      });
         path("/roles", () -> {
          get("", (req, res) -> roleController.getRoles(req, res));
          post("", (req, res) -> roleController.insertRole(req, res));
          path("/:id", () -> {
           get("",
               (req, res) -> roleController.getRole(req, res));
           delete("", (req, res) -> roleController.deleteRole(req, res));
           put("", (req, res) -> roleController.editRole(req, res));
       });

      });
         path("/operators", () -> {
          get("", (req, res) -> operatorController.getOperators(req, res));
      });

     });

    }
}
