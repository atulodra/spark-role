/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.sparkrole;

/**
 *
 * @author AVShrez
 */
import com.mycompany.sparkrole.role.RoleController;
import static spark.Spark.*;

public class Sparkrole {

    private final static RoleController roleController = new RoleController();

    public static void main(String[] args) {
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
            path("/roles", () -> {
                before("", (request, response) -> {
                    response.header("Access-Control-Allow-Origin",
                            "http://localhost:5173");
                    response.header("Access-Control-Allow-Methods",
                            "GET, PUT, POST, DELETE");
                    response.header("Access-Control-Allow-Headers", "Accept");
                    response.header("Access-Control-Allow-Credentials", "true");
                });
                get("", (req, res) -> roleController.getRoles(req, res));
            });
            path("/roles/:id", () -> {
                before("", (request, response) -> {
                    response.header("Access-Control-Allow-Origin",
                            "http://localhost:5173");
                    response.header("Access-Control-Allow-Methods",
                            "GET, PUT, POST, DELETE");
                    response.header("Access-Control-Allow-Headers", "Accept");
                    response.header("Access-Control-Allow-Credentials",
                            "true");
                });
                get("",
                        (req, res) -> roleController.getRole(req, res));
            });

        });
    }
}
