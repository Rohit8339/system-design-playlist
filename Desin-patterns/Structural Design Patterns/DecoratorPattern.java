// =====================================================
// PROBLEM CODE (WITHOUT DECORATOR PATTERN)
// =====================================================

/*
Problem:

Suppose a coffee shop offers:

1. Basic Coffee
2. Coffee + Milk
3. Coffee + Sugar
4. Coffee + Milk + Sugar

Without Decorator Pattern, we may end up creating
a separate class for every combination.
*/

interface Coffee {
    int cost();
}

// Basic Coffee
class BasicCoffee implements Coffee {

    @Override
    public int cost() {
        return 100;
    }
}

// Coffee with Milk
class MilkCoffee implements Coffee {

    @Override
    public int cost() {
        return 110;
    }
}

// Coffee with Sugar
class SugarCoffee implements Coffee {

    @Override
    public int cost() {
        return 120;
    }
}

// Coffee with Milk and Sugar
class MilkSugarCoffee implements Coffee {

    @Override
    public int cost() {
        return 130;
    }
}

/*
 * Problem:
 * 
 * If tomorrow we add:
 * 
 * 1. Chocolate
 * 2. Caramel
 * 3. Cream
 * 
 * The number of classes keeps increasing.
 * 
 * MilkCoffee
 * SugarCoffee
 * MilkSugarCoffee
 * ChocolateCoffee
 * MilkChocolateCoffee
 * SugarChocolateCoffee
 * MilkSugarChocolateCoffee
 * ...
 * 
 * This is difficult to maintain.
 */

// =====================================================
// SOLUTION CODE (DECORATOR PATTERN)
// =====================================================

/*
 * Solution:
 * 
 * Instead of creating a new class for every combination,
 * we add features dynamically using decorators.
 * 
 * Each decorator wraps an existing coffee object
 * and adds its own cost.
 */

// Base decorator class
abstract class CoffeeDecorator implements Coffee {

    // Reference to the object being decorated
    protected Coffee coffee;

    CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
}

// Milk decorator
class MilkDecorator extends CoffeeDecorator {

    MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int cost() {

        // Add milk cost
        return coffee.cost() + 10;
    }
}

// Sugar decorator
class SugarDecorator extends CoffeeDecorator {

    SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int cost() {

        // Add sugar cost
        return coffee.cost() + 20;
    }
}

public class DecoratorPattern {

    public static void main(String[] args) {

        // Create basic coffee
        Coffee coffee = new BasicCoffee();

        // Add milk
        coffee = new MilkDecorator(coffee);

        // Add sugar
        coffee = new SugarDecorator(coffee);

        // Final cost
        System.out.println(coffee.cost());
    }
}

/*
 * OUTPUT
 * 
 * 130
 * 
 * 
 * FLOW
 * 
 * BasicCoffee
 * Cost = 100
 * |
 * v
 * MilkDecorator
 * Cost = 100 + 10
 * |
 * v
 * SugarDecorator
 * Cost = 110 + 20
 * |
 * v
 * Final Cost = 130
 * 
 * 
 * CLASS DIAGRAM
 * 
 * Coffee
 * ^
 * |
 * +---------+---------+
 * | |
 * BasicCoffee CoffeeDecorator
 * ^
 * |
 * +------------+------------+
 * | |
 * MilkDecorator SugarDecorator
 * 
 * 
 * BENEFITS
 * 
 * 1. No class explosion
 * 2. Add features dynamically
 * 3. Follows Open/Closed Principle
 * 4. Easy to extend with new decorators
 */