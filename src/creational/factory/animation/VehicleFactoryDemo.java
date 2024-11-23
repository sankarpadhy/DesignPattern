package creational.factory.animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleFactoryDemo extends JFrame {
    private JPanel displayPanel;
    private Timer animationTimer;
    private int currentStep = 0;
    private String currentVehicle = "Car";
    private List<Point> particlePoints;
    private boolean isCreating = false;

    public VehicleFactoryDemo() {
        setTitle("Factory Pattern Animation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create main display panel
        displayPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawFactory(g);
            }
        };
        displayPanel.setBackground(new Color(240, 240, 240));
        add(displayPanel, BorderLayout.CENTER);

        // Create control panel
        JPanel controlPanel = new JPanel();
        String[] vehicles = {"Car", "Motorcycle", "Truck"};
        JComboBox<String> vehicleCombo = new JComboBox<>(vehicles);
        JButton createButton = new JButton("Create Vehicle");
        
        createButton.addActionListener(e -> {
            currentVehicle = (String) vehicleCombo.getSelectedItem();
            startCreationAnimation();
        });

        controlPanel.add(new JLabel("Select Vehicle: "));
        controlPanel.add(vehicleCombo);
        controlPanel.add(createButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Initialize animation
        particlePoints = new ArrayList<>();
        animationTimer = new Timer(50, e -> updateAnimation());

        // Center the frame
        setLocationRelativeTo(null);
    }

    private void drawFactory(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = displayPanel.getWidth();
        int height = displayPanel.getHeight();

        // Draw factory building
        g2d.setColor(new Color(200, 200, 200));
        g2d.fillRect(width/4, height/4, width/2, height/2);
        
        // Draw roof
        int[] xPoints = {width/4 - 30, width/2, width*3/4 + 30};
        int[] yPoints = {height/4, height/8, height/4};
        g2d.fillPolygon(xPoints, yPoints, 3);

        // Draw door
        g2d.setColor(new Color(150, 150, 150));
        g2d.fillRect(width/2 - 30, height*3/4 - 60, 60, 60);

        // Draw factory name
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Vehicle Factory", width/2 - 60, height/4 - 20);

        // Draw creation particles if animating
        if (isCreating) {
            g2d.setColor(new Color(255, 215, 0));
            for (Point p : particlePoints) {
                g2d.fillOval(p.x, p.y, 5, 5);
            }
        }

        // Draw vehicle if creation is complete
        if (currentStep >= 20) {
            drawVehicle(g2d, currentVehicle, width/2 - 40, height*3/4 + 20);
        }
    }

    private void drawVehicle(Graphics2D g2d, String vehicleType, int x, int y) {
        g2d.setColor(Color.BLUE);
        
        switch (vehicleType) {
            case "Car":
                // Draw car body
                g2d.fillRect(x, y, 80, 30);
                g2d.fillRect(x + 20, y - 20, 40, 20);
                // Draw wheels
                g2d.setColor(Color.BLACK);
                g2d.fillOval(x + 10, y + 20, 20, 20);
                g2d.fillOval(x + 50, y + 20, 20, 20);
                break;
                
            case "Motorcycle":
                // Draw motorcycle body
                g2d.setColor(Color.RED);
                g2d.fillRect(x + 20, y, 40, 15);
                // Draw wheels
                g2d.setColor(Color.BLACK);
                g2d.fillOval(x, y + 10, 30, 30);
                g2d.fillOval(x + 50, y + 10, 30, 30);
                break;
                
            case "Truck":
                // Draw truck body
                g2d.setColor(Color.GREEN);
                g2d.fillRect(x, y, 100, 40);
                g2d.fillRect(x + 10, y - 25, 40, 25);
                // Draw wheels
                g2d.setColor(Color.BLACK);
                g2d.fillOval(x + 10, y + 30, 25, 25);
                g2d.fillOval(x + 65, y + 30, 25, 25);
                break;
        }
    }

    private void startCreationAnimation() {
        currentStep = 0;
        isCreating = true;
        particlePoints.clear();
        animationTimer.start();
    }

    private void updateAnimation() {
        if (currentStep < 20) {
            // Add new particles
            int centerX = displayPanel.getWidth() / 2;
            int centerY = displayPanel.getHeight() * 3/4 - 30;
            
            for (int i = 0; i < 5; i++) {
                double angle = Math.random() * 2 * Math.PI;
                int radius = (int) (Math.random() * 50);
                int x = centerX + (int) (Math.cos(angle) * radius);
                int y = centerY + (int) (Math.sin(angle) * radius);
                particlePoints.add(new Point(x, y));
            }
            
            // Remove old particles
            if (particlePoints.size() > 50) {
                particlePoints.subList(0, 5).clear();
            }
            
            currentStep++;
        } else {
            isCreating = false;
            animationTimer.stop();
            particlePoints.clear();
        }
        
        displayPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VehicleFactoryDemo().setVisible(true);
        });
    }
}
