package Kaffeebohnen.Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Kaffeebohnen.Orders;

public class OrdersTest {

    private Orders orders;

    @Before
    public void setUp() {
        orders = new Orders();
    }

    @Test
    public void testImportAndDistribution() throws Exception {

        HashMap<String, List<String>> result = orders.importAndDistribution();
        assertNotNull(result);
        assertEquals(1001, result.get("order_id").size());
        assertEquals("Order ID", result.get("order_id").get(0));
        assertEquals("QEV-37451-860", result.get("order_id").get(1));

        assertEquals(1001, result.get("order_date").size());
        assertEquals("Order Date", result.get("order_date").get(0));
        assertEquals("05.09.2019", result.get("order_date").get(1));

        assertEquals(1001, result.get("customer_id").size());
        assertEquals("Customer ID", result.get("customer_id").get(0));
        assertEquals("17670-51384-MA", result.get("customer_id").get(1));

        assertEquals(1001, result.get("product_id").size());
        assertEquals("Product ID", result.get("product_id").get(0));
        assertEquals("R-M-1", result.get("product_id").get(1));

        assertEquals(1001, result.get("quantity").size());
        assertEquals("Quantity", result.get("quantity").get(0));
        assertEquals("2", result.get("quantity").get(1));
    }

}
