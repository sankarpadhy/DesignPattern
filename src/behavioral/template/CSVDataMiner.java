package behavioral.template;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * Concrete implementation of DataMiner for CSV files
 * Demonstrates how template method steps are implemented for specific file type
 */
public class CSVDataMiner extends DataMiner {
    
    /**
     * Extracts data from CSV file
     * @param path Path to the CSV file
     * @return Raw CSV content
     */
    @Override
    protected String extractData(String path) {
        System.out.println("Extracting data from CSV file: " + path);
        return "CSV Content: Row1, Row2, Row3";
    }
    
    /**
     * Parses CSV content into structured format
     * @param rawData Raw CSV content
     * @return List of parsed CSV rows
     */
    @Override
    protected List<String> parseData(String rawData) {
        System.out.println("Parsing CSV data...");
        return Arrays.asList(rawData.split(","));
    }
    
    /**
     * Provides CSV-specific data analysis
     * @param data Parsed CSV data
     * @return Analyzed data with CSV-specific metrics
     */
    @Override
    protected List<Object> analyze(List<String> data) {
        System.out.println("Performing CSV-specific analysis...");
        List<Object> results = new ArrayList<>();
        for (String row : data) {
            results.add("CSV Analysis - " + row.trim());
        }
        return results;
    }
    
    /**
     * Cleans up CSV resources after processing
     */
    @Override
    protected void cleanup() {
        System.out.println("Cleaning up CSV resources...");
    }
}
