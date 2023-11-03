package org.lessons.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Parametri di Connessione

        // URL Database
        String url = "jdbc:mysql://localhost:3306/db_nations";
        // Username
        String user = "root";
        // Password
        String password = "root";

        //provo ad aprire una connessione con try with resources
        try(Connection connection = DriverManager.getConnection(url, user, password)) {

        } catch (SQLException e) {
            System.out.println("Unable to open connection!");
            e.printStackTrace();
        }
    }
}
