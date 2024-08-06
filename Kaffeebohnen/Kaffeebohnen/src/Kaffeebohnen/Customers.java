package Kaffeebohnen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Customers implements Distribution {

    public Customers() {
    };

    @Override
    public HashMap<String, List<String>> importAndDistribution() {
        String csvFile = "C:/Users/Martin/Desktop/Kaffeebohnen/customers.csv";
        String line = "";

        HashMap<String, List<String>> sheet = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (!sheet.containsKey("customer_id")) {
                    sheet.put("customer_id", new ArrayList<>());
                }
                sheet.get("customer_id").add(parts[0]);

                if (!sheet.containsKey("customer_name")) {
                    sheet.put("customer_name", new ArrayList<>());
                }
                sheet.get("customer_name").add(parts[1]);

                if (!sheet.containsKey("email")) {
                    sheet.put("email", new ArrayList<>());
                }
                sheet.get("email").add(parts[2]);

                if (!sheet.containsKey("phone_number")) {
                    sheet.put("phone_number", new ArrayList<>());
                }
                sheet.get("phone_number").add(parts[3]);

                if (!sheet.containsKey("adress_line_1")) {
                    sheet.put("adress_line_1", new ArrayList<>());
                }
                sheet.get("adress_line_1").add(parts[4]);

                if (!sheet.containsKey("city")) {
                    sheet.put("city", new ArrayList<>());
                }
                sheet.get("city").add(parts[5]);

                if (!sheet.containsKey("country")) {
                    sheet.put("country", new ArrayList<>());
                }
                sheet.get("country").add(parts[6]);

                if (!sheet.containsKey("postcode")) {
                    sheet.put("postcode", new ArrayList<>());
                }
                sheet.get("postcode").add(parts[7]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheet;
    }

    // getters

    public static List<String> customer_id(HashMap<String, List<String>> data) {
        List<String> customer_id = new ArrayList<>();
        customer_id = data.get("customer_id").stream().collect(Collectors.toList());
        return customer_id;
    }

    public static List<String> customer_name(HashMap<String, List<String>> data) {
        List<String> customer_name = new ArrayList<>();
        customer_name = data.get("customer_name").stream().collect(Collectors.toList());
        return customer_name;
    }

    public static List<String> email(HashMap<String, List<String>> data) {
        List<String> email = new ArrayList<>();
        email = data.get("email").stream().collect(Collectors.toList());
        return email;
    }

    public static List<String> phone_number(HashMap<String, List<String>> data) {
        List<String> phone_number = new ArrayList<>();
        phone_number = data.get("phone_number").stream().collect(Collectors.toList());
        return phone_number;
    }

    public static List<String> adress_line_1(HashMap<String, List<String>> data) {
        List<String> adress_line_1 = new ArrayList<>();
        adress_line_1 = data.get("adress_line_1").stream().collect(Collectors.toList());
        return adress_line_1;
    }

    public static List<String> country(HashMap<String, List<String>> data) {
        List<String> country = new ArrayList<>();
        country = data.get("country").stream().collect(Collectors.toList());
        return country;
    }

    public static List<String> postcode(HashMap<String, List<String>> data) {
        List<String> postcode = new ArrayList<>();
        postcode = data.get("postcode").stream().collect(Collectors.toList());
        return postcode;
    }

}
