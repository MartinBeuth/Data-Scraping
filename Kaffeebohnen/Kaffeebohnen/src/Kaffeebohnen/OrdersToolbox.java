package Kaffeebohnen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrdersToolbox {

    public List<String> getMatchingCustomerName(HashMap<String, List<String>> data,
            HashMap<String, List<String>> data2) {
        List<String> customerIds = Customers.customer_id(data);
        List<String> orderCustomerIds = Orders.customer_id(data2);
        List<String> matchingCustomerNames = new ArrayList<>();

        HashMap<String, Integer> customerIdIndexMap = new HashMap<>();
        for (int i = 0; i < customerIds.size(); i++) {
            customerIdIndexMap.put(customerIds.get(i), i);
        }

        for (String customerId : orderCustomerIds) {
            if (customerIdIndexMap.containsKey(customerId)) {
                int index = customerIdIndexMap.get(customerId);
                matchingCustomerNames.add(Customers.customer_name(data).get(index));
            }
        }

        return matchingCustomerNames;
    }

    public List<String> getMatchingEmail(HashMap<String, List<String>> data,
            HashMap<String, List<String>> data2) {
        List<String> customerIds = Customers.customer_id(data);
        List<String> orderCustomerIds = Orders.customer_id(data2);
        List<String> matchingEmails = new ArrayList<>();

        HashMap<String, Integer> customerIdIndexMap = new HashMap<>();
        for (int i = 0; i < customerIds.size(); i++) {
            customerIdIndexMap.put(customerIds.get(i), i);
        }

        for (String customerId : orderCustomerIds) {
            if (customerIdIndexMap.containsKey(customerId)) {
                int index = customerIdIndexMap.get(customerId);
                List<String> emails = Customers.email(data);
                if (index >= 0 && index < emails.size()) {
                    matchingEmails.add(emails.get(index));
                }
            }
            for (int i = 0; i < matchingEmails.size(); i++) {
                if (matchingEmails.get(i).isEmpty()) {
                    matchingEmails.set(i, "not available");
                }
            }
        }
        return matchingEmails;
    }

    public List<String> getMatchingCountry(HashMap<String, List<String>> data,
            HashMap<String, List<String>> data2) {
        List<String> customerIds = Customers.customer_id(data);
        List<String> orderCustomerIds = Orders.customer_id(data2);
        List<String> matchingCountries = new ArrayList<>();

        HashMap<String, Integer> customerIdIndexMap = new HashMap<>();
        for (int i = 0; i < customerIds.size(); i++) {
            customerIdIndexMap.put(customerIds.get(i), i);
        }

        for (String customerId : orderCustomerIds) {
            if (customerIdIndexMap.containsKey(customerId)) {
                int index = customerIdIndexMap.get(customerId);
                List<String> countries = Customers.country(data);
                if (index >= 0 && index < countries.size()) {
                    matchingCountries.add(countries.get(index));
                }
            }
            for (int i = 0; i < matchingCountries.size(); i++) {
                if (matchingCountries.get(i).isEmpty()) {
                    matchingCountries.set(i, "not available");
                }
            }
        }
        return matchingCountries;
    }

    public List<String> getMatchingCoffeeTypes(HashMap<String, List<String>> data1,
            HashMap<String, List<String>> data2) {
        List<String> productIds1 = new ArrayList<>(data1.keySet());
        List<String> allColoumNames = new ArrayList<>(data2.keySet());
        List<String> allRowNames = new ArrayList<>(productIds1);
        allRowNames.retainAll(allColoumNames);

        List<String> uniqueID = new ArrayList<>();
        List<String> matchingCoffee2 = new ArrayList<>();

        for (String productId : allRowNames) {
            List<String> idTypes1 = data1.get(productId);
            List<String> idTypes2 = data2.get(productId);
            if (idTypes1 != null && idTypes2 != null) {
                uniqueID.addAll(idTypes1);
                uniqueID.addAll(idTypes2);
            }
        }

        for (String value : uniqueID) {
            char firstLetter = value.charAt(0);

            String coffeeType;
            switch (firstLetter) {
                case 'A':
                    coffeeType = "Ara";
                    break;
                case 'R':
                    coffeeType = "Rob";
                    break;
                case 'L':
                    coffeeType = "Lib";
                    break;
                case 'E':
                    coffeeType = "Exc";
                    break;
                default:
                    coffeeType = "Coffee Type";
                    break;
            }

            matchingCoffee2.add(coffeeType);
        }

        return matchingCoffee2;
    }

}
