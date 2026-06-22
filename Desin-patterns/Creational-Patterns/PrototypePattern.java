/*
 * PROTOTYPE DESIGN PATTERN
 *
 * Problem:
 * --------
 * Suppose Enemy object creation is expensive.
 * Every time creating a new object using:
 *
 * new Enemy("Zombie", 100, "Gun");
 *
 * causes repeated initialization.
 *
 * Solution:
 * ---------
 * Create one object and clone (copy) it whenever needed.
 *
 * Prototype Pattern:
 * ------------------
 * "Create new objects by cloning an existing object
 * instead of creating them from scratch."
 */

class Enemy implements Cloneable {

    String name;
    int health;
    String weapon;

    // Constructor
    Enemy(String name, int health, String weapon) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
    }

    // Prototype Method
    @Override
    protected Enemy clone() throws CloneNotSupportedException {

        // Creates a copy of the current object
        return (Enemy) super.clone();
    }

    void display() {
        System.out.println(
                "Name: " + name +
                        ", Health: " + health +
                        ", Weapon: " + weapon);
    }
}

public class PrototypePattern {

    public static void main(String[] args)
            throws CloneNotSupportedException {

        // Create original object only once
        Enemy originalEnemy = new Enemy("Zombie", 100, "Gun");

        // Create copies using clone()
        Enemy enemy1 = originalEnemy.clone();
        Enemy enemy2 = originalEnemy.clone();
        Enemy enemy3 = originalEnemy.clone();

        // Display all objects
        originalEnemy.display();
        enemy1.display();
        enemy2.display();
        enemy3.display();

        /*
         * Memory Concept:
         *
         * originalEnemy ---> Zombie,100,Gun
         *
         * enemy1 --------> Zombie,100,Gun
         *
         * enemy2 --------> Zombie,100,Gun
         *
         * enemy3 --------> Zombie,100,Gun
         *
         * All are separate objects.
         * Data is copied from original object.
         */
    }
}

/*
 * OUTPUT:
 *
 * Name: Zombie, Health: 100, Weapon: Gun
 * Name: Zombie, Health: 100, Weapon: Gun
 * Name: Zombie, Health: 100, Weapon: Gun
 * Name: Zombie, Health: 100, Weapon: Gun
 *
 * INTERVIEW ONE-LINER:
 *
 * Prototype Pattern =
 * Create Once → Clone Many Times
 */