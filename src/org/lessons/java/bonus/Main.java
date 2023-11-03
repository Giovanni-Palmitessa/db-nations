package org.lessons.java.bonus;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Istanzio lo scanner
        Scanner scanner = new Scanner(System.in);

        // Parametri di Connessione

        // URL Database
        String url = "jdbc:mysql://localhost:3306/db_nations";
        // Username
        String user = "root";
        // Password
        String password = "root";

        //provo ad aprire una connessione con try with resources
        try(Connection connection = DriverManager.getConnection(url, user, password)) {
            //Chiedo all'utente di inserire un paese da cercare
            System.out.println("Search a country by name: ");
            String searchCountry =scanner.nextLine();

            //query pe rla ricerca
            String query =
                    "SELECT c.name as country_name, c.country_id as country_id, r.name as region_name, c2.name as continent_name, " + "cs.`year` as statistics_year, cs.population as country_population, cs.gdp as country_gdp, l.`language` as country_language " +
                            "FROM countries c " +
                            "JOIN regions r ON r.region_id = c.region_id " +
                            "JOIN continents c2 ON c2.continent_id = r.continent_id " +
                            "JOIN country_languages cl ON cl.country_id = c.country_id " +
                            "JOIN languages l ON cl.language_id = l.language_id " +
                            "JOIN country_stats cs ON cs.country_id = c.country_id " +
                            "WHERE c.country_id LIKE 107 " +
                            "ORDER BY cs.`year` desc";
            //la connection prepara uno statement sql
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                //Binding dei parametri
                preparedStatement.setString(1, "%" + searchCountry + "%");
                //eseguo il prepare statement
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    //itero sul result set
                    while (resultSet.next()) {
                        // ad ogni iterazione resultSet si sposta e punta alla riga successiva
                        String countryName = resultSet.getString("country_name");
                        int countryID = resultSet.getInt("country_id");
                        String regionName = resultSet.getString("region_name");
                        String continentName = resultSet.getString("continent_name");

                        //Stampa a video i risultati
                        System.out.println("Country name: " + countryName + ", Country ID: " + countryID + ", Region name: " + regionName + ", Continent name: " + continentName);
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
