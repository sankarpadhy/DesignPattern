package creational.singleton;

/**
 * Singleton Pattern Example
 * 
 * Purpose:
 * - Ensures a class has only one instance and provides a global point of access to it
 * - Useful for managing shared resources like database connections, thread pools, or configuration settings
 * 
 * Key Points:
 * 1. Private constructor prevents direct instantiation
 * 2. Private static instance ensures only one instance exists
 * 3. Public static method provides access to the instance
 * 
 * Thread Safety:
 * - Uses double-checked locking pattern for thread safety and performance
 * - 'volatile' keyword ensures the instance variable is properly synchronized across threads
 * 
 * Protection Against:
 * - Reflection: Constructor check prevents reflection-based instantiation
 * - Cloning: Override clone() to prevent cloning
 * - Serialization: Can be enhanced with readResolve() if serialization is needed
 */
public class Singleton {
    // volatile keyword ensures that multiple threads handle the instance variable correctly
    // It prevents the situation where one thread might see a partially initialized instance
    private static volatile Singleton instance;
    
    // Private constructor prevents instantiation from other classes
    private Singleton() {
        // Protection against reflection
        // If instance exists and someone tries to use reflection to create another instance,
        // this constructor will throw an exception
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to get the instance.");
        }
    }
    
    /**
     * Gets the singleton instance using double-checked locking pattern.
     * Double-checked locking works by first checking if synchronization is needed
     * before actually acquiring the lock.
     * 
     * Why double-checked locking?
     * 1. First check (if instance == null):
     *    - Prevents unnecessary synchronization once instance is created
     *    - Improves performance as synchronization is expensive
     * 
     * 2. Synchronized block:
     *    - Ensures only one thread can create the instance
     *    - Only executed when instance is null
     * 
     * 3. Second check (if instance == null):
     *    - Prevents multiple instance creation if multiple threads reach the synchronized block
     *    - Essential because another thread might have created the instance while current thread
     *      was waiting to enter synchronized block
     */
    public static Singleton getInstance() {
        // First check (not synchronized)
        if (instance == null) {
            // Synchronize only if instance is null
            synchronized (Singleton.class) {
                // Second check (synchronized)
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
    
    // Prevent cloning of the singleton instance
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning of singleton instance is not allowed");
    }
    
    // Example method to demonstrate singleton usage
    public void showMessage() {
        System.out.println("Hello from Singleton instance! HashCode: " + this.hashCode());
    }
}
