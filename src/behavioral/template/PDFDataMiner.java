package behavioral.template;

import java.util.Arrays;
import java.util.List;

public class PDFDataMiner extends DataMiner {
    
    @Override
    protected String extractData(String path) {
        System.out.println("Extracting data from PDF file: " + path);
        return "PDF Content: Page1, Page2, Page3";
    }
    
    @Override
    protected List<String> parseData(String rawData) {
        System.out.println("Parsing PDF data...");
        return Arrays.asList(rawData.split(","));
    }
    
    @Override
    protected List<Object> analyze(List<String> data) {
        System.out.println("Performing PDF-specific analysis...");
        List<Object> results = new ArrayList<>();
        for (String page : data) {
            results.add("PDF Analysis - " + page.trim());
        }
        return results;
    }
    
    @Override
    protected void cleanup() {
        System.out.println("Cleaning up PDF resources...");
    }
}
