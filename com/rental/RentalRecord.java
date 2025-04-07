package com.rental;

import com.vehicles.Vehicle;

// Represents a rental transaction record linking a vehicle and customer
public class RentalRecord {
    // Unique identifier for this rental record
    private String recordId;
    // Vehicle associated with this rental
    private Vehicle vehicle;
    // ID of the customer who rented the vehicle
    private String customerId;

    // Creates a new rental record with given details
    public RentalRecord(String recordId, Vehicle vehicle, String customerId) {
        this.recordId = recordId;
        this.vehicle = vehicle;
        this.customerId = customerId;
    }

    // Returns a human-readable summary of the rental record
    @Override
    public String toString() {
        return "Record [ID: " + recordId +
               ", Vehicle: " + vehicle.getId() +
               ", Customer: " + customerId + "]";
    }

    // Converts rental data to a CSV-friendly string for storage
    public String toFileString() {
        return recordId + "," + vehicle.getId() + "," + customerId;
    }
}