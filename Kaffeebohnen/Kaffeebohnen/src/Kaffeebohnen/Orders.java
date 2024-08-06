package Kaffeebohnen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Orders extends OrdersToolbox implements Distribution {

    public Orders() {
    };

    // für das Interface Distribution
    @Override
    public HashMap<String, List<String>> importAndDistribution() {
        String csvFile = "C:/Users/Martin/Desktop/Kaffeebohnen/orders.csv";
        String line = "";

        HashMap<String, List<String>> sheet = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (!sheet.containsKey("order_id")) {
                    sheet.put("order_id", new ArrayList<>());
                }
                sheet.get("order_id").add(parts[0]);

                if (!sheet.containsKey("order_date")) {
                    sheet.put("order_date", new ArrayList<>());
                }
                sheet.get("order_date").add(parts[1]);

                if (!sheet.containsKey("customer_id")) {
                    sheet.put("customer_id", new ArrayList<>());
                }
                sheet.get("customer_id").add(parts[2]);

                if (!sheet.containsKey("product_id")) {
                    sheet.put("product_id", new ArrayList<>());
                }
                sheet.get("product_id").add(parts[3]);

                if (!sheet.containsKey("quantity")) {
                    sheet.put("quantity", new ArrayList<>());
                }
                sheet.get("quantity").add(parts[4]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheet;
    }

    // get-Methods

    public static List<String> order_id(HashMap<String, List<String>> data) {
        List<String> order_id = new ArrayList<>();
        order_id = data.get("order_id").stream().collect(Collectors.toList());
        return order_id;
    }

    public static List<String> order_date(HashMap<String, List<String>> data) {
        List<String> order_date = new ArrayList<>();
        order_date = data.get("order_date").stream().collect(Collectors.toList());
        return order_date;
    }

    public static List<String> customer_id(HashMap<String, List<String>> data) {
        List<String> customer_id = new ArrayList<>();
        customer_id = data.get("customer_id").stream().collect(Collectors.toList());
        return customer_id;
    }

    public List<String> customer_ids(HashMap<String, List<String>> data) {
        List<String> customer_id = new ArrayList<>();
        customer_id = data.get("customer_id").stream().collect(Collectors.toList());
        return customer_id;
    }

    public static List<String> product_id(HashMap<String, List<String>> data) {
        List<String> product_id = new ArrayList<>();
        product_id = data.get("product_id").stream().collect(Collectors.toList());
        return product_id;
    }

    public static List<String> quantity(HashMap<String, List<String>> data) {
        List<String> quantity = new ArrayList<>();
        quantity = data.get("quantity").stream().collect(Collectors.toList());
        return quantity;
    }

}