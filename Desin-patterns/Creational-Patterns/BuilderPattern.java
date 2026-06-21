import java.util.List;

// =====================================================
// PROBLEM CODE (WITHOUT BUILDER PATTERN)
// =====================================================

/*
Problem:

As the number of fields increases, the constructor
becomes larger and harder to read.

It is also difficult to remember the order of parameters.

Example:

Pizza pizza = new Pizza(
        "Wheat Bun",
        "Veg Patty",
        "Fries",
        List.of("Olives", "Corn"),
        true
);

Looking at the code, it is not immediately clear
what each value represents.
*/

class PizzaWithoutBuilder {

    private String bun;
    private String patty;
    private String sides;
    private List<String> toppings;
    private boolean cheese;

    public PizzaWithoutBuilder(String bun,
            String patty,
            String sides,
            List<String> toppings,
            boolean cheese) {

        this.bun = bun;
        this.patty = patty;
        this.sides = sides;
        this.toppings = toppings;
        this.cheese = cheese;
    }
}

// =====================================================
// SOLUTION CODE (BUILDER PATTERN)
// =====================================================

/*
 * Solution:
 * 
 * Instead of passing everything through a large constructor,
 * we create the object step by step using a Builder.
 * 
 * Benefits:
 * 1. More readable
 * 2. Easy to maintain
 * 3. Easy to add optional fields
 * 4. No need to remember constructor parameter order
 */

class Pizza {

    private String bun;
    private String patty;
    private String sides;
    private List<String> toppings;
    private boolean cheese;

    // Constructor receives Builder object
    private Pizza(PizzaBuilder builder) {

        this.bun = builder.bun;
        this.patty = builder.patty;
        this.sides = builder.sides;
        this.toppings = builder.toppings;
        this.cheese = builder.cheese;
    }

    // Print pizza details
    public void display() {

        System.out.println("Bun : " + bun);
        System.out.println("Patty : " + patty);
        System.out.println("Sides : " + sides);
        System.out.println("Toppings : " + toppings);
        System.out.println("Cheese : " + cheese);
    }

    // Builder class used to create Pizza object
    static class PizzaBuilder {

        private String bun;
        private String patty;
        private String sides;
        private List<String> toppings;
        private boolean cheese;

        // Set bun and return Builder object
        public PizzaBuilder setBun(String bun) {
            this.bun = bun;
            return this;
        }

        // Set patty and return Builder object
        public PizzaBuilder setPatty(String patty) {
            this.patty = patty;
            return this;
        }

        // Set sides and return Builder object
        public PizzaBuilder setSides(String sides) {
            this.sides = sides;
            return this;
        }

        // Set toppings and return Builder object
        public PizzaBuilder setToppings(List<String> toppings) {
            this.toppings = toppings;
            return this;
        }

        // Set cheese and return Builder object
        public PizzaBuilder setCheese(boolean cheese) {
            this.cheese = cheese;
            return this;
        }

        // Create final Pizza object
        public Pizza build() {
            return new Pizza(this);
        }
    }
}

// =====================================================
// MAIN METHOD
// =====================================================

public class BuilderPattern {

    public static void main(String[] args) {

        Pizza pizza = new Pizza.PizzaBuilder()
                .setBun("Wheat Bun")
                .setPatty("Veg Patty")
                .setSides("Fries")
                .setToppings(List.of("Olives", "Corn"))
                .setCheese(true)
                .build();

        pizza.display();
    }
}

/*
 * OUTPUT
 * 
 * Bun : Wheat Bun
 * Patty : Veg Patty
 * Sides : Fries
 * Toppings : [Olives, Corn]
 * Cheese : true
 * 
 * 
 * FLOW
 * 
 * Without Builder
 * 
 * Client
 * |
 * v
 * Large Constructor
 * |
 * v
 * Pizza Object
 * 
 * 
 * With Builder
 * 
 * Client
 * |
 * v
 * PizzaBuilder
 * |
 * | setBun()
 * | setPatty()
 * | setSides()
 * | setToppings()
 * | setCheese()
 * |
 * v
 * build()
 * |
 * v
 * Pizza Object
 */