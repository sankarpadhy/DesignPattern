package creational.singleton.animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DatabaseConnectionDemo extends JFrame {
    private JPanel animationPanel;
    private JPanel diagramPanel;
    private Timer connectionTimer;
    private List<ClientRequest> requests;
    private Random random;
    private int currentStep = 0;
    private boolean isProcessing = false;

    private static class ClientRequest {
        Point position;
        String query;
        Color color;
        boolean isProcessed;
        
        ClientRequest(Point position, String query, Color color) {
            this.position = position;
            this.query = query;
            this.color = color;
            this.isProcessed = false;
        }
    }

    public DatabaseConnectionDemo() {
        setTitle("Singleton Pattern - Database Connection Demo");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        random = new Random();
        requests = new ArrayList<>();
        initializeRequests();

        // Create main panel with card layout
        JPanel mainPanel = new JPanel(new CardLayout());
        mainPanel.add(buildAnimationPanel(), "animation");
        mainPanel.add(buildDiagramPanel(), "diagram");
        add(mainPanel, BorderLayout.CENTER);

        // Create control panel
        JPanel controlPanel = new JPanel();
        JButton addRequestButton = new JButton("Add Request");
        JButton processButton = new JButton("Process Requests");
        JButton resetButton = new JButton("Reset");
        
        addRequestButton.addActionListener(e -> addNewRequest());
        processButton.addActionListener(e -> startProcessing());
        resetButton.addActionListener(e -> resetAnimation());

        // Add toggle button
        JButton toggleButton = new JButton("Show Class Diagram");
        toggleButton.addActionListener(e -> {
            boolean showingDiagram = toggleButton.getText().equals("Show Animation");
            toggleButton.setText(showingDiagram ? "Show Class Diagram" : "Show Animation");
            CardLayout cl = (CardLayout) mainPanel.getLayout();
            cl.show(mainPanel, showingDiagram ? "animation" : "diagram");
        });

        controlPanel.add(addRequestButton);
        controlPanel.add(processButton);
        controlPanel.add(resetButton);
        controlPanel.add(toggleButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Initialize timer
        connectionTimer = new Timer(1000, e -> processNextRequest());

        // Center the frame
        setLocationRelativeTo(null);
    }

    private JPanel buildAnimationPanel() {
        animationPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawAnimation(g);
            }
        };
        animationPanel.setBackground(Color.WHITE);
        return animationPanel;
    }

    private JPanel buildDiagramPanel() {
        diagramPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawClassDiagram(g);
            }
        };
        diagramPanel.setBackground(Color.WHITE);
        return diagramPanel;
    }

    private void initializeRequests() {
        requests.add(new ClientRequest(new Point(50, 100), "SELECT * FROM users", Color.BLUE));
        requests.add(new ClientRequest(new Point(50, 200), "UPDATE products", Color.GREEN));
        requests.add(new ClientRequest(new Point(50, 300), "INSERT INTO orders", Color.RED));
    }

    private void drawAnimation(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw database
        int dbX = getWidth() - 200;
        int dbY = getHeight() / 2 - 50;
        drawDatabase(g2d, dbX, dbY);

        // Draw singleton instance
        int instanceX = getWidth() / 2 - 50;
        int instanceY = getHeight() / 2 - 25;
        drawSingletonInstance(g2d, instanceX, instanceY);

        // Draw requests and connections
        for (ClientRequest request : requests) {
            drawRequest(g2d, request);
            if (request.isProcessed) {
                drawConnection(g2d, request.position, new Point(instanceX, instanceY));
                drawConnection(g2d, new Point(instanceX + 100, instanceY), new Point(dbX, dbY));
            }
        }
    }

    private void drawDatabase(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillOval(x, y, 100, 40);
        g2d.fillRect(x, y + 20, 100, 60);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(x, y, 100, 40);
        g2d.drawRect(x, y + 20, 100, 60);
        g2d.drawString("Database", x + 20, y + 55);
    }

    private void drawSingletonInstance(Graphics2D g2d, int x, int y) {
        g2d.setColor(new Color(230, 230, 255));
        g2d.fillRect(x, y, 100, 50);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, 100, 50);
        g2d.drawString("Connection", x + 15, y + 30);
        g2d.drawString("Instance", x + 20, y + 45);
    }

    private void drawRequest(Graphics2D g2d, ClientRequest request) {
        g2d.setColor(request.color);
        g2d.fillOval(request.position.x, request.position.y, 30, 30);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(request.position.x, request.position.y, 30, 30);
        g2d.drawString("SQL", request.position.x + 5, request.position.y + 20);
    }

    private void drawConnection(Graphics2D g2d, Point start, Point end) {
        g2d.setColor(Color.BLACK);
        g2d.drawLine(start.x + 30, start.y + 15, end.x, end.y + 25);
    }

    private void drawClassDiagram(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw Singleton class
        drawClass(g2d, "DatabaseConnection",
                 new String[]{
                     "- instance: DatabaseConnection",
                     "- connection: Connection",
                     "- constructor(): private"
                 },
                 new String[]{
                     "+ getInstance(): DatabaseConnection",
                     "+ executeQuery(String): Result",
                     "- initConnection(): void"
                 },
                 300, 150, 300, 150);

        // Draw singleton instance arrow
        drawSelfAssociation(g2d, 450, 150, 500, 150);
        g2d.drawString("instance", 460, 140);

        // Add pattern description
        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        g2d.drawString("Singleton Pattern:", 50, 50);
        g2d.drawString("Ensures a class has only one instance and provides a global point of access to it.", 50, 70);
        g2d.drawString("Common use case: Database connections, logging, thread pools, configuration settings.", 50, 90);

        // Add notes
        drawNote(g2d, "Key characteristics:", new String[]{
            "1. Private constructor",
            "2. Static instance",
            "3. Global access point"
        }, 50, 150, 200, 100);

        drawNote(g2d, "Thread Safety Options:", new String[]{
            "1. Synchronized method",
            "2. Double-checked locking",
            "3. Static initialization",
            "4. Enum implementation"
        }, 50, 300, 200, 120);
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

    private void drawNote(Graphics2D g2d, String title, String[] lines,
                        int x, int y, int width, int height) {
        // Draw note box with folded corner
        g2d.setColor(new Color(255, 255, 200));
        g2d.fillRect(x, y, width - 10, height);
        g2d.fillPolygon(
            new int[]{x + width - 10, x + width - 10, x + width},
            new int[]{y, y + 10, y},
            3
        );
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, width - 10, height);
        g2d.drawLine(x + width - 10, y, x + width - 10, y + 10);
        g2d.drawLine(x + width - 10, y + 10, x + width, y);

        // Draw title and lines
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.drawString(title, x + 5, y + 20);
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        for (int i = 0; i < lines.length; i++) {
            g2d.drawString(lines[i], x + 5, y + 40 + i * 20);
        }
    }

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

    private void addNewRequest() {
        int y = 100 + requests.size() * 100;
        if (y < getHeight() - 100) {
            String[] queries = {"SELECT", "UPDATE", "INSERT", "DELETE"};
            String query = queries[random.nextInt(queries.length)];
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            requests.add(new ClientRequest(new Point(50, y), query, color));
            repaint();
        }
    }

    private void startProcessing() {
        if (!isProcessing) {
            isProcessing = true;
            connectionTimer.start();
        }
    }

    private void processNextRequest() {
        if (currentStep < requests.size()) {
            requests.get(currentStep).isProcessed = true;
            currentStep++;
            repaint();
        } else {
            connectionTimer.stop();
            isProcessing = false;
        }
    }

    private void resetAnimation() {
        currentStep = 0;
        isProcessing = false;
        connectionTimer.stop();
        for (ClientRequest request : requests) {
            request.isProcessed = false;
        }
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DatabaseConnectionDemo().setVisible(true);
        });
    }
}
