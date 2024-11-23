package creational.abstractfactory.animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FurnitureAnimationDemo extends JFrame {
    private JPanel displayPanel;
    private Timer timer;
    private int step = 0;
    private String currentStyle = "Modern";
    private final Color MODERN_COLOR = new Color(0, 150, 255);
    private final Color VICTORIAN_COLOR = new Color(139, 69, 19);

    public FurnitureAnimationDemo() {
        setTitle("Abstract Factory Pattern Animation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Create display panel for furniture
        displayPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawFurniture(g);
            }
        };
        displayPanel.setBackground(Color.WHITE);
        mainPanel.add(displayPanel, BorderLayout.CENTER);

        // Create control panel
        JPanel controlPanel = new JPanel(new FlowLayout());
        JButton switchButton = new JButton("Switch Style");
        switchButton.addActionListener(e -> switchStyle());
        controlPanel.add(switchButton);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        // Add description label
        JLabel descLabel = new JLabel("Current Style: Modern", SwingConstants.CENTER);
        descLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(descLabel, BorderLayout.NORTH);

        add(mainPanel);

        // Create timer for animation
        timer = new Timer(50, e -> {
            step = (step + 1) % 360;
            displayPanel.repaint();
        });
        timer.start();

        // Center the frame
        setLocationRelativeTo(null);
    }

    private void drawFurniture(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = displayPanel.getWidth();
        int height = displayPanel.getHeight();

        // Set color based on style
        Color mainColor = currentStyle.equals("Modern") ? MODERN_COLOR : VICTORIAN_COLOR;
        g2d.setColor(mainColor);

        // Draw chair
        drawChair(g2d, width / 4, height / 2);

        // Draw table
        drawTable(g2d, width / 2, height / 2);

        // Draw sofa
        drawSofa(g2d, 3 * width / 4, height / 2);

        // Add labels
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.drawString("Chair", width / 4 - 20, height / 2 + 100);
        g2d.drawString("Table", width / 2 - 20, height / 2 + 100);
        g2d.drawString("Sofa", 3 * width / 4 - 20, height / 2 + 100);
    }

    private void drawChair(Graphics2D g2d, int x, int y) {
        if (currentStyle.equals("Modern")) {
            // Draw modern chair
            g2d.fillRect(x - 20, y - 20, 40, 40); // seat
            g2d.fillRect(x - 20, y + 20, 5, 30);  // legs
            g2d.fillRect(x + 15, y + 20, 5, 30);
            g2d.fillRect(x - 20, y - 60, 5, 40);  // back
            g2d.fillRect(x + 15, y - 60, 5, 40);
        } else {
            // Draw Victorian chair
            g2d.fillRect(x - 25, y - 20, 50, 40); // seat
            // Ornate legs
            g2d.fillRect(x - 25, y + 20, 10, 30);
            g2d.fillRect(x + 15, y + 20, 10, 30);
            // Ornate back
            g2d.fillRect(x - 25, y - 70, 50, 10);
            g2d.fillRect(x - 25, y - 70, 10, 50);
            g2d.fillRect(x + 15, y - 70, 10, 50);
        }
    }

    private void drawTable(Graphics2D g2d, int x, int y) {
        if (currentStyle.equals("Modern")) {
            // Draw modern table
            g2d.fillRect(x - 40, y - 10, 80, 10); // top
            g2d.fillRect(x - 5, y, 10, 40);      // leg
        } else {
            // Draw Victorian table
            g2d.fillRect(x - 50, y - 15, 100, 15); // top
            // Ornate legs
            g2d.fillRect(x - 40, y, 15, 40);
            g2d.fillRect(x + 25, y, 15, 40);
            // Decorative elements
            g2d.fillOval(x - 45, y - 20, 10, 10);
            g2d.fillOval(x + 35, y - 20, 10, 10);
        }
    }

    private void drawSofa(Graphics2D g2d, int x, int y) {
        if (currentStyle.equals("Modern")) {
            // Draw modern sofa
            g2d.fillRect(x - 60, y - 20, 120, 40); // seat
            g2d.fillRect(x - 60, y - 60, 20, 40);  // arms
            g2d.fillRect(x + 40, y - 60, 20, 40);
            g2d.fillRect(x - 60, y - 60, 120, 20); // back
        } else {
            // Draw Victorian sofa
            g2d.fillRect(x - 70, y - 25, 140, 45); // seat
            // Ornate arms
            g2d.fillRect(x - 70, y - 70, 25, 45);
            g2d.fillRect(x + 45, y - 70, 25, 45);
            // Ornate back with curves
            g2d.fillRect(x - 70, y - 70, 140, 25);
            for (int i = 0; i < 5; i++) {
                g2d.fillOval(x - 60 + i * 35, y - 80, 20, 20);
            }
        }
    }

    private void switchStyle() {
        currentStyle = currentStyle.equals("Modern") ? "Victorian" : "Modern";
        ((JLabel)((JPanel)getContentPane().getComponent(0)).getComponent(2)).setText("Current Style: " + currentStyle);
        displayPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FurnitureAnimationDemo().setVisible(true);
        });
    }
}
