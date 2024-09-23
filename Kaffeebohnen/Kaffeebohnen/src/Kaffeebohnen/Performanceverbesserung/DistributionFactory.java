package Kaffeebohnen.Performanceverbesserung;

import Kaffeebohnen.Customers;
import Kaffeebohnen.Distribution;
import Kaffeebohnen.Orders;
import Kaffeebohnen.Products;

public class DistributionFactory {
    public static Distribution getDistribution(String type) {
        switch (type.toLowerCase()) {
            case "customers":
                return new Customers();
            case "orders":
                return new Orders();
            case "products":
                return new Products();
            default:
                throw new IllegalArgumentException("Unknown distribution type: " + type);
        }
    }
}