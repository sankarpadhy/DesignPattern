package behavioral.chainofresponsibility;

/**
 * Demonstration of Chain of Responsibility Pattern
 */
public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        // Create the chain of loggers
        Logger consoleLogger = new ConsoleLogger(LogLevel.INFO);
        Logger fileLogger = new FileLogger(LogLevel.DEBUG);
        Logger errorLogger = new ErrorLogger(LogLevel.ERROR);

        // Set up the chain
        consoleLogger.setNext(fileLogger).setNext(errorLogger);

        // Test the chain with different log levels
        System.out.println("Logging INFO level message:");
        consoleLogger.log(LogLevel.INFO, "This is an information message");
        
        System.out.println("\nLogging DEBUG level message:");
        consoleLogger.log(LogLevel.DEBUG, "This is a debug message");
        
        System.out.println("\nLogging ERROR level message:");
        consoleLogger.log(LogLevel.ERROR, "This is an error message");
    }
}
