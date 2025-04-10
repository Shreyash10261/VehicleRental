package com.rental;

// Class that represents a customer in the vehicle rental system
public class Customer {
    private String id;    // Unique identifier for the customer
    private String name;  // Name of the customer
    private String phone; // Phone number of the customer

    // Constructor to initialize a Customer object
    public Customer(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    // Getter for customer ID
    public String getId() { return id; }

    // Getter for customer name
    public String getName() { return name; }

    // Getter for customer phone number
    public String getPhone() { return phone; }

    // Returns a string representation of the customer object
    @Override
    public String toString() {
        return "Customer [ID: " + id + ", Name: " + name + ", Phone: " + phone + "]";
    }
}