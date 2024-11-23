package behavioral.chainofresponsibility;

/**
 * Enumeration of logging levels for the Chain of Responsibility pattern
 * Defines different severity levels for logging messages
 * Levels are ordered from least to most severe
 */
public enum LogLevel {
    INFO(1),
    DEBUG(2),
    ERROR(3);

    private final int level;

    /**
     * Constructs a LogLevel with a specific severity value
     * @param level The numeric value representing severity
     */
    LogLevel(int level) {
        this.level = level;
    }

    /**
     * Gets the numeric severity level
     * @return The severity level as an integer
     */
    public int getLevel() {
        return level;
    }
}
