package behavioral.template;

import java.util.Arrays;
import java.util.List;

/**
 * Concrete implementation of DataMiner for PDF files
 * Demonstrates how template method steps are implemented for specific file type
 */
public class PDFDataMiner extends DataMiner {
    
    /**
     * Extracts text data from PDF file
     * @param path Path to the PDF file
     * @return Raw text content
     */
    @Override
    protected String extractData(String path) {
        System.out.println("Extracting data from PDF file: " + path);
        return "PDF Content: Page1, Page2, Page3";
    }
    
    /**
     * Parses PDF text content into structured format
     * @param rawData Raw PDF text content
     * @return List of parsed text elements
     */
    @Override
    protected List<String> parseData(String rawData) {
        System.out.println("Parsing PDF data...");
        return Arrays.asList(rawData.split(","));
    }
    
    /**
     * Provides PDF-specific data analysis
     * @param data Parsed PDF data
     * @return Analyzed data with PDF-specific processing
     */
    @Override
    protected List<Object> analyze(List<String> data) {
        System.out.println("Performing PDF-specific analysis...");
        List<Object> results = new ArrayList<>();
        for (String page : data) {
            results.add("PDF Analysis - " + page.trim());
        }
        return results;
    }
    
    /**
     * Cleans up resources after data mining
     */
    @Override
    protected void cleanup() {
        System.out.println("Cleaning up PDF resources...");
    }
}
