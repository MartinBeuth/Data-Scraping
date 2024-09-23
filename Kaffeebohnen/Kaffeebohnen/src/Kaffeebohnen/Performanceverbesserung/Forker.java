package Kaffeebohnen.Performanceverbesserung;

import java.util.List;
import java.util.concurrent.RecursiveAction;

import Kaffeebohnen.UniqueCustomer;

public class Forker extends RecursiveAction {
    private final List<String> customerOrders;
    private final List<String> names;
    private final List<String> emails;
    private final List<String> countries;
    private final List<String> coffeeTypes;
    private final List<UniqueCustomer> uniqueCustomersList;

    private static final int THRESHOLD = 20;

    public Forker(List<String> customerOrders, List<String> names, List<String> emails,
            List<String> countries, List<String> coffeeTypes, List<UniqueCustomer> uniqueCustomersList) {
        this.customerOrders = customerOrders;
        this.names = names;
        this.emails = emails;
        this.countries = countries;
        this.coffeeTypes = coffeeTypes;
        this.uniqueCustomersList = uniqueCustomersList;
    }

    @Override
    protected void compute() {
        if (customerOrders.size() <= THRESHOLD) {
            for (int i = 0; i < customerOrders.size(); i++) {
                UniqueCustomer customer = new UniqueCustomer();
                customer.setAllData(customerOrders.get(i), names.get(i), emails.get(i), countries.get(i),
                        coffeeTypes.get(i));
                uniqueCustomersList.add(customer);
            }
        } else {
            int mid = customerOrders.size() / 2;

            Forker left = new Forker(customerOrders.subList(0, mid),
                    names.subList(0, mid), emails.subList(0, mid),
                    countries.subList(0, mid), coffeeTypes.subList(0, mid),
                    uniqueCustomersList);
            Forker right = new Forker(customerOrders.subList(mid, customerOrders.size()),
                    names.subList(mid, names.size()), emails.subList(mid, emails.size()),
                    countries.subList(mid, countries.size()), coffeeTypes.subList(mid, coffeeTypes.size()),
                    uniqueCustomersList);

            invokeAll(left, right);
        }
    }
}