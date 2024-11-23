package behavioral.chainofresponsibility;

/**
 * Concrete handler for error logging
 */
public class ErrorLogger extends Logger {
    public ErrorLogger(LogLevel level) {
        super(level);
    }

    @Override
    protected void writeMessage(String message) {
        System.out.println("Error Logger: " + message);
        // In a real implementation, this might send emails or notifications
    }
}
