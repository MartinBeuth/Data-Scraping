package Kaffeebohnen.Performanceverbesserung;

import Kaffeebohnen.*;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class Performupdate {

    public Performupdate() {
    }

    public void performance(String serFile, String csvFile, Database data) {
        // Daten importieren und verarbeiten
        Map<String, List<String>> sheetData = importSheets();

        // Eindeutige Kundenobjekte erstellen
        List<UniqueCustomer> uniqueCustomers = createUniqueCustomers(sheetData);

        // Ausgabe der Kundeninformationen in Dateien
        outputToFiles(uniqueCustomers, csvFile, data);
    }

    // ------------------------------------------------------------------------------------------------------

    private Map<String, List<String>> importSheets() {
        // Sheets importieren
        Distribution customers = DistributionFactory.getDistribution("customers");
        Distribution orders = DistributionFactory.getDistribution("orders");
        Distribution products = DistributionFactory.getDistribution("products");

        HashMap<String, List<String>> sheetCustomer = customers.importAndDistribution();
        HashMap<String, List<String>> sheetOrder = orders.importAndDistribution();
        HashMap<String, List<String>> sheetProduct = products.importAndDistribution();

        // Zuweisen der Informationen aus den verschiedenen Sheets
        Map<String, List<String>> sheetData = new HashMap<>();

        Orders newOrder = new Orders();
        sheetData.put("customerOrders", newOrder.customer_ids(sheetOrder));
        sheetData.put("names", newOrder.getMatchingCustomerName(sheetCustomer, sheetOrder));
        sheetData.put("emails", newOrder.getMatchingEmail(sheetCustomer, sheetOrder));
        sheetData.put("countries", newOrder.getMatchingCountry(sheetCustomer, sheetOrder));
        sheetData.put("coffeeTypes", newOrder.getMatchingCoffeeTypes(sheetOrder, sheetProduct));

        return sheetData;
    }

    private List<UniqueCustomer> createUniqueCustomers(Map<String, List<String>> sheetData) {
        // Eindeutige Kundenobjekte erstellen
        List<UniqueCustomer> uniqueCustomersList = new ArrayList<>();

        ForkJoinPool pool = new ForkJoinPool();
        Forker task = new Forker(
                sheetData.get("customerOrders"),
                sheetData.get("names"),
                sheetData.get("emails"),
                sheetData.get("countries"),
                sheetData.get("coffeeTypes"),
                uniqueCustomersList);

        pool.invoke(task);

        return uniqueCustomersList;
    }

    private void outputToFiles(List<UniqueCustomer> customers, String csvFile, Database data) {
        // Ausgabe der Kundeninformationen in CSV-Datei und HTML-Datei
        writeToCsvFile(customers, csvFile);
        generateHtmlOutput(customers);

        // Datenbank-Abfragen
        data = new Database();

        data.insertCustomers(customers);

        data.myQuery();

        System.out.println("Aggregierte Abfrage zur Zählung der Kunden pro Kaffee-Typ: ");
        data.countCustomersByCoffeeType();
        System.out.println("mehrere Kaffeesorten: ");
        data.getCustomersWithMultipleCoffeeTypes();

    }

    private void writeToCsvFile(List<UniqueCustomer> customers, String csvFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            for (UniqueCustomer customer : customers) {
                System.out.println(customer.getCustomerID() + " " + customer.getNames() + " " +
                        customer.getEmail() + " " + customer.getCountry() + " " + customer.getCoffeeType());
                writer.println(customer.getCustomerID() + ", " + customer.getNames() + ", " +
                        customer.getEmail() + ", " + customer.getCountry() + ", " +
                        customer.getCoffeeType());
            }
            System.out.println("CSV file ready.");
        } catch (IOException e) {
            System.err.println("Error writing to the CSV file: " + e.getMessage());
        }
    }

    private void generateHtmlOutput(List<UniqueCustomer> customers) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("output_verbesserung.html"))) {
            writer.println("<html><head><title>Customer Information</title>");
            writer.println("<style>");

            // CSS-Styles für das HTML-Dokument
            writer.println(
                    "body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f9f9f9; }");
            writer.println(
                    ".container { max-width: 800px; margin: 20px auto; padding: 20px; background-color: #e8f5e9; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); }");
            writer.println("h1 { text-align: center; color: #333; }");
            writer.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
            writer.println("th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }");
            writer.println("th { background-color: #4CAF50; color: white; }");
            writer.println("tr:hover { background-color: #c8e6c9; }");
            writer.println("tr.email-not-available:hover { background-color: #ffccbc; }");
            writer.println("tr:nth-child(even) { background-color: #f9f9f9; }");

            writer.println("</style></head>");
            writer.println("<body>");
            writer.println("<div class=\"container\">");
            writer.println("<h1>Customer Details</h1>");
            writer.println("<table>");
            writer.println(
                    "<tr><th>Customer ID</th><th>Name</th><th>Email</th><th>Country</th><th>Coffee Type</th></tr>");

            for (UniqueCustomer customer : customers) {
                String emailClass = customer.getEmail().equalsIgnoreCase("not available") ? "email-not-available" : "";
                writer.println("<tr class=\"" + emailClass + "\">");
                writer.println("<td>" + customer.getCustomerID() + "</td>");
                writer.println("<td>" + customer.getNames() + "</td>");
                writer.println("<td>" + customer.getEmail() + "</td>");
                writer.println("<td>" + customer.getCountry() + "</td>");
                writer.println("<td>" + customer.getCoffeeType() + "</td>");
                writer.println("</tr>");
            }

            writer.println("</table>");
            writer.println("</div>");
            writer.println("</body></html>");

            System.out.println("HTML document generated successfully.");

        } catch (IOException e) {
            System.err.println("Error writing to the HTML file: " + e.getMessage());
        }
    }

}