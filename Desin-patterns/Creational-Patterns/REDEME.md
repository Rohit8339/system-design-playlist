# Singleton Design Pattern

## What is Singleton?

Singleton is a Creational Design Pattern that ensures only one instance of a class exists throughout the application and provides a global access point to it.

## Implementations

- Eager Initialization
- Lazy Initialization
- Synchronized Singleton
- Double Checked Locking

## Advantages

- Saves memory
- Ensures consistent state
- Provides global access to a single instance

## Use Cases

- Logger
- Configuration Manager
- Cache Manager
- Database Connection Manager

# Factory Pattern in Java

## Overview

This project demonstrates the Factory Pattern using a simple logistics application.

The goal is to separate object creation logic from business logic.

---

## Problem

Initially, the `LogisticsService` class was responsible for:

1. Creating objects
2. Using those objects

Example:

```java
if(mode.equals("Air")){
    Logistics logistics = new Air();
    logistics.send();
}
else if(mode.equals("Road")){
    Logistics logistics = new Road();
    logistics.send();
}
```

### Issues

- Violates the Single Responsibility Principle (SRP)
- Creates tight coupling between service and concrete classes
- Difficult to maintain
- Every new transport type requires modifying the service class

---

## Solution

A separate Factory class is introduced to handle object creation.

```java
class LogisticsFactory {

    public static Logistics getLogistics(String mode) {

        if(mode.equals("Air")) {
            return new Air();
        }

        if(mode.equals("Road")) {
            return new Road();
        }

        throw new IllegalArgumentException("Invalid transport mode");
    }
}
```

The service class now focuses only on business logic.

```java
class LogisticsServiceAfter {

    public void service(String mode) {

        Logistics logistics =
                LogisticsFactory.getLogistics(mode);

        logistics.send();
    }
}
```

---

## Class Diagram

### Before Factory Pattern

```text
LogisticsService
    ├── Creates Air Object
    ├── Creates Road Object
    └── Uses Objects
```

### After Factory Pattern

```text
LogisticsFactory
    ├── Creates Air Object
    └── Creates Road Object

LogisticsService
    └── Uses Objects
```

---

## Benefits

- Follows Single Responsibility Principle (SRP)
- Reduces tight coupling
- Improves maintainability
- Improves code readability
- Easier to extend with new transport types

---

## Output

```text
Before Factory Pattern:
Sending via Air

After Factory Pattern:
Sending via Road
```

---

## Key Takeaway

The Factory Pattern moves object creation logic into a dedicated factory class.

Instead of creating objects directly using:

```java
new Air();
new Road();
```

the application asks the factory to provide the required object:

```java
Logistics logistics =
        LogisticsFactory.getLogistics(mode);
```

This keeps the service class focused on business logic while the factory handles object creation.

---

-
