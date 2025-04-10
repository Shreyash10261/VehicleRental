package com.vehicles;

// Subclass representing a bicycle/motorcycle, extending the Vehicle class
public class Bike extends Vehicle {
    // Type of bike (e.g., mountain, road, electric)
    private String bikeType;

    // Constructor to initialize bike-specific properties along with vehicle details
    public Bike(String id, String brand, double hourlyRate, double dailyRate, String bikeType) {
        super(id, brand, hourlyRate, dailyRate);
        this.bikeType = bikeType;
    }

    // Returns "Bike" as the type identifier
    @Override
    public String getType() { return "Bike"; }

    // Displays bike details in a user-friendly format
    @Override
    public void display() {
        System.out.println(" ID: " + id + 
                         " | Brand: " + brand +
                         " | Type: " + bikeType +
                         " | Hourly: Rupees " + hourlyRate);
    }

    @Override
    protected String getAdditionalFields() {
        return bikeType; // Specific field for Bike
    }
}