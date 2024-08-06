package Kaffeebohnen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Products implements Distribution {

    public Products() {
    };

    @Override
    public HashMap<String, List<String>> importAndDistribution() {
        String csvFile = "C:/Users/Martin/Desktop/Kaffeebohnen/products.csv";
        String line = "";

        HashMap<String, List<String>> sheet = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (!sheet.containsKey("product_id")) {
                    sheet.put("product_id", new ArrayList<>());
                }
                sheet.get("product_id").add(parts[0]);

                if (!sheet.containsKey("coffee_type")) {
                    sheet.put("coffee_type", new ArrayList<>());
                }
                sheet.get("coffee_type").add(parts[1]);

                if (!sheet.containsKey("roast_type")) {
                    sheet.put("roast_type", new ArrayList<>());
                }
                sheet.get("roast_type").add(parts[2]);

                if (!sheet.containsKey("size")) {
                    sheet.put("size", new ArrayList<>());
                }
                sheet.get("size").add(parts[3]);

                if (!sheet.containsKey("unit_price")) {
                    sheet.put("unit_price", new ArrayList<>());
                }
                sheet.get("unit_price").add(parts[4]);

                if (!sheet.containsKey("price_per_100g")) {
                    sheet.put("price_per_100g", new ArrayList<>());
                }
                sheet.get("price_per_100g").add(parts[5]);

                if (!sheet.containsKey("profit")) {
                    sheet.put("profit", new ArrayList<>());
                }
                sheet.get("profit").add(parts[6]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheet;
    }

    // getters
    public static List<String> productId(HashMap<String, List<String>> data) {
        List<String> product_id = new ArrayList<>();
        product_id = data.get("product_id").stream().collect(Collectors.toList());
        return product_id;
    }

    public static List<String> coffee_type(HashMap<String, List<String>> data) {
        List<String> coffee_type = new ArrayList<>();
        coffee_type = data.get("coffee_type").stream().collect(Collectors.toList());
        return coffee_type;
    }

    public static List<String> roast_type(HashMap<String, List<String>> data) {
        List<String> roast_type = new ArrayList<>();
        roast_type = data.get("roast_type").stream().collect(Collectors.toList());
        return roast_type;
    }

    public static List<String> size(HashMap<String, List<String>> data) {
        List<String> size = new ArrayList<>();
        size = data.get("size").stream().collect(Collectors.toList());
        return size;
    }

    public static List<String> unit_price(HashMap<String, List<String>> data) {
        List<String> unit_price = new ArrayList<>();
        unit_price = data.get("unit_price").stream().collect(Collectors.toList());
        return unit_price;
    }

    public static List<String> price_per_100g(HashMap<String, List<String>> data) {
        List<String> price_per_100g = new ArrayList<>();
        price_per_100g = data.get("price_per_100g").stream().collect(Collectors.toList());
        return price_per_100g;
    }

    public static List<String> profit(HashMap<String, List<String>> data) {
        List<String> profit = new ArrayList<>();
        profit = data.get("profit").stream().collect(Collectors.toList());
        return profit;
    }

}
