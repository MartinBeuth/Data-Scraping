package Kaffeebohnen;

import java.io.*;
import java.util.*;

public class UniqueCustomerSerializer {
    public void serializeUniqueCustomers(List<UniqueCustomer> uniqueCustomers, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(uniqueCustomers);
            objectOut.close();
            fileOut.close();
            System.out.println("Ready.");
        } catch (IOException e) {
            System.err.println("Error serialization: " + e.getMessage());
        }
    }

    public List<UniqueCustomer> deserializeUniqueCustomers(String fileName) {
        List<UniqueCustomer> uniqueCustomers = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            uniqueCustomers = (List<UniqueCustomer>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            System.out.println("Ready.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error deserialization: " + e.getMessage());
        }
        return uniqueCustomers;
    }
}
