package behavioral.chainofresponsibility;

/**
 * Abstract handler in the chain of responsibility pattern
 */
public abstract class Logger {
    protected LogLevel level;
    protected Logger nextLogger;

    public Logger(LogLevel level) {
        this.level = level;
    }

    /**
     * Set the next logger in the chain
     */
    public Logger setNext(Logger nextLogger) {
        this.nextLogger = nextLogger;
        return nextLogger;
    }

    /**
     * Handle the message if appropriate level, otherwise pass to next handler
     */
    public void log(LogLevel messageLevel, String message) {
        if (messageLevel.getLevel() >= level.getLevel()) {
            writeMessage(message);
        }
        if (nextLogger != null) {
            nextLogger.log(messageLevel, message);
        }
    }

    /**
     * Actual logging implementation - to be defined by concrete loggers
     */
    protected abstract void writeMessage(String message);
}
