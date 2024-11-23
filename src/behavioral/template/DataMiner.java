package behavioral.template;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class defining the template method for data mining operations
 */
public abstract class DataMiner {
    
    // Template method
    public final void mine(String path) {
        String rawData = extractData(path);
        List<String> processedData = parseData(rawData);
        List<Object> analyzedData = analyze(processedData);
        sendReport(analyzedData);
        cleanup();
    }
    
    // Abstract methods to be implemented by subclasses
    protected abstract String extractData(String path);
    protected abstract List<String> parseData(String rawData);
    
    // Hook methods with default implementation
    protected List<Object> analyze(List<String> data) {
        List<Object> results = new ArrayList<>();
        System.out.println("Performing standard analysis...");
        for (String item : data) {
            // Default analysis logic
            results.add("Analyzed: " + item);
        }
        return results;
    }
    
    protected void sendReport(List<Object> data) {
        System.out.println("=== Analysis Report ===");
        for (Object item : data) {
            System.out.println(item);
        }
        System.out.println("=====================");
    }
    
    protected void cleanup() {
        System.out.println("Performing standard cleanup...");
    }
}
