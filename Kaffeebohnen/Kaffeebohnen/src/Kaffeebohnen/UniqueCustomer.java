package Kaffeebohnen;

import java.io.Serializable;

public class UniqueCustomer implements Serializable {
    private static final long serialVersionUID = 1L;
    private String customerID;
    private String names;
    private String email;
    private String country;
    private String coffeeType;
    private int id;

    public void setAllData(String customerID, String name, String email, String country, String coffeeType) {
        this.customerID = customerID;
        this.names = name;
        this.email = email;
        this.country = country;
        this.coffeeType = coffeeType;
    }

    public void setAllData2(int id, String customerID, String name, String email, String country,
            String coffeeType) {
        this.id = id;
        this.customerID = customerID;
        this.names = name;
        this.email = email;
        this.country = country;
        this.coffeeType = coffeeType;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(String coffeeType) {
        this.coffeeType = coffeeType;
    }

    public void setID(int id) {
        this.id = id;
    }

}
