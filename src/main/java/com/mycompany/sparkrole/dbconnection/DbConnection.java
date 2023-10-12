/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkrole.dbconnection;

import config.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author AVShrez
 */
public class DbConnection {

//    private static interface Singleton {
//
//        final DbConnection INSTANCE = new DbConnection();
//    }
    private static DbConnection instance = null;
    private final Connection connection;

    private DbConnection() throws SQLException {
        Properties dbProps = Config.getConfig();
        String server = dbProps.getProperty("server");
        String port = dbProps.getProperty("port");
        String database = dbProps.getProperty("database");
        String user = dbProps.getProperty("user");
        String password = dbProps.getProperty("password");
        StringBuilder connectionURL = new StringBuilder();
        connectionURL.append("jdbc:mysql://")
                .append(server)
                .append(":")
                .append(port)
                .append("/")
                .append(database)
                .append("?user=")
                .append(user)
                .append("&password=")
                .append(password);
        this.connection = DriverManager.getConnection(connectionURL.toString());
    }

    public Connection getConnection() {
        return connection;
    }

    public static DbConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection()
                .isClosed()) {
            instance = new DbConnection();
        }
        return instance;
    }

}
