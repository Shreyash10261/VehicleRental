package com.vehicles;

// Subclass representing a standard car, extending the Vehicle class
public class Car extends Vehicle {
    // Type of fuel the car uses (e.g., petrol, diesel, electric)
    private String fuelType;

    // Constructor to initialize car-specific properties along with vehicle details
    public Car(String id, String brand, double hourlyRate, double dailyRate, String fuelType) {
        super(id, brand, hourlyRate, dailyRate);
        this.fuelType = fuelType;
    }

    // Returns "Car" as the type identifier
    @Override
    public String getType() { return "Car"; }

    // Displays car details in a user-friendly format
    @Override
    public void display() {
        System.out.println(" ID: " + id + 
                         " | Brand: " + brand +
                         " | Fuel: " + fuelType +
                         " | Hourly: Rupees " + hourlyRate);
    }

    @Override
    protected String getAdditionalFields() {
        return fuelType; // Specific field for Car
    }
}