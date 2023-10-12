/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkrole.operator;

import com.mycompany.sparkrole.dbconnection.DbConnection;
import com.mycompany.sparkrole.permissions.Permissions;
import com.mycompany.sparkrole.role.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author AVShrez
 */
public class OperatorDbdao {

    private Map<Integer, Operator> operators = new HashMap<>();

    public int login(String email, String password) {
        int loginStatus = 0;
        try (Connection con = DbConnection.getInstance()
                .getConnection();
             PreparedStatement callLoginStatus = con.prepareStatement(
                     "CALL LoginStatus(?,?)")) {
            callLoginStatus.setString(1, email);
            callLoginStatus.setString(2, password);
            try (ResultSet resultStatus = callLoginStatus.executeQuery()) {
                resultStatus.next();
                loginStatus = resultStatus.getInt("login_status");
//                return loginStatus;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return loginStatus;
    }

    public Optional<Operator> getOperator(String email) {
        Operator operator = null;
        Role role = null;
        try (Connection con = DbConnection.getInstance()
                .getConnection();
             PreparedStatement getOp = con.prepareStatement(
                     "SELECT * FROM operator WHERE email = ?");
             PreparedStatement getRole = con
                     .prepareStatement(
                             new StringBuilder().append(
                                     "SELECT roles.id, roles.name, permissions.id, permissions.section, permissions.operation, permissions.description ")
                                     .append(
                                             "From roles ")
                                     .append(
                                             "INNER JOIN role_permissions ")
                                     .append(
                                             "ON roles.id = role_permissions.role_id ")
                                     .append(
                                             "INNER JOIN permissions ")
                                     .append(
                                             "ON role_permissions.permissions_id = permissions.id ")
                                     .append(
                                             "WHERE roles.id = ?;")
                                     .toString());) {
            getOp.setString(1, email);
            try (ResultSet resultOp = getOp.executeQuery()) {
                if (resultOp.next()) {
                    operator = new Operator();
                    operator.setName(resultOp.getString("name"));
                    operator.setEmail(resultOp.getString("email"));
                    operator.setStatus(resultOp.getString("status"));
                    operator.setLoginCount(resultOp.getInt("login_count"));

                    getRole.setInt(1, resultOp.getInt("role_id"));
                    try (ResultSet resultSet = getRole
                            .executeQuery()) {
                        while (resultSet.next()) {
                            if (role == null) {
                                role = new Role(resultSet.getInt(
                                        "id"),
                                                resultSet.getString(
                                                        "name"),
                                                new Permissions(
                                                        resultSet
                                                                .getInt(
                                                                        "permissions.id"),
                                                        resultSet
                                                                .getString(
                                                                        "section"),
                                                        resultSet
                                                                .getString(
                                                                        "operation"),
                                                        resultSet
                                                                .getString(
                                                                        "description")));
                            } else {
                                role.addToPermissions(
                                        new Permissions(
                                                resultSet.getInt(
                                                        "permissions.id"),
                                                resultSet.getString(
                                                        "section"),
                                                resultSet.getString(
                                                        "operation"),
                                                resultSet.getString(
                                                        "description"))
                                );
                            }
                        }
                    }
                    Optional<Role> optRole = Optional.ofNullable(role);
                    if (optRole.isPresent()) {
                        operator.setRole(optRole.orElseThrow());
                    }
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Optional.ofNullable(operator);
    }

    public Optional<Operator> getOperator(int id) {
        Operator operator = null;
        Role role = null;
        try (Connection con = DbConnection.getInstance()
                .getConnection();
             PreparedStatement getOp = con.prepareStatement(
                     "SELECT * FROM operator WHERE id = ?");
             PreparedStatement getRole = con
                     .prepareStatement(
                             new StringBuilder().append(
                                     "SELECT roles.id, roles.name, permissions.id, permissions.section, permissions.operation, permissions.description ")
                                     .append(
                                             "From roles ")
                                     .append(
                                             "INNER JOIN role_permissions ")
                                     .append(
                                             "ON roles.id = role_permissions.role_id ")
                                     .append(
                                             "INNER JOIN permissions ")
                                     .append(
                                             "ON role_permissions.permissions_id = permissions.id ")
                                     .append(
                                             "WHERE roles.id = ?;")
                                     .toString());) {
            getOp.setInt(1, id);
            try (ResultSet resultOp = getOp.executeQuery()) {
                if (resultOp.next()) {
                    operator = new Operator();
                    operator.setName(resultOp.getString("name"));
                    operator.setEmail(resultOp.getString("email"));
                    operator.setStatus(resultOp.getString("status"));
                    operator.setLoginCount(resultOp.getInt("login_count"));

                    getRole.setInt(1, resultOp.getInt("role_id"));
                    try (ResultSet resultSet = getRole
                            .executeQuery()) {
                        while (resultSet.next()) {
                            if (role == null) {
                                role = new Role(resultSet.getInt(
                                        "id"),
                                                resultSet.getString(
                                                        "name"),
                                                new Permissions(
                                                        resultSet
                                                                .getInt(
                                                                        "permissions.id"),
                                                        resultSet
                                                                .getString(
                                                                        "section"),
                                                        resultSet
                                                                .getString(
                                                                        "operation"),
                                                        resultSet
                                                                .getString(
                                                                        "description")));
                            } else {
                                role.addToPermissions(
                                        new Permissions(
                                                resultSet.getInt(
                                                        "permissions.id"),
                                                resultSet.getString(
                                                        "section"),
                                                resultSet.getString(
                                                        "operation"),
                                                resultSet.getString(
                                                        "description"))
                                );
                            }
                        }
                    }
                    Optional<Role> optRole = Optional.ofNullable(role);
                    if (optRole.isPresent()) {
                        operator.setRole(optRole.orElseThrow());
                    }
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Optional.ofNullable(operator);
    }

    public List<Operator> getOperators() {
        try (Connection con = DbConnection.getInstance()
                .getConnection();
             PreparedStatement preparedStmt = con.prepareStatement(
                     new StringBuilder().append(
                             "SELECT operator.id, operator.name, operator.email,operator.password, operator.status, ")
                             .append("operator.login_count, operator.role_id, roles.name, permissions.id, ")
                             .append("permissions.section, permissions.operation, permissions.description ")
                             .append("FROM operator ")
                             .append("INNER JOIN roles ")
                             .append("ON operator.role_id = roles.id ")
                             .append("INNER JOIN role_permissions ")
                             .append("ON operator.role_id = role_permissions.role_id ")
                             .append("INNER JOIN permissions ")
                             .append("ON role_permissions.permissions_id = permissions.id; ")
                             .toString());
             ResultSet resultSet = preparedStmt.executeQuery();) {
            while (resultSet.next()) {
                if (operators.containsKey(resultSet.getInt("operator.id"))) {
                    operators.get(resultSet.getInt("operator.id"))
                            .getRole()
                            .addToPermissions(new Permissions(
                                    resultSet.getInt("permissions.id"),
                                    resultSet.getString(
                                            "section"), resultSet
                                            .getString(
                                                    "operation"),
                                    resultSet.getString("description")));
                } else {
                    operators.put(resultSet.getInt("operator.id"), new Operator(
                                  resultSet
                                          .getString("name"), resultSet
                                  .getString(
                                          "email"),
                                  resultSet.getString("status"), resultSet
                                  .getInt("login_count"), new Role(resultSet
                                  .getInt("role_id"), resultSet.getString(
                                                                   "roles.name"),
                                                                   new Permissions(
                                                                           resultSet
                                                                                   .getInt("permissions.id"),
                                                                           resultSet
                                                                                   .getString(
                                                                                           "section"),
                                                                           resultSet
                                                                                   .getString(
                                                                                           "operation"),
                                                                           resultSet
                                                                                   .getString(
                                                                                           "description")))));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>(operators.values());
    }
}
