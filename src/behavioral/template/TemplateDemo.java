package behavioral.template;

public class TemplateDemo {
    public static void main(String[] args) {
        System.out.println("=== PDF Data Mining Process ===");
        DataMiner pdfMiner = new PDFDataMiner();
        pdfMiner.mine("sample.pdf");
        
        System.out.println("\n=== CSV Data Mining Process ===");
        DataMiner csvMiner = new CSVDataMiner();
        csvMiner.mine("sample.csv");
    }
}
