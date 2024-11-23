package behavioral.chainofresponsibility.animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Interactive demonstration of the Chain of Responsibility pattern
 * Provides a visual representation of how log messages are handled
 * through different logging levels in the chain
 */
public class LoggingAnimationDemo extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextArea logArea;
    private JPanel chainPanel;
    private JPanel diagramPanel;
    private Timer animationTimer;
    private int currentStep = 0;
    private List<String> messages;
    private List<String> levels;
    private int messageIndex = 0;

    /**
     * Initializes the demo window with animation and control panels
     */
    public LoggingAnimationDemo() {
        // Set up the window title and default close operation
        setTitle("Chain of Responsibility Pattern Animation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize messages and levels
        initializeMessages();

        // Create main panel with card layout
        JPanel mainPanel = new JPanel(new CardLayout());
        mainPanel.add(buildAnimationPanel(), "animation");
        mainPanel.add(buildDiagramPanel(), "diagram");
        add(mainPanel, BorderLayout.CENTER);

        // Create control panel
        JPanel controlPanel = new JPanel(new FlowLayout());
        JButton nextButton = new JButton("Process Next Message");
        nextButton.addActionListener(e -> processNextMessage());
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetAnimation());
        controlPanel.add(nextButton);
        controlPanel.add(resetButton);

        // Add toggle button
        JButton toggleButton = new JButton("Show Class Diagram");
        toggleButton.addActionListener(e -> {
            boolean showingDiagram = toggleButton.getText().equals("Show Animation");
            toggleButton.setText(showingDiagram ? "Show Class Diagram" : "Show Animation");
            CardLayout cl = (CardLayout) mainPanel.getLayout();
            cl.show(mainPanel, showingDiagram ? "animation" : "diagram");
        });
        controlPanel.add(toggleButton);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Create animation timer
        animationTimer = new Timer(1000, e -> updateAnimation());

        // Center the frame
        setLocationRelativeTo(null);
    }

    /**
     * Builds the animation panel with chain visualization and log area
     */
    private JPanel buildAnimationPanel() {
        JPanel animationPanel = new JPanel(new BorderLayout());
        animationPanel.setBackground(Color.WHITE);

        // Create chain visualization panel
        chainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawChain(g);
            }
        };
        chainPanel.setPreferredSize(new Dimension(800, 200));
        chainPanel.setBackground(Color.WHITE);
        animationPanel.add(chainPanel, BorderLayout.NORTH);

        // Create log area
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        animationPanel.add(scrollPane, BorderLayout.CENTER);

        return animationPanel;
    }

    /**
     * Builds the diagram panel with class diagram
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
     * Initializes the list of log messages and their corresponding levels
     */
    private void initializeMessages() {
        messages = new ArrayList<>();
        levels = new ArrayList<>();
        
        messages.add("System starting up...");
        levels.add("INFO");
        
        messages.add("Invalid user input detected");
        levels.add("DEBUG");
        
        messages.add("Database connection failed!");
        levels.add("ERROR");
        
        messages.add("Processing user request");
        levels.add("INFO");
        
        messages.add("Critical system failure!");
        levels.add("ERROR");
    }

    /**
     * Draws the chain of responsibility visualization
     */
    private void drawChain(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = chainPanel.getWidth();
        int height = chainPanel.getHeight();
        int handlerWidth = 120;
        int handlerHeight = 60;
        int gap = 50;
        int startX = 50;
        int y = height / 2 - handlerHeight / 2;

        // Draw handlers
        String[] handlers = {"ERROR Handler", "DEBUG Handler", "INFO Handler"};
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN};
        
        for (int i = 0; i < handlers.length; i++) {
            int x = startX + i * (handlerWidth + gap);
            
            // Draw handler box
            g2d.setColor(colors[i]);
            g2d.fillRoundRect(x, y, handlerWidth, handlerHeight, 10, 10);
            g2d.setColor(Color.BLACK);
            g2d.drawRoundRect(x, y, handlerWidth, handlerHeight, 10, 10);

            // Draw handler name
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            FontMetrics fm = g2d.getFontMetrics();
            int textX = x + (handlerWidth - fm.stringWidth(handlers[i])) / 2;
            int textY = y + handlerHeight / 2 + fm.getAscent() / 2;
            g2d.drawString(handlers[i], textX, textY);

            // Draw arrow to next handler
            if (i < handlers.length - 1) {
                g2d.drawLine(x + handlerWidth, y + handlerHeight / 2, 
                            x + handlerWidth + gap, y + handlerHeight / 2);
                g2d.fillPolygon(
                    new int[]{x + handlerWidth + gap - 10, x + handlerWidth + gap, x + handlerWidth + gap - 10},
                    new int[]{y + handlerHeight / 2 - 5, y + handlerHeight / 2, y + handlerHeight / 2 + 5},
                    3
                );
            }
        }

        // Highlight current handler if processing
        if (currentStep > 0 && currentStep <= 3) {
            int x = startX + (currentStep - 1) * (handlerWidth + gap);
            g2d.setColor(new Color(255, 255, 0, 100));
            g2d.fillRoundRect(x, y, handlerWidth, handlerHeight, 10, 10);
        }
    }

    /**
     * Draws the class diagram
     */
    private void drawClassDiagram(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set up fonts
        Font titleFont = new Font("Arial", Font.BOLD, 14);
        Font normalFont = new Font("Arial", Font.PLAIN, 12);
        g2d.setFont(titleFont);

        // Draw abstract Handler class
        drawClass(g2d, "Logger",
                 new String[]{"# nextLogger: Logger",
                            "# logLevel: LogLevel"},
                 new String[]{"+ setNext(Logger): Logger",
                            "+ logMessage(LogLevel, String)",
                            "# write(String)"},
                 300, 100, 250, 130);

        // Draw Concrete Handlers
        drawClass(g2d, "ConsoleLogger",
                 new String[]{"- LOG_LEVEL: LogLevel"},
                 new String[]{"+ logMessage(LogLevel, String)",
                            "# write(String)"},
                 100, 300, 200, 100);

        drawClass(g2d, "FileLogger",
                 new String[]{"- LOG_LEVEL: LogLevel",
                            "- filePath: String"},
                 new String[]{"+ logMessage(LogLevel, String)",
                            "# write(String)"},
                 300, 300, 200, 120);

        drawClass(g2d, "ErrorLogger",
                 new String[]{"- LOG_LEVEL: LogLevel",
                            "- errorFile: String"},
                 new String[]{"+ logMessage(LogLevel, String)",
                            "# write(String)"},
                 500, 300, 200, 120);

        // Draw LogLevel enum
        drawEnum(g2d, "LogLevel",
                new String[]{"INFO", "DEBUG", "ERROR"},
                600, 100, 150, 100);

        // Draw relationships
        drawArrow(g2d, 200, 300, 300, 230); // ConsoleLogger to Logger
        drawArrow(g2d, 400, 300, 425, 230); // FileLogger to Logger
        drawArrow(g2d, 600, 300, 550, 230); // ErrorLogger to Logger
        drawAssociationArrow(g2d, 550, 165, 600, 165); // Logger to LogLevel
        drawSelfAssociation(g2d, 425, 100, 475, 100); // Logger to Logger

        // Add pattern description
        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        g2d.drawString("Chain of Responsibility Pattern:", 50, 50);
        g2d.drawString("Passes a request along a chain of handlers. Upon receiving a request, each handler", 50, 70);
        g2d.drawString("decides either to process it or to pass it to the next handler in the chain.", 50, 90);
    }

    /**
     * Draws a class box with attributes and methods
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
     * Draws an enum box with values
     */
    private void drawEnum(Graphics2D g2d, String name, String[] values,
                        int x, int y, int width, int height) {
        g2d.setFont(new Font("Arial", Font.ITALIC, 12));
        g2d.drawString("«enumeration»", x + 10, y - 5);
        
        // Draw enum box
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x, y, width, height);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, width, height);

        // Draw name
        int y1 = y + 25;
        g2d.drawLine(x, y1, x + width, y1);
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.drawString(name, x + 10, y + 20);

        // Draw values
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        for (int i = 0; i < values.length; i++) {
            g2d.drawString(values[i], x + 10, y1 + 20 + i * 25);
        }
    }

    /**
     * Draws an arrow between two points
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
     */
    private void drawAssociationArrow(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        g2d.drawLine(x1, y1, x2, y2);
        
        // Draw diamond
        int diamondSize = 10;
        double angle = Math.atan2(y2 - y1, x2 - x1);
        
        int xDiamond = x1;
        int yDiamond = y1;
        
        int[] xPoints = new int[4];
        int[] yPoints = new int[4];
        
        xPoints[0] = xDiamond;
        yPoints[0] = yDiamond;
        
        xPoints[1] = (int) (xDiamond - diamondSize * Math.cos(angle + Math.PI/4));
        yPoints[1] = (int) (yDiamond - diamondSize * Math.sin(angle + Math.PI/4));
        
        xPoints[2] = (int) (xDiamond - diamondSize * Math.cos(angle));
        yPoints[2] = (int) (yDiamond - diamondSize * Math.sin(angle));
        
        xPoints[3] = (int) (xDiamond - diamondSize * Math.cos(angle - Math.PI/4));
        yPoints[3] = (int) (yDiamond - diamondSize * Math.sin(angle - Math.PI/4));
        
        g2d.drawPolygon(xPoints, yPoints, 4);
    }

    /**
     * Draws a self-association arrow
     */
    private void drawSelfAssociation(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        int curveHeight = 30;
        int arrowSize = 10;
        
        // Draw curved line
        g2d.drawArc(x1, y1 - curveHeight, x2 - x1, curveHeight * 2, 0, 180);
        
        // Draw arrow head
        int arrowX = x2;
        int arrowY = y2;
        
        g2d.drawLine(arrowX, arrowY, arrowX - arrowSize, arrowY - arrowSize);
        g2d.drawLine(arrowX, arrowY, arrowX - arrowSize, arrowY + arrowSize);
    }

    /**
     * Processes the next log message
     */
    private void processNextMessage() {
        if (messageIndex < messages.size()) {
            String message = messages.get(messageIndex);
            String level = levels.get(messageIndex);
            
            logArea.append("Processing: " + message + " [" + level + "]\n");
            
            // Start animation for this message
            currentStep = 1;
            animationTimer.start();
            
            messageIndex++;
        } else {
            logArea.append("No more messages to process.\n");
        }
    }

    /**
     * Updates the animation
     */
    private void updateAnimation() {
        if (currentStep < 3) {
            String level = levels.get(messageIndex - 1);
            String handler = currentStep == 1 ? "ERROR" : (currentStep == 2 ? "DEBUG" : "INFO");
            
            logArea.append("  Checking " + handler + " Handler...\n");
            
            if ((handler.equals("ERROR") && level.equals("ERROR")) ||
                (handler.equals("DEBUG") && level.equals("DEBUG")) ||
                (handler.equals("INFO") && level.equals("INFO"))) {
                logArea.append("  Message handled by " + handler + " Handler\n\n");
                animationTimer.stop();
                currentStep = 0;
            } else {
                currentStep++;
            }
        } else {
            logArea.append("  Message handled by INFO Handler\n\n");
            animationTimer.stop();
            currentStep = 0;
        }
        
        chainPanel.repaint();
    }

    /**
     * Resets the animation
     */
    private void resetAnimation() {
        messageIndex = 0;
        currentStep = 0;
        logArea.setText("");
        animationTimer.stop();
        chainPanel.repaint();
    }

    /**
     * Main method to run the demo
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoggingAnimationDemo().setVisible(true);
        });
    }
}
