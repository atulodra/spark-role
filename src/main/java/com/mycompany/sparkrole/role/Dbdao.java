/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkrole.role;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author AVShrez
 */
public class Dbdao {

    // Database properties
    private final String connectionURL = "jdbc:mysql://localhost:3306/testdb?user=root&password=whatapassword";

    //Internal properties
    private final Map<Integer, Role> rolePermissions = new HashMap<>();
    private final List<Permissions> permissions = new ArrayList<>();
//    private List<Role> roles = new ArrayList<>();

    public List<Role> getRoles() {
//        Role role;
        try (Connection con = DriverManager.getConnection(connectionURL);
             PreparedStatement preparedStmt = con.prepareStatement(
                     new StringBuilder().append(
                             "SELECT roles.id, roles.name, permissions.id, permissions.section, permissions.operation, permissions.description ")
                             .append("From roles ")
                             .append(
                                     "LEFT OUTER JOIN role_permissions ")
                             .append(
                                     "ON roles.id = role_permissions.role_id ")
                             .append(
                                     "LEFT OUTER JOIN permissions ")
                             .append(
                                     "ON role_permissions.permissions_id = permissions.id;")
                             .toString());
             ResultSet resultSet = preparedStmt.executeQuery();) {
//            preparedStmt.setInt(1, id);
            while (resultSet.next()) {

                if (rolePermissions.containsKey(resultSet.getInt("id"))) {
                    rolePermissions.get(resultSet.getInt("id"))
                            .addToPermissions(
                                    new Permissions(
                                            resultSet.getInt("permissions.id"),
                                            resultSet.getString(
                                                    "section"), resultSet
                                                    .getString(
                                                            "operation"),
                                            resultSet.getString("description")));
                } else {
                    if (resultSet.getInt("permissions.id") == 0) {
                        rolePermissions.put(resultSet.getInt("id"), new Role(
                                            resultSet.getInt("id"), resultSet
                                            .getString(
                                                    "name")));

                    } else {
                        rolePermissions.put(resultSet.getInt("id"), new Role(
                                            resultSet.getInt("id"), resultSet
                                            .getString(
                                                    "name"), new Permissions(
                                                    resultSet.getInt(
                                                            "permissions.id"),
                                                    resultSet.getString(
                                                            "section"),
                                                    resultSet
                                                            .getString(
                                                                    "operation"),
                                                    resultSet.getString(
                                                            "description"))));
                    }

                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
//        System.out.println(new HashSet<>(roles));
        System.out.println(rolePermissions.get(2)
                .getPermissions());
        return new ArrayList<>(rolePermissions.values());

    }

    public Optional<Role> getRole(int id) {
        Role role = null;
        //        System.out.println(role);
        //        Optional<Role> roleOpt = Optional.ofNullable(role);
        //        System.out.println(roleOpt.isEmpty());
        try (Connection con = DriverManager.getConnection(connectionURL);
             PreparedStatement preparedStmt = con.prepareStatement(
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
            preparedStmt.setInt(1, id);
            try (ResultSet resultSet = preparedStmt.executeQuery();) {
                while (resultSet.next()) {
                    if (role == null) {
                        role = new Role(resultSet.getInt("id"),
                                        resultSet.getString(
                                                "name"), new Permissions(
                                                resultSet.getInt(
                                                        "permissions.id"),
                                                resultSet.getString(
                                                        "section"), resultSet
                                                        .getString(
                                                                "operation"),
                                                resultSet.getString(
                                                        "description")));
                    } else {
                        role.addToPermissions(new Permissions(
                                resultSet.getInt("permissions.id"),
                                resultSet.getString(
                                        "section"), resultSet.getString(
                                        "operation"),
                                resultSet.getString("description"))
                        );
                    }
                }
            }
//            permissions.clear();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(role);

    }

    public void insertRole(Role role) throws SQLException {
        try (Connection con = DriverManager.getConnection(connectionURL);
             PreparedStatement preparedStmt = con.prepareStatement(
                     "INSERT INTO roles Values(default, ?)");) {
            preparedStmt.setString(1, role.getName());
            preparedStmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }
    }

    public void deleteRole(int id) throws SQLException {
        try (Connection con = DriverManager.getConnection(connectionURL);
             PreparedStatement preparedStmt = con.prepareStatement(
                     "DELETE FROM roles WHERE id = ?");) {
//            preparedStmt.setString(1, "title");
            preparedStmt.setInt(1, id);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }
    }

}
