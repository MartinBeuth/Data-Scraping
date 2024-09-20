package Kaffeebohnen.Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Kaffeebohnen.Customers;

public class CustomersTest {

    private Customers customers;

    @Before
    public void setUp() {
        customers = new Customers();
    }

    @Test
    public void testImportAndDistribution() throws IOException {

        HashMap<String, List<String>> result = customers.importAndDistribution();

        assertNotNull(result);
        assertEquals(1001, result.get("customer_id").size());
        assertEquals("Customer ID", result.get("customer_id").get(0));
        assertEquals("17670-51384-MA", result.get("customer_id").get(1));

        assertEquals(1001, result.get("customer_name").size());
        assertEquals("Customer Name", result.get("customer_name").get(0));
        assertEquals("Aloisia Allner", result.get("customer_name").get(1));

        assertEquals(1001, result.get("email").size());
        assertEquals("Email", result.get("email").get(0));
        assertEquals("aallner0@lulu.com", result.get("email").get(1));

        assertEquals(1001, result.get("phone_number").size());
        assertEquals("Phone Number", result.get("phone_number").get(0));
        assertEquals("+1 (862) 817-0124", result.get("phone_number").get(1));

    }

}
