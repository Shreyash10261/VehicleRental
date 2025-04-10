package com.interfaces;
/*
 The Rentable interface represents a blueprint for classes that implement this.
 It provides methods to rent a vehicle, return it, and check its availability.
 */
public interface Rentable {
    void rentVehicle(int hours);
    void returnVehicle();
    boolean isAvailable();
}