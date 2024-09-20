package Kaffeebohnen.Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Kaffeebohnen.Perform;

public class PerformTest {

    private Perform perform;

    @Before
    public void setUp() {
        perform = new Perform();
    }

    @After
    public void tearDown() {

        new File("test_serialized_customers.ser").delete();
        new File("test_output.csv").delete();
        new File("output.html").delete();
    }

    @Test
    public void testPerformance() throws Exception {
        String serialFile = "test_serialized_customers.ser";
        String csvFile = "test_output.csv";

        perform.performance(serialFile, csvFile);

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line1 = reader.readLine();

            assertEquals("Customer ID, Customer Name, Email, Country, Coffee Type", line1);

        } catch (IOException e) {
            fail("CSV file not found or could not be read.");
        }

        File htmlFile = new File("output.html");
        assertTrue(htmlFile.exists());
    }
}
