package org.lessons.java;

import java.sql.*;

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
            String query = "";
            //la connection prepara uno statement sql
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                //eseguo il prepare statement
                try(ResultSet resultSet = preparedStatement.executeQuery()) {

                } catch (SQLException e) {
                    System.out.println("Unable to execute query");
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                System.out.println("Unable to prepare statement");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Unable to open connection!");
            e.printStackTrace();
        }
    }
}
