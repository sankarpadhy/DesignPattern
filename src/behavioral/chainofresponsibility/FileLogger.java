package behavioral.chainofresponsibility;

/**
 * Concrete handler for file logging
 */
public class FileLogger extends Logger {
    public FileLogger(LogLevel level) {
        super(level);
    }

    @Override
    protected void writeMessage(String message) {
        System.out.println("File Logger: " + message);
        // In a real implementation, this would write to a file
    }
}
