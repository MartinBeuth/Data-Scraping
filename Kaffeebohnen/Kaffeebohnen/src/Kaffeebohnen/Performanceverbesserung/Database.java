package Kaffeebohnen.Performanceverbesserung;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Kaffeebohnen.UniqueCustomer;

public class Database extends DatabaseRequests {

    private Connection connection;

    public Database() {
        try {

            Class.forName("org.h2.Driver");

            this.connection = DriverManager.getConnection("jdbc:h2:file:~/data/test", "sa", "");
            System.out.println("Database connection established successfully.");

            createTable();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }
    // CRUD
    // -----------------------------------------------------------------------------------------------------

    // Create
    private void createTable() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS test (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "customerID VARCHAR(50), " +
                "name VARCHAR(100), " +
                "email VARCHAR(100), " +
                "country VARCHAR(50), " +
                "coffeeType VARCHAR(5))";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }

    // Read
    public void queryData() {
        String querySQL = "SELECT * FROM test";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(querySQL)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String customerID = rs.getString("customerID");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                String coffeeType = rs.getString("coffeeType");
                System.out.println("ID: " + id + ", Customer ID: " + customerID + ", Name: " + name + ", Email: "
                        + email + ", Country: " + country + ", Coffee Type: " + coffeeType);
            }
        } catch (SQLException e) {
            System.err.println("Error querying customers: " + e.getMessage());
        }
    }

    // Update1
    public void insertCustomers(List<UniqueCustomer> customers) {
        String insertSQL = "INSERT INTO test (customerID, name, email, country, coffeeType) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            for (UniqueCustomer customer : customers) {
                pstmt.setString(1, customer.getCustomerID());
                pstmt.setString(2, customer.getNames());
                pstmt.setString(3, customer.getEmail());
                pstmt.setString(4, customer.getCountry());
                pstmt.setString(5, customer.getCoffeeType());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            System.out.println("Customers successfully saved in the H2 database.");
        } catch (SQLException e) {
            System.err.println("Error inserting customers: " + e.getMessage());
        }
    }

    // Update2
    public void updateCustomer(int id, UniqueCustomer updatedCustomer) {
        String updateSQL = "UPDATE test SET customerID = ?, name = ?, email = ?, country = ?, coffeeType = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
            pstmt.setString(1, updatedCustomer.getCustomerID());
            pstmt.setString(2, updatedCustomer.getNames());
            pstmt.setString(3, updatedCustomer.getEmail());
            pstmt.setString(4, updatedCustomer.getCountry());
            pstmt.setString(5, updatedCustomer.getCoffeeType());
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
            System.out.println("Customer with ID " + id + " successfully updated.");
        } catch (SQLException e) {
            System.err.println("Error updating customer: " + e.getMessage());
        }
    }

    // Delete1
    public void deleteCustomer(int id) {
        String deleteSQL = "DELETE FROM test WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Customer with ID " + id + " successfully deleted.");
        } catch (SQLException e) {
            System.err.println("Error deleting customer: " + e.getMessage());
        }
    }

    //// Delete2
    public void deleteDatabase() {
        try {
            String dropTableSQL = "DROP TABLE IF EXISTS test";
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(dropTableSQL);
                System.out.println("Database table 'test' deleted successfully.");
            }

            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting database: " + e.getMessage());
        }
    }

    public void checkTableStructure() {
        String checkSQL = "SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='test'";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(checkSQL)) {
            System.out.println("Table Structure for 'users':");
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                String dataType = rs.getString("DATA_TYPE");
                System.out.println("Column Name: " + columnName + ", Data Type: " + dataType);
            }
        } catch (SQLException e) {
            System.err.println("Error checking table structure: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {
        Database test = new Database();
        test.createTable();

        // mock data
        List<UniqueCustomer> mockCustomers = new ArrayList<>();
        UniqueCustomer a = new UniqueCustomer();
        a.setAllData2(1, "123-234-ad", "Sarah Smith", "sarahSchmidt@gmail.com", "USA", "Rob");
        mockCustomers.add(a);

        // Insert mock data
        test.insertCustomers(mockCustomers);

        // display mock data
        test.queryData();
    }
}