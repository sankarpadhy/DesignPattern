package behavioral.template.animation;

import behavioral.template.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Interactive demonstration of the Template Method Pattern
 * Shows how the algorithm structure remains constant while steps vary
 * Includes both animation and UML class diagram visualization
 */
public class TemplateMethodDemo extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel mainPanel;
    private JPanel animationPanel;
    private JPanel controlPanel;
    private Timer animationTimer;
    private boolean showingDiagram = false;
    private List<String> currentSteps;
    private int currentStep = 0;
    private DataMiner currentMiner;

    /**
     * Initializes the demo window with animation and control panels
     */
    public class TemplateMethodDemo() {
        setTitle("Template Method Pattern - Data Mining Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        initializeComponents();
        setupLayout();
        startAnimation();
    }

    private void initializeComponents() {
        currentSteps = new ArrayList<>();
        mainPanel = new JPanel(new BorderLayout());
        animationPanel = createAnimationPanel();
        controlPanel = createControlPanel();
    }

    private JPanel createAnimationPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (showingDiagram) {
                    drawUMLDiagram(g);
                } else {
                    drawAnimation(g);
                }
            }
        };
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton toggleViewButton = new JButton("Toggle UML/Animation");
        toggleViewButton.addActionListener(e -> {
            showingDiagram = !showingDiagram;
            repaint();
        });

        JButton processPDFButton = new JButton("Process PDF");
        processPDFButton.addActionListener(e -> {
            currentMiner = new PDFDataMiner();
            currentSteps.clear();
            currentStep = 0;
            currentMiner.mine("sample.pdf");
            repaint();
        });

        JButton processCSVButton = new JButton("Process CSV");
        processCSVButton.addActionListener(e -> {
            currentMiner = new CSVDataMiner();
            currentSteps.clear();
            currentStep = 0;
            currentMiner.mine("sample.csv");
            repaint();
        });

        panel.add(toggleViewButton);
        panel.add(processPDFButton);
        panel.add(processCSVButton);

        return panel;
    }

    private void setupLayout() {
        mainPanel.add(animationPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private void startAnimation() {
        animationTimer = new Timer(50, e -> repaint());
        animationTimer.start();
    }

    private void drawAnimation(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight() - 100; // Leave space for controls

        // Draw algorithm steps
        int startY = 50;
        int stepHeight = 60;
        String[] steps = {"Open File", "Extract Data", "Parse Data", "Analyze Data", "Send Report"};

        for (int i = 0; i < steps.length; i++) {
            int y = startY + (i * stepHeight);
            
            // Draw step box
            g2d.setColor(i == currentStep ? Color.LIGHT_GRAY : Color.WHITE);
            g2d.fillRect(width/4, y, width/2, stepHeight-10);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(width/4, y, width/2, stepHeight-10);
            
            // Draw step name
            g2d.drawString(steps[i], width/4 + 10, y + 30);
        }

        // Draw execution log
        g2d.setColor(Color.BLACK);
        int logY = startY + (steps.length * stepHeight) + 20;
        g2d.drawString("Execution Log:", 10, logY);
        for (int i = 0; i < currentSteps.size(); i++) {
            g2d.drawString(currentSteps.get(i), 20, logY + ((i + 1) * 20));
        }
    }

    private void drawUMLDiagram(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw title
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("Template Method Pattern - UML Class Diagram", 50, 30);

        // Draw explanation
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.drawString("The Template Method Pattern defines the skeleton of an algorithm in a method,", 50, 60);
        g2d.drawString("deferring some steps to subclasses. Template Method lets subclasses redefine", 50, 80);
        g2d.drawString("certain steps of an algorithm without changing the algorithm's structure.", 50, 100);

        // Draw abstract class
        String[] abstractMethods = {
            "# openFile(path: String): File",
            "# extractData(file: File): String",
            "# parseData(rawData: String): List<String>",
            "# analyzeData(data: List<String>): List<String>",
            "# sendReport(data: List<String>): void"
        };
        drawClass(g2d, "AbstractClass\nDataMiner", 
                 new String[]{"+ final mine(path: String): List<String>"}, 
                 abstractMethods, 
                 300, 150, 250, 180);

        // Draw concrete classes
        String[] concreteMethods = {
            "# openFile(path: String): File",
            "# extractData(file: File): String",
            "# parseData(rawData: String): List<String>",
            "# analyzeData(data: List<String>): List<String>",
            "# sendReport(data: List<String>): void"
        };
        drawClass(g2d, "PDFDataMiner", new String[]{}, concreteMethods, 150, 400, 200, 180);
        drawClass(g2d, "CSVDataMiner", new String[]{}, concreteMethods, 500, 400, 200, 180);

        // Draw inheritance arrows
        drawInheritanceArrow(g2d, 250, 400, 425, 330);
        drawInheritanceArrow(g2d, 600, 400, 425, 330);
    }

    private void drawClass(Graphics2D g2d, String name, String[] attributes, String[] methods, 
                         int x, int y, int width, int height) {
        // Draw class box
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x, y, width, height);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, width, height);

        // Draw name compartment
        int nameHeight = 30;
        g2d.drawLine(x, y + nameHeight, x + width, y + nameHeight);
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        for (String line : name.split("\n")) {
            g2d.drawString(line, x + 10, y + 20);
            y += 15;
        }

        // Draw attributes
        y = y + 10;
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.drawLine(x, y + nameHeight, x + width, y + nameHeight);
        for (String attribute : attributes) {
            g2d.drawString(attribute, x + 10, y + 45);
            y += 20;
        }

        // Draw methods
        y += 10;
        for (String method : methods) {
            g2d.drawString(method, x + 10, y + 45);
            y += 20;
        }
    }

    private void drawInheritanceArrow(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        g2d.drawLine(x1, y1, x2, y2);
        
        // Draw arrow head
        int arrowSize = 10;
        double angle = Math.atan2(y2 - y1, x2 - x1);
        int x3 = x2 - (int) (arrowSize * Math.cos(angle - Math.PI/6));
        int y3 = y2 - (int) (arrowSize * Math.sin(angle - Math.PI/6));
        int x4 = x2 - (int) (arrowSize * Math.cos(angle + Math.PI/6));
        int y4 = y2 - (int) (arrowSize * Math.sin(angle + Math.PI/6));
        
        g2d.drawLine(x2, y2, x3, y3);
        g2d.drawLine(x2, y2, x4, y4);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TemplateMethodDemo().setVisible(true);
        });
    }
}
