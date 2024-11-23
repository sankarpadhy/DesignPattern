package behavioral.template;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class defining the template method pattern for data mining
 * 
 * Key aspects of Template Method Pattern demonstrated here:
 * 1. Defines the skeleton of an algorithm in a method
 * 2. Defers some steps to subclasses
 * 3. Template method is final to prevent overriding
 * 4. Hooks provide optional extension points
 */
public abstract class DataMiner {
    /**
     * Template method that defines the algorithm structure
     * The sequence is fixed but individual steps can be customized
     * @param path Path to the data file
     * @return List of processed data
     */
    public final List<String> mine(String path) {
        File file = openFile(path);
        String rawData = extractData(file);
        List<String> data = parseData(rawData);
        List<String> analyzedData = analyzeData(data);
        sendReport(analyzedData);
        return analyzedData;
    }

    /**
     * Opens a file for reading
     * @param path Path to the file
     * @return File object
     */
    protected abstract File openFile(String path);

    /**
     * Extracts raw data from the file
     * @param file File to extract from
     * @return Raw data string
     */
    protected abstract String extractData(File file);

    /**
     * Parses raw data into structured format
     * @param rawData Raw data to parse
     * @return List of parsed data
     */
    protected abstract List<String> parseData(String rawData);

    /**
     * Analyzes the parsed data
     * Default implementation returns data as is
     * Subclasses can override to provide specific analysis
     * @param data Data to analyze
     * @return Analyzed data
     */
    protected List<String> analyzeData(List<String> data) {
        return new ArrayList<>(data);
    }

    /**
     * Hook method for sending reports
     * Default implementation does nothing
     * Subclasses can override to implement reporting
     * @param data Data to report
     */
    protected void sendReport(List<String> data) {
        // Hook method - default implementation does nothing
    }
}
