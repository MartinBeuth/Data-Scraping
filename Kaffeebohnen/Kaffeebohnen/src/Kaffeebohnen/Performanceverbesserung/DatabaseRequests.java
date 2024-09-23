package Kaffeebohnen.Performanceverbesserung;

import java.sql.*;
import java.util.Scanner;

public class DatabaseRequests {

    private Connection connection;

    public DatabaseRequests() {
        try {
            // Verbindung Datenbank
            Class.forName("org.h2.Driver");
            this.connection = DriverManager.getConnection("jdbc:h2:file:~/data/test", "sa", "");
            System.out.println("Database connection established successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }

    // die eigene Abfrage
    public void myQuery() {
        try {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Your Request: ");
            String query = scanner.nextLine();

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Fehler bei der Ausführung der Abfrage: " + e.getMessage());
        }
    }

    // Aggregierte Abfrage zur Zählung der Kunden pro Kaffee-Typ
    public void countCustomersByCoffeeType() {
        String querySQL = "SELECT coffeeType, COUNT(*) AS customer_count " +
                "FROM test " +
                "GROUP BY coffeeType " +
                "HAVING COUNT(*) > 1";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(querySQL)) {
            while (rs.next()) {
                String coffeeType = rs.getString("coffeeType");
                int customerCount = rs.getInt("customer_count");
                System.out.println("Coffee Type: " + coffeeType + ", Customer Count: " + customerCount);
            }
        } catch (SQLException e) {
            System.err.println("Error counting customers by coffee type: " + e.getMessage());
        }
    }

    // mehrere Kaffeesorten
    public void getCustomersWithMultipleCoffeeTypes() {
        String querySQL = "SELECT name, COUNT(DISTINCT coffeeType) AS Anzahl_Coffee_Types, " +
                "GROUP_CONCAT(DISTINCT coffeeType) AS Coffee_Types " +
                "FROM test " +
                "WHERE coffeeType IN ('Ara', 'Exc', 'Lib', 'Rob') " +
                "GROUP BY name " +
                "HAVING COUNT(DISTINCT coffeeType) > 1 " +
                "ORDER BY Anzahl_Coffee_Types ASC";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(querySQL)) {
            while (rs.next()) {
                String name = rs.getString("name");
                int coffeeTypeCount = rs.getInt("Anzahl_Coffee_Types");
                String coffeeTypes = rs.getString("Coffee_Types");
                System.out.println("Name: " + name + ", Number of Coffee Types: " + coffeeTypeCount +
                        ", Coffee Types: " + coffeeTypes);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving customers with multiple coffee types: " + e.getMessage());
        }
    }

}
