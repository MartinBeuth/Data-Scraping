package Kaffeebohnen.Tests;

import org.junit.Before;
import org.junit.Test;

import Kaffeebohnen.OrdersToolbox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;

public class OrdersToolboxTest {

    private OrdersToolbox ordersToolbox;
    private HashMap<String, List<String>> customerData;
    private HashMap<String, List<String>> orderData;

    @Before
    public void setUp() {
        ordersToolbox = new OrdersToolbox();

        customerData = new HashMap<>();
        customerData.put("customer_id", List.of("K001", "K002", "K003"));
        customerData.put("customer_name", List.of("Hans Müller", "Anna Schmidt", "Alice Schneider"));
        customerData.put("email", List.of("hans@example.com", "anna@example.com", ""));
        customerData.put("country", List.of("Deutschland", "Österreich", ""));

        orderData = new HashMap<>();
        orderData.put("order_id", List.of("B001", "B002"));
        orderData.put("customer_id", List.of("K001", "K003"));
    }

    @Test
    public void testGetMatchingCustomerName() {
        List<String> matchingNames = ordersToolbox.getMatchingCustomerName(customerData, orderData);

        assertNotNull(matchingNames);
        assertEquals(2, matchingNames.size());
        assertEquals("Hans Müller", matchingNames.get(0));
        assertEquals("Alice Schneider", matchingNames.get(1));
    }

    @Test
    public void testGetMatchingEmail() {
        List<String> matchingEmails = ordersToolbox.getMatchingEmail(customerData, orderData);

        assertNotNull(matchingEmails);
        assertEquals(2, matchingEmails.size());
        assertEquals("hans@example.com", matchingEmails.get(0));
        assertEquals("not available", matchingEmails.get(1));
    }

    @Test
    public void testGetMatchingCountry() {
        List<String> matchingCountries = ordersToolbox.getMatchingCountry(customerData, orderData);

        assertNotNull(matchingCountries);
        assertEquals(2, matchingCountries.size());
        assertEquals("Deutschland", matchingCountries.get(0));
        assertEquals("not available", matchingCountries.get(1));
    }

    @Test
    public void testGetMatchingCoffeeTypes() {

        HashMap<String, List<String>> coffeeTypeData1 = new HashMap<>();
        coffeeTypeData1.put("A001", List.of("Arabica"));

        HashMap<String, List<String>> coffeeTypeData2 = new HashMap<>();
        coffeeTypeData2.put("A001", List.of("Arabica"));
        coffeeTypeData2.put("R001", List.of("Robusta"));

        List<String> matchingCoffeeTypes = ordersToolbox.getMatchingCoffeeTypes(coffeeTypeData1, coffeeTypeData2);

        assertNotNull(matchingCoffeeTypes);
        assertEquals(2, matchingCoffeeTypes.size());
        assertEquals("Ara", matchingCoffeeTypes.get(0));

    }
}
