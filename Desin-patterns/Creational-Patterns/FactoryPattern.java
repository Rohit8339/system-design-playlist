// ============================
// PROBLEM CODE
// ============================

// Common interface
interface Logistics {
    void send();
}

// Air transport implementation
class Air implements Logistics {

    @Override
    public void send() {
        System.out.println("Sending via Air");
    }
}

// Road transport implementation
class Road implements Logistics {

    @Override
    public void send() {
        System.out.println("Sending via Road");
    }
}

// PROBLEM:
// This class is responsible for both
// 1. Creating objects
// 2. Using objects
//
// This violates the Single Responsibility Principle (SRP).
class LogisticsServiceBefore {

    public void service(String mode) {

        // Object creation logic
        if (mode.equals("Air")) {
            Logistics logistics = new Air();
            logistics.send();
        }

        else if (mode.equals("Road")) {
            Logistics logistics = new Road();
            logistics.send();
        }
    }
}

// ============================
// SOLUTION CODE (FACTORY PATTERN)
// ============================

// Factory class is responsible only for object creation
class LogisticsFactory {

    public static Logistics getLogistics(String mode) {

        // Create Air object
        if (mode.equals("Air")) {
            return new Air();
        }

        // Create Road object
        if (mode.equals("Road")) {
            return new Road();
        }

        throw new IllegalArgumentException("Invalid transport mode");
    }
}

// Service class is responsible only for business logic
class LogisticsServiceAfter {

    public void service(String mode) {

        // Ask factory to create the object
        Logistics logistics = LogisticsFactory.getLogistics(mode);

        // Use the object
        logistics.send();
    }
}

// ============================
// MAIN METHOD
// ============================

public class Factory {

    public static void main(String[] args) {

        System.out.println("Before Factory Pattern:");
        LogisticsServiceBefore before = new LogisticsServiceBefore();
        before.service("Air");

        System.out.println();

        System.out.println("After Factory Pattern:");
        LogisticsServiceAfter after = new LogisticsServiceAfter();
        after.service("Road");
    }
}

/*
 * OUTPUT
 * 
 * Before Factory Pattern:
 * Sending via Air
 * 
 * After Factory Pattern:
 * Sending via Road
 */

/*
 * KEY TAKEAWAY
 * 
 * Before Factory Pattern
 * 
 * LogisticsService
 * -> Creates Object
 * -> Uses Object
 * 
 * After Factory Pattern
 * 
 * LogisticsFactory
 * -> Creates Object
 * 
 * LogisticsService
 * -> Uses Object
 * 
 * Benefits:
 * 1. Follows Single Responsibility Principle (SRP)
 * 2. Reduces tight coupling
 * 3. Easy to maintain
 * 4. Easy to add new transport types like Sea or Rail
 */