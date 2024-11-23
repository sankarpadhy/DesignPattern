package behavioral.command;

/**
 * Receiver class in the Command Pattern
 * Represents a light that can be turned on and off
 * 
 * The Receiver knows how to perform the actual operations
 * It contains the business logic for carrying out the command requests
 */
public class Light {
    private String location;
    private boolean isOn;

    /**
     * Creates a new Light with a specific location
     * @param location The location of the light (e.g., "Living Room", "Kitchen")
     */
    public Light(String location) {
        this.location = location;
        this.isOn = false;
    }

    /**
     * Turns the light on
     * This is one of the actions that can be executed by commands
     */
    public void turnOn() {
        isOn = true;
        System.out.println(location + " light is now ON");
    }

    /**
     * Turns the light off
     * This is one of the actions that can be executed by commands
     */
    public void turnOff() {
        isOn = false;
        System.out.println(location + " light is now OFF");
    }

    /**
     * Checks if the light is currently on
     * @return true if the light is on, false otherwise
     */
    public boolean isOn() {
        return isOn;
    }

    /**
     * Gets the location of this light
     * @return The location string
     */
    public String getLocation() {
        return location;
    }
}
