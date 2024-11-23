package behavioral.command;

/**
 * Receiver class for stereo operations
 * Demonstrates a complex receiver with multiple states and operations
 */
public class Stereo {
    private String location;
    private boolean isOn;
    private String source;  // CD, DVD, Radio
    private int volume;     // 0-100
    private double frequency; // For radio

    /**
     * Creates a new Stereo with a specific location
     * @param location The location of the stereo
     */
    public Stereo(String location) {
        this.location = location;
        this.isOn = false;
        this.source = "CD";
        this.volume = 0;
        this.frequency = 87.5; // Default FM frequency
    }

    /**
     * Turns the stereo on
     */
    public void on() {
        isOn = true;
        System.out.println(location + " stereo is ON");
    }

    /**
     * Turns the stereo off
     */
    public void off() {
        isOn = false;
        System.out.println(location + " stereo is OFF");
    }

    /**
     * Sets the stereo to play a CD
     */
    public void setCD() {
        if (isOn) {
            source = "CD";
            System.out.println(location + " stereo is set for CD input");
        }
    }

    /**
     * Sets the stereo to play a DVD
     */
    public void setDVD() {
        if (isOn) {
            source = "DVD";
            System.out.println(location + " stereo is set for DVD input");
        }
    }

    /**
     * Sets the stereo to radio mode
     */
    public void setRadio() {
        if (isOn) {
            source = "Radio";
            System.out.println(location + " stereo is set for Radio input");
            System.out.println("Current frequency: " + frequency + " MHz");
        }
    }

    /**
     * Sets the volume level
     * @param level Volume level (0-100)
     */
    public void setVolume(int level) {
        if (isOn && level >= 0 && level <= 100) {
            volume = level;
            System.out.println(location + " stereo volume set to " + volume);
        }
    }

    /**
     * Sets the radio frequency
     * @param freq Radio frequency in MHz (87.5-108.0)
     */
    public void setFrequency(double freq) {
        if (isOn && source.equals("Radio") && freq >= 87.5 && freq <= 108.0) {
            frequency = freq;
            System.out.println(location + " radio frequency set to " + frequency + " MHz");
        }
    }

    /**
     * Gets the current state of the stereo
     * @return true if the stereo is on
     */
    public boolean isOn() {
        return isOn;
    }

    /**
     * Gets the current source
     * @return Current input source
     */
    public String getSource() {
        return source;
    }

    /**
     * Gets the current volume level
     * @return Volume level (0-100)
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Gets the current radio frequency
     * @return Radio frequency in MHz
     */
    public double getFrequency() {
        return frequency;
    }

    /**
     * Gets the location of this stereo
     * @return The location string
     */
    public String getLocation() {
        return location;
    }
}
