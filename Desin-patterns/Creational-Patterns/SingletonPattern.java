// Eager Initialization Singleton
// Object is created when the class is loaded into memory.
public class SingletonPattern {

    // Object is created immediately
    private static SingletonPattern instance = new SingletonPattern();

    // Private constructor prevents object creation using 'new'
    private SingletonPattern() {

    }

    // Returns the already created object
    public static SingletonPattern getInstance() {
        return instance;
    }
}

// ------------------------------------------------------------

// Lazy Initialization Singleton
// Object is created only when it is needed for the first time.
class LazySingleton {

    // Initially no object is created
    private static LazySingleton instance;

    // Private constructor prevents object creation from outside
    private LazySingleton() {

    }

    // Returns the singleton object
    public static LazySingleton getLazySingletonInstance() {

        // Create object only if it does not exist
        if (instance == null) {
            instance = new LazySingleton();
        }

        // Return the existing object
        return instance;
    }
}

// ------------------------------------------------------------

// Synchronized Singleton
// Thread-safe because only one thread can enter the method at a time.
class SynchronizedSingleton {

    private static SynchronizedSingleton instance;

    // Private constructor
    private SynchronizedSingleton() {

    }

    // Synchronized method prevents multiple threads
    // from creating multiple objects simultaneously
    public static synchronized SynchronizedSingleton getSynchronizedSingleton() {

        // Create object if it does not exist
        if (instance == null) {
            instance = new SynchronizedSingleton();
        }

        // Return the existing object
        return instance;
    }
}

// ------------------------------------------------------------

// Double Checked Locking Singleton
// Improves performance by synchronizing only when needed.
class DoubleCheckedLockingSingleton {

    // Volatile ensures visibility of changes across threads
    private static volatile DoubleCheckedLockingSingleton instance;

    // Private constructor
    private DoubleCheckedLockingSingleton() {

    }

    public static DoubleCheckedLockingSingleton getInstance() {

        // First check without locking
        if (instance == null) {

            // Only one thread can enter this block at a time
            synchronized (DoubleCheckedLockingSingleton.class) {

                // Second check to avoid creating multiple objects
                if (instance == null) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }

        // Return the singleton object
        return instance;
    }
}