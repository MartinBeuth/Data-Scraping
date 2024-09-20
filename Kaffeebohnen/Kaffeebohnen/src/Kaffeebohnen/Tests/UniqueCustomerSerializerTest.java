package Kaffeebohnen.Tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Kaffeebohnen.UniqueCustomer;
import Kaffeebohnen.UniqueCustomerSerializer;

public class UniqueCustomerSerializerTest {

    private UniqueCustomerSerializer serializer;
    private String testFileName;

    @Before
    public void setUp() {
        serializer = new UniqueCustomerSerializer();
        testFileName = "testUniqueCustomers.ser";
    }

    @After
    public void tearDown() {

        File file = new File(testFileName);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testDeserializeUniqueCustomers() {
        List<UniqueCustomer> uniqueCustomers = new ArrayList<>();
        UniqueCustomer customer1 = new UniqueCustomer();
        customer1.setAllData("AAB1", "Jochen Käfer", "käfer@example.de", "Deutschland", "Arabica");

        uniqueCustomers.add(customer1);

        serializer.serializeUniqueCustomers(uniqueCustomers, testFileName);

        List<UniqueCustomer> deserializedCustomers = serializer.deserializeUniqueCustomers(testFileName);

        UniqueCustomer deserializedCustomer = deserializedCustomers.get(0);

        assertEquals("AAB1", deserializedCustomer.getCustomerID());
        assertEquals("Jochen Käfer", deserializedCustomer.getNames());
        assertEquals("käfer@example.de", deserializedCustomer.getEmail());
        assertEquals("Deutschland", deserializedCustomer.getCountry());
        assertEquals("Arabica", deserializedCustomer.getCoffeeType());
    }

}
