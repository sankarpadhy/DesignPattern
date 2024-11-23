package behavioral.chainofresponsibility;

/**
 * Abstract base class for the Chain of Responsibility pattern
 * Implements the basic chain functionality and defines the logging contract
 * Each concrete logger will handle specific log levels and pass others to the next handler
 */
public abstract class Logger {
    protected LogLevel level;
    protected Logger nextLogger;

    /**
     * Constructs a logger with a specific log level
     * @param level The minimum level this logger handles
     */
    public Logger(LogLevel level) {
        this.level = level;
    }

    /**
     * Sets the next logger in the chain
     * @param nextLogger The logger to handle messages this logger doesn't process
     * @return The next logger in the chain
     */
    public Logger setNext(Logger nextLogger) {
        this.nextLogger = nextLogger;
        return nextLogger;
    }

    /**
     * Handles the message if appropriate for this logger's level,
     * otherwise passes it to the next logger in the chain
     * @param message The message to be logged
     * @param level The level at which to log the message
     */
    public void logMessage(String message, LogLevel level) {
        if (this.level.ordinal() <= level.ordinal()) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.logMessage(message, level);
        }
    }

    /**
     * Abstract method to be implemented by concrete loggers
     * Defines how the actual logging should be performed
     * @param message The message to be written to the log
     */
    protected abstract void write(String message);
}
