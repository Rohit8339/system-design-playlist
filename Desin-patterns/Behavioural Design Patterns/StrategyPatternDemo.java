/*
=========================================================
STRATEGY DESIGN PATTERN
Real World Example: Google Maps Route Calculation
=========================================================

PROBLEM:
Without Strategy Pattern, every new route type requires
modifying the GoogleMaps class.

This violates Open/Closed Principle because the class
must be changed whenever a new route is added.
*/

// ==================== PROBLEM CODE ====================

class GoogleMapsOld {

    public void findRoute(String type) {

        if (type.equals("Bike")) {
            System.out.println("Calculating Bike Route");
        } else if (type.equals("Car")) {
            System.out.println("Calculating Car Route");
        } else if (type.equals("Train")) {
            System.out.println("Calculating Train Route");
        } else {
            System.out.println("Calculating Walk Route");
        }
    }
}

/*
 * Problems:
 * 1. Too many if-else conditions.
 * 2. Difficult to maintain.
 * 3. Every new route requires modifying GoogleMapsOld.
 * 4. Not scalable.
 */

// ==================== SOLUTION CODE ====================

/*
 * Step 1:
 * Create a common strategy interface.
 * All route algorithms will implement this interface.
 */
interface RouteStrategy {
    void buildRoute();
}

/*
 * Step 2:
 * Create separate classes for each route algorithm.
 */

// Bike Route Strategy
class BikeRoute implements RouteStrategy {

    public void buildRoute() {
        System.out.println("Calculating Bike Route");
    }
}

// Car Route Strategy
class CarRoute implements RouteStrategy {

    public void buildRoute() {
        System.out.println("Calculating Car Route");
    }
}

// Train Route Strategy
class TrainRoute implements RouteStrategy {

    public void buildRoute() {
        System.out.println("Calculating Train Route");
    }
}

// Walk Route Strategy
class WalkRoute implements RouteStrategy {

    public void buildRoute() {
        System.out.println("Calculating Walk Route");
    }
}

/*
 * Step 3:
 * GoogleMaps only knows about RouteStrategy.
 * It doesn't care whether it is Bike, Car, Train, etc.
 */
class GoogleMaps {

    private RouteStrategy strategy;

    GoogleMaps(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    // Change strategy at runtime
    public void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    public void findRoute() {
        strategy.buildRoute();
    }
}

/*
 * Step 4:
 * Client decides which strategy to use.
 */
public class StrategyPatternDemo {

    public static void main(String[] args) {

        // User selected Bike
        GoogleMaps maps = new GoogleMaps(new BikeRoute());
        maps.findRoute();

        // User changed to Car
        maps.setStrategy(new CarRoute());
        maps.findRoute();

        // User changed to Train
        maps.setStrategy(new TrainRoute());
        maps.findRoute();

        // User changed to Walk
        maps.setStrategy(new WalkRoute());
        maps.findRoute();
    }
}

/*
 * =========================================================
 * OUTPUT
 * =========================================================
 * 
 * Calculating Bike Route
 * Calculating Car Route
 * Calculating Train Route
 * Calculating Walk Route
 * 
 * =========================================================
 * PATTERN FLOW
 * =========================================================
 * 
 * User Selects Route
 * |
 * v
 * GoogleMaps
 * |
 * v
 * RouteStrategy
 * |
 * -------------------------
 * | | | |
 * Bike Car Train Walk
 * 
 * =========================================================
 * KEY IDEA
 * =========================================================
 * 
 * Factory Pattern:
 * -> Selects WHICH OBJECT to create.
 * 
 * Strategy Pattern:
 * -> Selects WHICH ALGORITHM / BEHAVIOR to execute.
 * 
 * Google Maps object remains the same.
 * Only route calculation strategy changes.
 * 
 * =========================================================
 * AT SCALE
 * =========================================================
 * 
 * Google Maps -> Car/Bike/Walk Route Strategies
 * Uber -> Normal/Premium Fare Strategies
 * Paytm -> UPI/Card/Wallet Payment Strategies
 * Netflix -> 480p/720p/1080p Streaming Strategies
 * 
 * =========================================================
 */