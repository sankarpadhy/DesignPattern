package behavioral.chainofresponsibility;

/**
 * Concrete handler for console logging
 */
public class ConsoleLogger extends Logger {
    public ConsoleLogger(LogLevel level) {
        super(level);
    }

    @Override
    protected void writeMessage(String message) {
        System.out.println("Console Logger: " + message);
    }
}
