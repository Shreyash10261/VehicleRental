package com.vehicles;

// Subclass representing a Van, extending the Vehicle class
public class Van extends Vehicle {
    // Cargo capacity of the van in tons
    private double cargoCapacity;

    // Constructor to initialize van-specific properties along with vehicle details
    public Van(String id, String brand, double hourlyRate, double dailyRate, double cargoCapacity) {
        super(id, brand, hourlyRate, dailyRate);
        this.cargoCapacity = cargoCapacity;
    }

    // Returns "Van" as the type identifier
    @Override
    public String getType() { return "Van"; }

    // Displays van details in a user-friendly format
    @Override
    public void display() {
        System.out.println(" ID: " + id + 
                         " | Brand: " + brand +
                         " | Cargo: " + cargoCapacity + " tons" +
                         " | Hourly: Rupees " + hourlyRate);
    }

    @Override
    protected String getAdditionalFields() {
        return String.valueOf(cargoCapacity); // Specific field for Van
    }
}