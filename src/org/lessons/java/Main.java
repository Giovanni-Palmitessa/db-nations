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
            String query =
                    "SELECT c.name as country_name, c.country_id as country_id, r.name as region_name, c2.name as continent_name " +
                    "FROM countries c " +
                    "JOIN regions r ON r.region_id = c.region_id " +
                    "JOIN continents c2 ON c2.continent_id = r.continent_id " +
                    "ORDER BY c.name;";
            //la connection prepara uno statement sql
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                //eseguo il prepare statement
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    //itero sul result set
                    while (resultSet.next()) {
                        // ad ogni iterazione resultSet si sposta e punta alla riga successiva
                        String countryName = resultSet.getString("country_name");
                        int countryID = resultSet.getInt("country_id");
                    }
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
