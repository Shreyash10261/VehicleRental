package com.vehicles;

// Subclass representing a passenger car, extending the Vehicle class
public class TravelerCar extends Vehicle {
    // Maximum number of passengers the car can carry
    private int passengerCapacity;

    // Constructor to initialize car-specific properties along with vehicle details
    public TravelerCar(String id, String brand, double hourlyRate, double dailyRate, int passengerCapacity) {
        super(id, brand, hourlyRate, dailyRate);
        this.passengerCapacity = passengerCapacity;
    }

    // Returns "TravelerCar" as the type identifier
    @Override
    public String getType() { return "TravelerCar"; }

    // Displays car details in a user-friendly format
    @Override
    public void display() {
        System.out.println(" ID: " + id + 
                         " | Brand: " + brand +
                         " | Seats: " + passengerCapacity +
                         " | Hourly: Rupees " + hourlyRate);
    }

    @Override
    protected String getAdditionalFields() {
        return String.valueOf(passengerCapacity); // Specific field for TravelerCar
    }
}