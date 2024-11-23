package behavioral.strategy.animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Interactive demonstration of the Strategy Pattern using sorting algorithms
 * Shows how different sorting strategies can be swapped at runtime
 * Provides both animated visualization and UML class diagram
 */
public class SortingStrategyDemo extends JFrame {
    private List<Integer> numbers;
    private Timer sortingTimer;
    private String currentStrategy = "Bubble Sort";
    private int currentStep = 0;
    private int currentIndex = 0;
    private boolean isSorting = false;
    private List<Integer> highlightIndices;
    private JLabel statusLabel;
    private JComboBox<String> strategyCombo;
    private JButton sortButton;
    private JButton resetButton;

    /**
     * Initializes the demo window with animation and control panels
     */
    public SortingStrategyDemo() {
        setTitle("Sorting Strategy Pattern Animation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize data
        numbers = new ArrayList<>();
        highlightIndices = new ArrayList<>();
        initializeNumbers();

        // Create main panel with card layout
        JPanel mainPanel = new JPanel(new CardLayout());
        mainPanel.add(buildAnimationPanel(), "animation");
        mainPanel.add(buildDiagramPanel(), "diagram");
        add(mainPanel, BorderLayout.CENTER);

        // Create control panel
        JPanel controlPanel = new JPanel();
        String[] strategies = {"Bubble Sort", "Selection Sort", "Insertion Sort"};
        strategyCombo = new JComboBox<>(strategies);
        sortButton = new JButton("Sort");
        resetButton = new JButton("Reset");
        statusLabel = new JLabel("Select a sorting strategy");

        sortButton.addActionListener(e -> startSorting());
        resetButton.addActionListener(e -> resetArray());
        strategyCombo.addActionListener(e -> {
            currentStrategy = (String) strategyCombo.getSelectedItem();
            resetArray();
        });

        controlPanel.add(new JLabel("Strategy:"));
        controlPanel.add(strategyCombo);
        controlPanel.add(sortButton);
        controlPanel.add(resetButton);
        add(controlPanel, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.NORTH);

        // Add toggle button
        JButton toggleButton = new JButton("Show Class Diagram");
        toggleButton.addActionListener(e -> {
            boolean showingDiagram = toggleButton.getText().equals("Show Animation");
            toggleButton.setText(showingDiagram ? "Show Class Diagram" : "Show Animation");
            CardLayout cl = (CardLayout) mainPanel.getLayout();
            cl.show(mainPanel, showingDiagram ? "animation" : "diagram");
        });
        controlPanel.add(toggleButton);

        // Initialize sorting timer
        sortingTimer = new Timer(100, e -> performSortStep());

        // Center the frame
        setLocationRelativeTo(null);
    }

    /**
     * Builds the animation panel for the sorting visualization
     * @return the animation panel
     */
    private JPanel buildAnimationPanel() {
        JPanel animationPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBars(g);
            }
        };
        animationPanel.setBackground(Color.WHITE);
        return animationPanel;
    }

    /**
     * Builds the diagram panel for the UML class diagram
     * @return the diagram panel
     */
    private JPanel buildDiagramPanel() {
        JPanel diagramPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawClassDiagram(g);
            }
        };
        diagramPanel.setBackground(Color.WHITE);
        return diagramPanel;
    }

    /**
     * Initializes the numbers array with random values
     */
    private void initializeNumbers() {
        numbers.clear();
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            numbers.add(rand.nextInt(90) + 10);
        }
        highlightIndices.clear();
        currentStep = 0;
        currentIndex = 0;
    }

    /**
     * Draws the bars for the sorting visualization
     * @param g the graphics context
     */
    private void drawBars(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int barWidth = width / 25;
        int maxHeight = height - 100;

        for (int i = 0; i < numbers.size(); i++) {
            int x = i * (barWidth + 5) + 50;
            int barHeight = (numbers.get(i) * maxHeight) / 100;
            int y = height - barHeight - 50;

            // Determine bar color
            if (highlightIndices.contains(i)) {
                g2d.setColor(Color.RED);
            } else {
                g2d.setColor(Color.BLUE);
            }

            // Draw bar
            g2d.fillRect(x, y, barWidth, barHeight);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, y, barWidth, barHeight);

            // Draw number
            g2d.drawString(String.valueOf(numbers.get(i)), x + 5, height - 30);
        }
    }

    /**
     * Draws the UML class diagram for the Strategy Pattern
     * @param g the graphics context
     */
    private void drawClassDiagram(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set up fonts
        Font titleFont = new Font("Arial", Font.BOLD, 14);
        Font normalFont = new Font("Arial", Font.PLAIN, 12);
        g2d.setFont(titleFont);

        // Draw Strategy interface
        drawInterface(g2d, "SortingStrategy",
                     new String[]{"+ sort(int[] array)",
                                "+ getName(): String"},
                     200, 100, 250, 100);

        // Draw Context class
        drawClass(g2d, "SortingContext",
                 new String[]{"- strategy: SortingStrategy"},
                 new String[]{"+ setStrategy(SortingStrategy)",
                            "+ executeSort(int[] array)"},
                 200, 300, 250, 120);

        // Draw Concrete Strategies
        drawClass(g2d, "BubbleSort",
                 new String[]{},
                 new String[]{"+ sort(int[] array)",
                            "+ getName(): String"},
                 500, 100, 200, 100);

        drawClass(g2d, "SelectionSort",
                 new String[]{},
                 new String[]{"+ sort(int[] array)",
                            "+ getName(): String"},
                 500, 220, 200, 100);

        drawClass(g2d, "InsertionSort",
                 new String[]{},
                 new String[]{"+ sort(int[] array)",
                            "+ getName(): String"},
                 500, 340, 200, 100);

        // Draw relationships
        drawArrow(g2d, 500, 150, 450, 150); // BubbleSort to Strategy
        drawArrow(g2d, 500, 270, 450, 150); // SelectionSort to Strategy
        drawArrow(g2d, 500, 390, 450, 150); // InsertionSort to Strategy
        drawAssociationArrow(g2d, 325, 300, 325, 200); // Context to Strategy

        // Add pattern description
        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        g2d.drawString("Strategy Pattern:", 50, 50);
        g2d.drawString("Defines a family of algorithms, encapsulates each one, and makes them", 50, 70);
        g2d.drawString("interchangeable. Strategy lets the algorithm vary independently from", 50, 90);
        g2d.drawString("clients that use it.", 50, 110);
    }

    /**
     * Draws a class box with attributes and methods
     * @param g2d the graphics context
     * @param name the class name
     * @param attributes the class attributes
     * @param methods the class methods
     * @param x the x-coordinate of the class box
     * @param y the y-coordinate of the class box
     * @param width the width of the class box
     * @param height the height of the class box
     */
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

    /**
     * Draws an interface box with methods
     * @param g2d the graphics context
     * @param name the interface name
     * @param methods the interface methods
     * @param x the x-coordinate of the interface box
     * @param y the y-coordinate of the interface box
     * @param width the width of the interface box
     * @param height the height of the interface box
     */
    private void drawInterface(Graphics2D g2d, String name, String[] methods,
                             int x, int y, int width, int height) {
        g2d.setFont(new Font("Arial", Font.ITALIC, 12));
        g2d.drawString("«interface»", x + 10, y - 5);
        drawClass(g2d, name, new String[]{}, methods, x, y, width, height);
    }

    /**
     * Draws an arrow between two points
     * @param g2d the graphics context
     * @param x1 the x-coordinate of the starting point
     * @param y1 the y-coordinate of the starting point
     * @param x2 the x-coordinate of the ending point
     * @param y2 the y-coordinate of the ending point
     */
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

    /**
     * Draws an association arrow between two points
     * @param g2d the graphics context
     * @param x1 the x-coordinate of the starting point
     * @param y1 the y-coordinate of the starting point
     * @param x2 the x-coordinate of the ending point
     * @param y2 the y-coordinate of the ending point
     */
    private void drawAssociationArrow(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        // Draw diamond
        int diamondSize = 10;
        g2d.drawLine(x1, y1, x1 - diamondSize/2, y1 - diamondSize);
        g2d.drawLine(x1 - diamondSize/2, y1 - diamondSize, x1, y1 - diamondSize*2);
        g2d.drawLine(x1, y1 - diamondSize*2, x1 + diamondSize/2, y1 - diamondSize);
        g2d.drawLine(x1 + diamondSize/2, y1 - diamondSize, x1, y1);
        
        // Draw line to target
        g2d.drawLine(x1, y1 - diamondSize*2, x2, y2);
    }

    /**
     * Starts the sorting animation
     */
    private void startSorting() {
        if (!isSorting) {
            isSorting = true;
            sortButton.setEnabled(false);
            strategyCombo.setEnabled(false);
            resetButton.setEnabled(false);
            currentStep = 0;
            currentIndex = 0;
            sortingTimer.start();
        }
    }

    /**
     * Performs a single step of the sorting algorithm
     */
    private void performSortStep() {
        highlightIndices.clear();
        
        switch (currentStrategy) {
            case "Bubble Sort":
                bubbleSortStep();
                break;
            case "Selection Sort":
                selectionSortStep();
                break;
            case "Insertion Sort":
                insertionSortStep();
                break;
        }
        
        repaint();
    }

    /**
     * Performs a single step of the bubble sort algorithm
     */
    private void bubbleSortStep() {
        if (currentIndex < numbers.size() - 1 - currentStep) {
            highlightIndices.add(currentIndex);
            highlightIndices.add(currentIndex + 1);
            
            if (numbers.get(currentIndex) > numbers.get(currentIndex + 1)) {
                // Swap elements
                int temp = numbers.get(currentIndex);
                numbers.set(currentIndex, numbers.get(currentIndex + 1));
                numbers.set(currentIndex + 1, temp);
            }
            
            currentIndex++;
            statusLabel.setText("Bubble Sort: Comparing elements " + currentIndex + " and " + (currentIndex + 1));
        } else {
            currentStep++;
            currentIndex = 0;
            
            if (currentStep >= numbers.size() - 1) {
                finishSorting();
            }
        }
    }

    /**
     * Performs a single step of the selection sort algorithm
     */
    private void selectionSortStep() {
        if (currentStep < numbers.size() - 1) {
            highlightIndices.add(currentStep);
            
            if (currentIndex == currentStep) {
                // Start new minimum search
                numbers.set(numbers.size() - 1, currentStep); // Store minIndex
                currentIndex = currentStep + 1;
            }
            
            highlightIndices.add(currentIndex);
            
            int minIndex = numbers.get(numbers.size() - 1);
            if (numbers.get(currentIndex) < numbers.get(minIndex)) {
                numbers.set(numbers.size() - 1, currentIndex);
            }
            
            currentIndex++;
            statusLabel.setText("Selection Sort: Finding minimum element");
            
            if (currentIndex == numbers.size() - 1) {
                // Swap elements
                minIndex = numbers.get(numbers.size() - 1);
                int temp = numbers.get(currentStep);
                numbers.set(currentStep, numbers.get(minIndex));
                numbers.set(minIndex, temp);
                currentStep++;
                currentIndex = currentStep;
            }
        } else {
            finishSorting();
        }
    }

    /**
     * Performs a single step of the insertion sort algorithm
     */
    private void insertionSortStep() {
        if (currentStep < numbers.size()) {
            highlightIndices.add(currentStep);
            
            if (currentIndex > 0 && numbers.get(currentIndex - 1) > numbers.get(currentIndex)) {
                // Swap elements
                int temp = numbers.get(currentIndex);
                numbers.set(currentIndex, numbers.get(currentIndex - 1));
                numbers.set(currentIndex - 1, temp);
                currentIndex--;
                highlightIndices.add(currentIndex);
                statusLabel.setText("Insertion Sort: Inserting element " + temp);
            } else {
                currentStep++;
                currentIndex = currentStep;
            }
        } else {
            finishSorting();
        }
    }

    /**
     * Finishes the sorting animation
     */
    private void finishSorting() {
        sortingTimer.stop();
        isSorting = false;
        sortButton.setEnabled(true);
        strategyCombo.setEnabled(true);
        resetButton.setEnabled(true);
        statusLabel.setText("Sorting completed!");
        highlightIndices.clear();
        repaint();
    }

    /**
     * Resets the numbers array and stops the sorting animation
     */
    private void resetArray() {
        if (!isSorting) {
            initializeNumbers();
            statusLabel.setText("Array reset - Select a sorting strategy");
            repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SortingStrategyDemo().setVisible(true);
        });
    }
}
