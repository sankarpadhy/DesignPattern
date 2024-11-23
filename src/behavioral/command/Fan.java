package behavioral.command;

/**
 * Receiver class for fan operations
 * Demonstrates a more complex receiver with multiple states
 */
public class Fan {
    public static final int OFF = 0;
    public static final int LOW = 1;
    public static final int MEDIUM = 2;
    public static final int HIGH = 3;
    
    private String location;
    private int speed;    // 0 = off, 1 = low, 2 = medium, 3 = high
    private int prevSpeed;

    /**
     * Creates a new Fan with a specific location
     * @param location The location of the fan
     */
    public Fan(String location) {
        this.location = location;
        this.speed = OFF;
        this.prevSpeed = OFF;
    }

    /**
     * Increases the fan speed by one level
     * Cycles through off -> low -> medium -> high
     */
    public void high() {
        prevSpeed = speed;
        speed = HIGH;
        System.out.println(location + " fan is on high");
    }

    /**
     * Sets the fan to medium speed
     */
    public void medium() {
        prevSpeed = speed;
        speed = MEDIUM;
        System.out.println(location + " fan is on medium");
    }

    /**
     * Sets the fan to low speed
     */
    public void low() {
        prevSpeed = speed;
        speed = LOW;
        System.out.println(location + " fan is on low");
    }

    /**
     * Turns the fan off
     */
    public void off() {
        prevSpeed = speed;
        speed = OFF;
        System.out.println(location + " fan is off");
    }

    /**
     * Gets the current speed of the fan
     * @return Current speed (0-3)
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Gets the previous speed before the last change
     * @return Previous speed (0-3)
     */
    public int getPrevSpeed() {
        return prevSpeed;
    }

    /**
     * Gets the location of this fan
     * @return The location string
     */
    public String getLocation() {
        return location;
    }
}
