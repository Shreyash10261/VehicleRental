package com.rental;
import com.vehicles.*;
import com.utils.InputValidator;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Main system for managing the entire program 
public class VehicleRentalSystem {
    // Files for storing the application data
    private static final String VEHICLES_FILE = "vehicles.txt";
    private static final String CUSTOMERS_FILE = "customers.txt";
    private static final String RECORDS_FILE = "records.txt";
    
    // Array list to store data of vehicles, customers, and rental records
    private static List<Vehicle> vehicles = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<RentalRecord> records = new ArrayList<>();

    public static void main(String[] args) {
        loadVehicles();    // Load saved vehicle data
        loadCustomers();   // Load saved customer data
        loadRecords();     // Load saved rental records
        showMainMenu();    // Display main interface
        saveVehicles();    // Save changes to vehicle data
        saveCustomers();   // Save changes to customer data
        saveRecords();     // Save changes to rental records
    }

    // Load vehicle data from text file at startup
    private static void loadVehicles() {
        try (BufferedReader reader = new BufferedReader(new FileReader(VEHICLES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Trim the line to remove leading/trailing spaces
                line = line.trim();

                // Skip empty lines
                if (line.isEmpty()) {
                    System.out.println("[ERROR] Skipping empty line in vehicles file.");
                    continue;
                }

                String[] parts = line.split(",");
                // Validate the number of fields
                if (parts.length < 7) { // Expecting at least 7 fields
                    System.out.println("[ERROR] Invalid line in vehicles file: " + line);
                    continue; // Skip this line
                }

                String type = parts[0];
                switch (type) {
                    case "Car":
                        vehicles.add(new Car(
                            parts[1], // ID
                            parts[2], // Brand
                            Double.parseDouble(parts[3]), // Hourly Rate
                            Double.parseDouble(parts[4]), // Daily Rate
                            parts[5] // Fuel Type
                        ));
                        break;
                    case "Bike":
                        vehicles.add(new Bike(
                            parts[1], // ID
                            parts[2], // Brand
                            Double.parseDouble(parts[3]), // Hourly Rate
                            Double.parseDouble(parts[4]), // Daily Rate
                            parts[5] // Type
                        ));
                        break;
                    case "Van":
                        vehicles.add(new Van(
                            parts[1], // ID
                            parts[2], // Brand
                            Double.parseDouble(parts[3]), // Hourly Rate
                            Double.parseDouble(parts[4]), // Daily Rate
                            Double.parseDouble(parts[5]) // Cargo Capacity
                        ));
                        break;
                    case "TravelerCar":
                        vehicles.add(new TravelerCar(
                            parts[1], // ID
                            parts[2], // Brand
                            Double.parseDouble(parts[3]), // Hourly Rate
                            Double.parseDouble(parts[4]), // Daily Rate
                            Integer.parseInt(parts[5]) // Passenger Capacity
                        ));
                        break;
                    default:
                        System.out.println("[ERROR] Unknown vehicle type: " + type);
                        continue; // Skip this line
                }

                // Set the isRented field
                Vehicle lastVehicle = vehicles.get(vehicles.size() - 1);
                lastVehicle.isRented = Boolean.parseBoolean(parts[6]); // Parse isRented as boolean
            }
        } catch (IOException e) {
            System.out.println("[INFO] No vehicles file found. Starting fresh.");
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid data format in vehicles file.");
        }
    }

    // Save vehicle data to text file on exit
    private static void saveVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("[WARNING] No vehicles to save. The vehicles.txt file will be empty.");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VEHICLES_FILE))) {
            for (Vehicle v : vehicles) {
                writer.write(v.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("[ERROR] Failed to save vehicles.");
        }
    }

    // Load customer data from text file at startup
    private static void loadCustomers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Trim the line to remove leading/trailing spaces
                line = line.trim();

                // Skip empty lines
                if (line.isEmpty()) {
                    System.out.println("[ERROR] Skipping empty line in customers file.");
                    continue;
                }

                String[] parts = line.split(",");
                // Validate the number of fields
                if (parts.length < 3) { // Expecting at least 3 fields: ID, Name, Phone
                    System.out.println("[ERROR] Invalid line in customers file: " + line);
                    continue; // Skip this line
                }

                // Add the customer to the list
                customers.add(new Customer(parts[0], parts[1], parts[2]));
            }
        } catch (IOException e) {
            System.out.println("[INFO] No customers file found. Starting fresh.");
        }
    }

    // Save customer data to text file on exit
    private static void saveCustomers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTOMERS_FILE))) {
            for (Customer c : customers) {
                writer.write(c.getId() + "," + c.getName() + "," + c.getPhone());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("[ERROR] Failed to save customers.");
        }
    }

    // Load rental records from text file at startup
    private static void loadRecords() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RECORDS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Vehicle vehicle = findVehicle(parts[1]);
                if (vehicle != null) {
                    records.add(new RentalRecord(parts[0], vehicle, parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("[INFO] No records file found. Starting fresh.");
        }
    }

    // Save rental records to text file on exit
    private static void saveRecords() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECORDS_FILE))) {
            for (RentalRecord record : records) {
                writer.write(record.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("[ERROR] Failed to save records.");
        }
    }

    // Display main menu and handle user choices
    private static void showMainMenu() {
        while (true) {
            System.out.println("\nWelcome to Vehicle Venture Zone!");
            System.out.println("1. Register Customer");
            System.out.println("2. Rent a Car");
            System.out.println("3. Rent a Bike");
            System.out.println("4. Rent a Van");
            System.out.println("5. Rent a Traveler Car");
            System.out.println("6. Return Vehicle");
            System.out.println("7. View Available Vehicles");
            System.out.println("8. View Rental Records");
            System.out.println("9. Exit");
            int choice = InputValidator.getIntInput("Enter choice (1-9)");
            switch (choice) {
                case 1: registerCustomer(); break;
                case 2: rentVehicle("Car"); break;
                case 3: rentVehicle("Bike"); break;
                case 4: rentVehicle("Van"); break;
                case 5: rentVehicle("TravelerCar"); break;
                case 6: returnVehicle(); break;
                case 7: showAvailableVehicles(); break;
                case 8: showRentalRecords(); break;
                case 9: return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // Handle customer registration process
    private static void registerCustomer() {
        String id = InputValidator.getStringInput("Enter Customer ID");
        if (findCustomer(id) != null) {
            System.out.println("[ERROR] Customer already exists!");
            return;
        }
        String name = InputValidator.getStringInput("Enter Name");
        String phone = InputValidator.getStringInput("Enter Phone");
        customers.add(new Customer(id, name, phone));
        System.out.println("[SUCCESS] Customer registered successfully!");
    }

    // Handle vehicle rental process for specified type
    private static void rentVehicle(String type) {
        if (customers.isEmpty()) {
            System.out.println("[ERROR] No registered customers! Please register first.");
            return;
        }
        // Show available vehicles of the selected type
        System.out.println("\nAvailable " + type + "s:");
        boolean hasAvailableVehicles = false;
        for (Vehicle v : vehicles) {
            if (!v.isRented() && v.getType().equals(type)) {
                v.display();
                hasAvailableVehicles = true;
            }
        }
        if (!hasAvailableVehicles) {
            System.out.println("[INFO] No available " + type + "s at the moment.");
            return;
        }
        // Collect rental details
        String vehicleId = InputValidator.getStringInput("Enter Vehicle ID");
        Vehicle selected = findVehicle(vehicleId);
        if (selected == null || !selected.getType().equals(type) || selected.isRented()) {
            System.out.println("[ERROR] Vehicle unavailable!");
            return;
        }
        String customerId = InputValidator.getStringInput("Enter Customer ID");
        Customer customer = findCustomer(customerId);
        if (customer == null) {
            System.out.println("[ERROR] Customer not found! Please register first.");
            return;
        }
        int hours = InputValidator.getIntInput("Enter rental hours");
        selected.rent(hours);
        records.add(new RentalRecord("REC" + (records.size() + 1), selected, customerId));
        System.out.println("[SUCCESS] Vehicle rented successfully!");
    }

    // Handle vehicle return process
    private static void returnVehicle() {
        String vehicleId = InputValidator.getStringInput("Enter Vehicle ID");
        Vehicle selected = findVehicle(vehicleId);
        if (selected != null && selected.isRented()) {
            selected.returnVehicle();
            System.out.println("[SUCCESS] Vehicle returned!");
        } else {
            System.out.println("[ERROR] Vehicle not found or not rented!");
        }
    }

    // Display all available vehicles
    private static void showAvailableVehicles() {
        System.out.println("\nAvailable Vehicles:");
        for (Vehicle v : vehicles) {
            if (!v.isRented()) v.display();
        }
    }

    // Display all rental records
    private static void showRentalRecords() {
        System.out.println("\nRental Records:");
        if (records.isEmpty()) {
            System.out.println("No rental records found.");
        } else {
            for (RentalRecord record : records) {
                System.out.println(record);
            }
        }
    }

    // Find vehicle by ID in the list
    private static Vehicle findVehicle(String id) {
        for (Vehicle v : vehicles) {
            if (v.getId().equals(id)) return v;
        }
        return null;
    }

    // Find customer by ID in the list
    private static Customer findCustomer(String id) {
        for (Customer c : customers) {
            if (c.getId().equals(id)) return c;
        }
        return null;
    }
}