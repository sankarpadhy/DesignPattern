package behavioral.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class DataMinerTest {
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    private DataMiner pdfMiner;
    private DataMiner csvMiner;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        pdfMiner = new PDFDataMiner();
        csvMiner = new CSVDataMiner();
    }

    @Test
    void testPDFMiningProcess() {
        pdfMiner.mine("test.pdf");
        String output = outputStream.toString();
        
        assertTrue(output.contains("Extracting data from PDF file"));
        assertTrue(output.contains("Parsing PDF data"));
        assertTrue(output.contains("Performing PDF-specific analysis"));
        assertTrue(output.contains("PDF Analysis"));
        assertTrue(output.contains("Cleaning up PDF resources"));
    }

    @Test
    void testCSVMiningProcess() {
        csvMiner.mine("test.csv");
        String output = outputStream.toString();
        
        assertTrue(output.contains("Extracting data from CSV file"));
        assertTrue(output.contains("Parsing CSV data"));
        assertTrue(output.contains("Performing CSV-specific analysis"));
        assertTrue(output.contains("CSV Analysis"));
        assertTrue(output.contains("Cleaning up CSV resources"));
    }

    @Test
    void testPDFDataExtraction() {
        String data = pdfMiner.extractData("test.pdf");
        assertNotNull(data);
        assertTrue(data.contains("PDF Content"));
    }

    @Test
    void testCSVDataExtraction() {
        String data = csvMiner.extractData("test.csv");
        assertNotNull(data);
        assertTrue(data.contains("CSV Content"));
    }

    @Test
    void testPDFDataParsing() {
        String rawData = pdfMiner.extractData("test.pdf");
        List<String> parsedData = pdfMiner.parseData(rawData);
        
        assertNotNull(parsedData);
        assertFalse(parsedData.isEmpty());
        assertTrue(parsedData.get(0).contains("PDF Content"));
    }

    @Test
    void testCSVDataParsing() {
        String rawData = csvMiner.extractData("test.csv");
        List<String> parsedData = csvMiner.parseData(rawData);
        
        assertNotNull(parsedData);
        assertFalse(parsedData.isEmpty());
        assertTrue(parsedData.get(0).contains("CSV Content"));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}
