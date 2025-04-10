package com.vehicles;

// Base abstract class representing a vehicle
public abstract class Vehicle {
    // Unique identifier for the vehicle
    protected String id;
    // Brand name of the vehicle
    protected String brand;
    // Cost per hour for renting
    protected double hourlyRate;
    // Cost per day for renting
    protected double dailyRate;
    // Tracks if the vehicle is currently rented
    public boolean isRented;

    // Constructor to initialize vehicle details
    public Vehicle(String id, String brand, double hourlyRate, double dailyRate) {
        this.id = id;
        this.brand = brand;
        this.hourlyRate = hourlyRate;
        this.dailyRate = dailyRate;
        this.isRented = false; // Starts as available
    }

    // Method to rent the vehicle for a given number of hours
    public void rent(int hours) {
        if (!isRented) {
            isRented = true;
            System.out.println(getType() + " " + id + " rented for Rupees " + calculateCost(hours));
        } else {
            System.out.println(getType() + " " + id + " is already rented.");
        }
    }

    // Method to return the vehicle after renting
    public void returnVehicle() {
        if (isRented) {
            isRented = false;
            System.out.println(getType() + " " + id + " returned successfully.");
        } else {
            System.out.println(getType() + " " + id + " was not rented.");
        }
    }

    // Calculates rental cost based on hours (uses daily rate if >=24 hours)
    public double calculateCost(int hours) {
        return hours >= 24 ? (hours / 24) * dailyRate : hours * hourlyRate;
    }

    // Checks if the vehicle is currently rented
    public boolean isRented() { return isRented; }

    // Returns the vehicle's unique ID
    public String getId() { return id; }

    // Abstract method to get vehicle type (e.g., "Car", "Bike")
    public abstract String getType();

    // Abstract method to display vehicle details
    public abstract void display();

    // Converts vehicle data to a string format for saving to file
    public String toFileString() {
        return getType() + "," + id + "," + brand + "," + hourlyRate + "," + dailyRate + "," + getAdditionalFields() + "," + (isRented ? "true" : "false");
    }

    // Abstract method to get additional fields specific to each vehicle type
    protected abstract String getAdditionalFields();
}