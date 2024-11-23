package creational.builder.animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ComputerBuilderDemo extends JFrame {
    private JPanel buildPanel;
    private JPanel diagramPanel;
    private Timer animationTimer;
    private int currentStep = 0;
    private List<Component> components;
    private boolean showingDiagram = true;
    private JButton toggleButton;
    private JButton nextButton;
    private JButton resetButton;
    private JLabel statusLabel;

    private static class Component {
        String name;
        Color color;
        Point position;
        boolean isInstalled;
        
        Component(String name, Color color, Point position) {
            this.name = name;
            this.color = color;
            this.position = position;
            this.isInstalled = false;
        }
    }

    public ComputerBuilderDemo() {
        setTitle("Builder Pattern Animation");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize components
        components = new ArrayList<>();
        initializeComponents();

        // Create main panel with card layout
        JPanel mainPanel = new JPanel(new CardLayout());
        
        // Create build visualization panel
        buildPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBuildProcess(g);
            }
        };
        buildPanel.setBackground(Color.WHITE);

        // Create class diagram panel
        diagramPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawClassDiagram(g);
            }
        };
        diagramPanel.setBackground(Color.WHITE);

        mainPanel.add(buildPanel, "build");
        mainPanel.add(diagramPanel, "diagram");
        add(mainPanel, BorderLayout.CENTER);

        // Create control panel
        JPanel controlPanel = new JPanel();
        toggleButton = new JButton("Show Build Process");
        nextButton = new JButton("Next Step");
        resetButton = new JButton("Reset");
        
        toggleButton.addActionListener(e -> {
            showingDiagram = !showingDiagram;
            toggleButton.setText(showingDiagram ? "Show Build Process" : "Show Class Diagram");
            CardLayout cl = (CardLayout) mainPanel.getLayout();
            cl.show(mainPanel, showingDiagram ? "diagram" : "build");
        });
        
        nextButton.addActionListener(e -> nextStep());
        resetButton.addActionListener(e -> reset());
        
        controlPanel.add(toggleButton);
        controlPanel.add(nextButton);
        controlPanel.add(resetButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Add status label
        statusLabel = new JLabel("Welcome to Computer Builder Demo", SwingConstants.CENTER);
        add(statusLabel, BorderLayout.NORTH);

        // Center the frame
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        components.add(new Component("Motherboard", new Color(50, 150, 50), new Point(300, 200)));
        components.add(new Component("CPU", new Color(200, 50, 50), new Point(350, 150)));
        components.add(new Component("Memory", new Color(50, 50, 200), new Point(400, 200)));
        components.add(new Component("Storage", new Color(200, 200, 50), new Point(350, 250)));
        components.add(new Component("Power Supply", new Color(150, 150, 150), new Point(250, 250)));
    }

    private void drawBuildProcess(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw computer case
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(200, 100, 400, 300);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(200, 100, 400, 300);

        // Draw installed components
        for (int i = 0; i < components.size(); i++) {
            Component comp = components.get(i);
            if (i < currentStep) {
                g2d.setColor(comp.color);
                g2d.fillRect(comp.position.x, comp.position.y, 100, 50);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(comp.position.x, comp.position.y, 100, 50);
                g2d.drawString(comp.name, comp.position.x + 10, comp.position.y + 30);
            }
        }

        // Draw builder steps
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        int y = 450;
        String[] steps = {
            "1. Add Motherboard",
            "2. Install CPU",
            "3. Add Memory",
            "4. Add Storage",
            "5. Install Power Supply"
        };
        
        for (int i = 0; i < steps.length; i++) {
            if (i < currentStep) {
                g2d.setColor(Color.GREEN);
                g2d.drawString("✓", 220, y);
            }
            g2d.setColor(Color.BLACK);
            g2d.drawString(steps[i], 250, y);
            y += 25;
        }
    }

    private void drawClassDiagram(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set up fonts
        Font titleFont = new Font("Arial", Font.BOLD, 14);
        Font normalFont = new Font("Arial", Font.PLAIN, 12);
        g2d.setFont(titleFont);

        // Draw Computer class
        drawClass(g2d, "Computer", 
                 new String[]{"- motherboard: String", "- cpu: String", "- memory: String",
                            "- storage: String", "- powerSupply: String"},
                 new String[]{}, 500, 100, 200, 150);

        // Draw Builder interface
        drawInterface(g2d, "ComputerBuilder",
                     new String[]{"+ addMotherboard()", "+ addCPU()", "+ addMemory()",
                                "+ addStorage()", "+ addPowerSupply()", "+ build(): Computer"},
                     200, 100, 250, 180);

        // Draw Concrete Builder
        drawClass(g2d, "GamingComputerBuilder",
                 new String[]{"- computer: Computer"},
                 new String[]{"+ addMotherboard()", "+ addCPU()", "+ addMemory()",
                            "+ addStorage()", "+ addPowerSupply()", "+ build(): Computer"},
                 200, 350, 250, 180);

        // Draw Director
        drawClass(g2d, "ComputerBuildDirector",
                 new String[]{"- builder: ComputerBuilder"},
                 new String[]{"+ constructComputer()", "+ changeBuilder()"},
                 500, 350, 200, 100);

        // Draw relationships
        drawArrow(g2d, 325, 350, 325, 280); // Implementation arrow
        drawArrow(g2d, 500, 400, 400, 400); // Association to Builder
        drawArrow(g2d, 600, 350, 600, 250); // Association to Computer

        // Add pattern description
        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        g2d.drawString("Builder Pattern:", 50, 50);
        g2d.drawString("Separates the construction of a complex object from its representation,", 50, 70);
        g2d.drawString("allowing the same construction process to create different representations.", 50, 90);
    }

    private void drawClass(Graphics2D g2d, String name, String[] attributes, String[] methods,
                         int x, int y, int width, int height) {
        // Draw class box
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x, y, width, height);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, width, height);

        // Draw name compartment
        int y1 = y + 25;
        g2d.drawLine(x, y1, x + width, y1);
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.drawString(name, x + 10, y + 20);

        // Draw attributes
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        int y2 = y1 + 25 * attributes.length;
        for (int i = 0; i < attributes.length; i++) {
            g2d.drawString(attributes[i], x + 10, y1 + 20 + i * 25);
        }
        g2d.drawLine(x, y2, x + width, y2);

        // Draw methods
        for (int i = 0; i < methods.length; i++) {
            g2d.drawString(methods[i], x + 10, y2 + 20 + i * 25);
        }
    }

    private void drawInterface(Graphics2D g2d, String name, String[] methods,
                             int x, int y, int width, int height) {
        g2d.setFont(new Font("Arial", Font.ITALIC, 12));
        g2d.drawString("«interface»", x + 10, y - 5);
        drawClass(g2d, name, new String[]{}, methods, x, y, width, height);
    }

    private void drawArrow(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        g2d.drawLine(x1, y1, x2, y2);
        
        // Calculate arrow head
        double angle = Math.atan2(y2 - y1, x2 - x1);
        int arrowSize = 10;
        
        int x3 = (int) (x2 - arrowSize * Math.cos(angle - Math.PI/6));
        int y3 = (int) (y2 - arrowSize * Math.sin(angle - Math.PI/6));
        int x4 = (int) (x2 - arrowSize * Math.cos(angle + Math.PI/6));
        int y4 = (int) (y2 - arrowSize * Math.sin(angle + Math.PI/6));
        
        g2d.drawLine(x2, y2, x3, y3);
        g2d.drawLine(x2, y2, x4, y4);
    }

    private void nextStep() {
        if (currentStep < components.size()) {
            currentStep++;
            Component comp = components.get(currentStep - 1);
            statusLabel.setText("Installing " + comp.name + "...");
            repaint();
        }
        if (currentStep == components.size()) {
            statusLabel.setText("Computer build completed!");
        }
    }

    private void reset() {
        currentStep = 0;
        statusLabel.setText("Starting new computer build...");
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ComputerBuilderDemo().setVisible(true);
        });
    }
}
