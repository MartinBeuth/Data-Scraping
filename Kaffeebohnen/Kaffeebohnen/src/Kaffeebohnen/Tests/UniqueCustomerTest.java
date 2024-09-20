package Kaffeebohnen.Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Kaffeebohnen.UniqueCustomer;

public class UniqueCustomerTest {

    private UniqueCustomer uniqueCustomer;

    @Before
    public void setUp() {
        uniqueCustomer = new UniqueCustomer();
    }

    @Test
    public void testSetAllData() {
        uniqueCustomer.setAllData("AAB1", "Jochen Käfer", "käfer@example.de", "Deutschland", "Arabica");

        assertEquals("AAB1", uniqueCustomer.getCustomerID());
        assertEquals("Jochen Käfer", uniqueCustomer.getNames());
        assertEquals("käfer@example.de", uniqueCustomer.getEmail());
        assertEquals("Deutschland", uniqueCustomer.getCountry());
        assertEquals("Arabica", uniqueCustomer.getCoffeeType());
    }

}
