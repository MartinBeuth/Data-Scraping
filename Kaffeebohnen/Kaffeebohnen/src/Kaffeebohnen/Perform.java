package Kaffeebohnen;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Perform {

    public Perform() {
    };

    public void performance(String serFil, String csvFil) {
        // Files
        String serFile = serFil;
        String csvFile = csvFil;
        // --------------------------------------------------------------------------------------

        // Sheets importieren
        Distribution customers = new Customers();
        Distribution orders = new Orders();
        Distribution products = new Products();
        HashMap<String, List<String>> sheet_customer = customers.importAndDistribution();
        HashMap<String, List<String>> sheet_order = orders.importAndDistribution();
        HashMap<String, List<String>> sheet_product = products.importAndDistribution();
        // --------------------------------------------------------------------------------------

        // Zuweisen der Informationen aus den verschiedenen Sheets
        Orders new_Order = new Orders();
        List<String> customerOrders = new_Order.customer_ids(sheet_order);
        List<String> namen = new_Order.getMatchingCustomerName(sheet_customer, sheet_order);
        List<String> email = new_Order.getMatchingEmail(sheet_customer, sheet_order);
        List<String> country = new_Order.getMatchingCountry(sheet_customer, sheet_order);
        List<String> coffeeType = new_Order.getMatchingCoffeeTypes(sheet_order, sheet_product);
        // --------------------------------------------------------------------------------------

        // UniqueCustomer und artverwandte Serialisierungs-Objekte anlegen
        List<UniqueCustomer> uniqueCustomersList = new ArrayList<>();
        UniqueCustomerSerializer serialize = new UniqueCustomerSerializer();
        // --------------------------------------------------------------------------------------

        // Zuweisen der Informationen an die UniqueCustomer Objekte

        int maxSize = customerOrders.size();
        for (int i = 0; i < maxSize; i++) {
            UniqueCustomer customer = new UniqueCustomer();
            customer.setAllData(customerOrders.get(i), namen.get(i), email.get(i), country.get(i), coffeeType.get(i));
            uniqueCustomersList.add(customer);
        }
        // --------------------------------------------------------------------------------------

        // Serialisierung
        serialize.serializeUniqueCustomers(uniqueCustomersList, serFile);
        // --------------------------------------------------------------------------------------

        // De-Serialisierung und Ausgabe auf Konsole,im Spreadsheet-Format,HTML

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile));) {
            for (UniqueCustomer c : serialize.deserializeUniqueCustomers(serFile)) {
                // Console
                System.out.println(c.getCustomerID() + " " + c.getNames() + " " +
                        c.getEmail() + " " + c.getCountry() + " "
                        + c.getCoffeeType());
                // CSV
                writer.println(c.getCustomerID() + ", " + c.getNames() + ", " +
                        c.getEmail() + ", " + c.getCountry() + ", "
                        + c.getCoffeeType());

            }

            System.out.println("Ready.");

        } catch (IOException e) {
            System.err.println("Error writing to the CSV file: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("output.html"));) {
            writer.println("<html>");
            writer.println("<head><title>Customer Information</title>");
            writer.println("<style>");
            writer.println("table { font-family: Arial, sans-serif; border-collapse: collapse; width: 100%; }");
            writer.println("th { background-color: #f2e2f2; }");
            writer.println("th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }");
            writer.println("tr:nth-child(even) { background-color: #f2f2f2; }");
            writer.println("tr:hover { background-color: #e5e5f5; }");
            writer.println("</style></head>");
            writer.println("<body>");
            writer.println(
                    "<h1 style=\"font-family: Arial, sans-serif; color: #333; background-color: #e5e5f5; padding: 10px 20px; border-radius: 5px; box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1); text-align: center;\">Customer Details</h1>");

            writer.println("<table border=\"1\">");
            writer.println(
                    "<tr><th>Customer ID</th><th>Name</th><th>Email</th><th>Country</th><th>Coffee Type</th></tr>");

            for (UniqueCustomer c : serialize.deserializeUniqueCustomers(serFile)) {
                writer.println("<tr>");
                writer.println("<td>" + c.getCustomerID() + "</td>");
                writer.println("<td>" + c.getNames() + "</td>");
                writer.println("<td>" + c.getEmail() + "</td>");
                writer.println("<td>" + c.getCountry() + "</td>");
                writer.println("<td>" + c.getCoffeeType() + "</td>");
                writer.println("</tr>");
            }

            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");

            System.out.println("HTML document generated successfully.");

        } catch (IOException e) {
            System.err.println("Error writing to the HTML file: " + e.getMessage());
        }

    }

}
