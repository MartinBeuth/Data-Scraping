package Kaffeebohnen.Tests;

import org.junit.Before;

import Kaffeebohnen.Products;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class ProductsTest {

    private Products products;

    @Before
    public void setUp() {
        products = new Products();
    }

    @Test
    public void testImportAndDistribution() throws Exception {

        HashMap<String, List<String>> result = products.importAndDistribution();
        assertNotNull(result);
        assertEquals(49, result.get("product_id").size());
        assertEquals("Product ID", result.get("product_id").get(0));
        assertEquals("A-L-0.2", result.get("product_id").get(1));

        assertEquals(49, result.get("coffee_type").size());
        assertEquals("Coffee Type", result.get("coffee_type").get(0));
        assertEquals("Ara", result.get("coffee_type").get(1));

        assertEquals(49, result.get("roast_type").size());
        assertEquals("Roast Type", result.get("roast_type").get(0));
        assertEquals("L", result.get("roast_type").get(1));

        assertEquals(49, result.get("size").size());
        assertEquals("Size", result.get("size").get(0));

        assertEquals(49, result.get("unit_price").size());
        assertEquals("Unit Price", result.get("unit_price").get(0));

        assertEquals(49, result.get("price_per_100g").size());
        assertEquals("Price per 100g", result.get("price_per_100g").get(0));

        assertEquals(49, result.get("profit").size());
        assertEquals("Profit", result.get("profit").get(0));

    }

}
