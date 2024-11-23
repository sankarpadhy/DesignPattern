package behavioral.template;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class CSVDataMiner extends DataMiner {
    
    @Override
    protected String extractData(String path) {
        System.out.println("Extracting data from CSV file: " + path);
        return "CSV Content: Row1, Row2, Row3";
    }
    
    @Override
    protected List<String> parseData(String rawData) {
        System.out.println("Parsing CSV data...");
        return Arrays.asList(rawData.split(","));
    }
    
    @Override
    protected List<Object> analyze(List<String> data) {
        System.out.println("Performing CSV-specific analysis...");
        List<Object> results = new ArrayList<>();
        for (String row : data) {
            results.add("CSV Analysis - " + row.trim());
        }
        return results;
    }
    
    @Override
    protected void cleanup() {
        System.out.println("Cleaning up CSV resources...");
    }
}
