package com.utils;

import com.rental.RentalRecord;
import java.util.List;

// Utility class for generating rental reports
public class ReportGenerator {
    
    // Prints a formatted rental report
    public static void generateRentalReport(List<RentalRecord> records) {
        System.out.println("\n=== Rental Report ===");
        if (records.isEmpty()) {
            System.out.println("No rental records found.");
        } else {
            for (RentalRecord record : records) {
                System.out.println(record); // Uses RentalRecord's toString() formatting
            }
        }
    }
}