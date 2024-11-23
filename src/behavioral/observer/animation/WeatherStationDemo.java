package behavioral.observer.animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeatherStationDemo extends JFrame {
    private JPanel weatherStation;
    private List<ObserverPanel> observers;
    private Timer weatherTimer;
    private Timer updateTimer;
    private double temperature = 25.0;
    private double humidity = 60.0;
    private double pressure = 1013.0;
    private Random random = new Random();

    private class ObserverPanel extends JPanel {
        private String name;
        private Color color;
        private Point position;
        private List<Point> dataPoints;
        private boolean isReceivingUpdate = false;
        private int animationStep = 0;

        public ObserverPanel(String name, Color color, Point position) {
            this.name = name;
            this.color = color;
            this.position = position;
            this.dataPoints = new ArrayList<>();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw observer
            g2d.setColor(color);
            g2d.fillRect(position.x, position.y, 100, 60);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(position.x, position.y, 100, 60);
            g2d.drawString(name, position.x + 10, position.y + 35);

            // Draw data points if receiving update
            if (isReceivingUpdate) {
                g2d.setColor(color);
                for (Point p : dataPoints) {
                    g2d.fillOval(p.x, p.y, 5, 5);
                }
            }
        }

        public void startUpdate() {
            isReceivingUpdate = true;
            animationStep = 0;
            dataPoints.clear();
        }

        public void updateAnimation() {
            if (isReceivingUpdate) {
                if (animationStep < 10) {
                    // Calculate path from weather station to observer
                    int startX = getWidth() / 2;
                    int startY = 50;
                    int stepX = (position.x + 50 - startX) / 10;
                    int stepY = (position.y - startY) / 10;
                    
                    dataPoints.add(new Point(
                        startX + stepX * animationStep,
                        startY + stepY * animationStep
                    ));
                    
                    animationStep++;
                } else {
                    isReceivingUpdate = false;
                    dataPoints.clear();
                }
            }
        }
    }

    public WeatherStationDemo() {
        setTitle("Weather Station Observer Pattern Animation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create main panel with card layout
        JPanel mainPanel = new JPanel(new CardLayout());
        mainPanel.add(buildAnimationPanel(), "animation");
        mainPanel.add(buildDiagramPanel(), "diagram");
        add(mainPanel, BorderLayout.CENTER);

        // Initialize observers
        observers = new ArrayList<>();
        observers.add(new ObserverPanel("Temperature\nDisplay", Color.RED, new Point(50, 300)));
        observers.add(new ObserverPanel("Humidity\nDisplay", Color.BLUE, new Point(350, 300)));
        observers.add(new ObserverPanel("Pressure\nDisplay", Color.GREEN, new Point(650, 300)));

        // Create control panel
        JPanel controlPanel = new JPanel();
        JButton updateButton = new JButton("Manual Update");
        updateButton.addActionListener(e -> triggerUpdate());
        JToggleButton autoButton = new JToggleButton("Auto Update");
        autoButton.addActionListener(e -> {
            if (autoButton.isSelected()) {
                weatherTimer.start();
            } else {
                weatherTimer.stop();
            }
        });
        controlPanel.add(updateButton);
        controlPanel.add(autoButton);

        // Add toggle button
        JButton toggleButton = new JButton("Show Class Diagram");
        toggleButton.addActionListener(e -> {
            boolean showingDiagram = toggleButton.getText().equals("Show Animation");
            toggleButton.setText(showingDiagram ? "Show Class Diagram" : "Show Animation");
            CardLayout cl = (CardLayout) mainPanel.getLayout();
            cl.show(mainPanel, showingDiagram ? "animation" : "diagram");
        });
        controlPanel.add(toggleButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Create timers
        weatherTimer = new Timer(3000, e -> triggerUpdate());
        updateTimer = new Timer(50, e -> updateObservers());

        // Display current values
        JPanel valuesPanel = new JPanel();
        valuesPanel.add(new JLabel("Current Values:"));
        add(valuesPanel, BorderLayout.NORTH);

        // Center the frame
        setLocationRelativeTo(null);
    }

    private JPanel buildAnimationPanel() {
        JPanel animationPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawWeatherStation(g);
            }
        };
        animationPanel.setBackground(Color.WHITE);
        return animationPanel;
    }

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

    private void drawWeatherStation(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw weather station
        int centerX = getWidth() / 2;
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(centerX - 50, 20, 100, 60);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Weather Station", centerX - 40, 50);
        g2d.drawString(String.format("%.1f°C", temperature), centerX - 40, 65);

        // Draw observers
        for (ObserverPanel observer : observers) {
            observer.paintComponent(g2d);
        }
    }

    private void drawClassDiagram(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set up fonts
        Font titleFont = new Font("Arial", Font.BOLD, 14);
        Font normalFont = new Font("Arial", Font.PLAIN, 12);
        g2d.setFont(titleFont);

        // Draw Subject interface
        drawInterface(g2d, "WeatherSubject",
                     new String[]{"+ registerObserver(Observer)",
                                "+ removeObserver(Observer)",
                                "+ notifyObservers()"},
                     200, 100, 250, 120);

        // Draw Observer interface
        drawInterface(g2d, "WeatherObserver",
                     new String[]{"+ update(temperature, humidity, pressure)"},
                     600, 100, 250, 80);

        // Draw Concrete Subject
        drawClass(g2d, "WeatherStation",
                 new String[]{"- observers: List<Observer>",
                            "- temperature: float",
                            "- humidity: float",
                            "- pressure: float"},
                 new String[]{"+ registerObserver(Observer)",
                            "+ removeObserver(Observer)",
                            "+ notifyObservers()",
                            "+ setMeasurements(...)"},
                 200, 300, 250, 200);

        // Draw Concrete Observers
        drawClass(g2d, "TemperatureDisplay",
                 new String[]{"- temperature: float"},
                 new String[]{"+ update(temperature, humidity, pressure)"},
                 500, 300, 200, 100);

        drawClass(g2d, "HumidityDisplay",
                 new String[]{"- humidity: float"},
                 new String[]{"+ update(temperature, humidity, pressure)"},
                 500, 420, 200, 100);

        drawClass(g2d, "PressureDisplay",
                 new String[]{"- pressure: float"},
                 new String[]{"+ update(temperature, humidity, pressure)"},
                 500, 540, 200, 100);

        // Draw relationships
        drawArrow(g2d, 325, 300, 325, 220); // WeatherStation to WeatherSubject
        drawArrow(g2d, 500, 350, 725, 180); // TemperatureDisplay to Observer
        drawArrow(g2d, 500, 470, 725, 180); // HumidityDisplay to Observer
        drawArrow(g2d, 500, 590, 725, 180); // PressureDisplay to Observer
        drawDependencyArrow(g2d, 450, 150, 600, 150); // Subject to Observer

        // Add pattern description
        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        g2d.drawString("Observer Pattern:", 50, 50);
        g2d.drawString("Defines one-to-many dependency between objects so that when one", 50, 70);
        g2d.drawString("object changes state, all its dependents are notified and updated automatically.", 50, 90);
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

    private void drawDependencyArrow(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        float[] dash = {5.0f};
        g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 
                                    10.0f, dash, 0.0f));
        drawArrow(g2d, x1, y1, x2, y2);
        g2d.setStroke(new BasicStroke(1.0f));
    }

    private void triggerUpdate() {
        // Update weather values
        temperature += random.nextDouble() * 2 - 1;  // -1 to +1
        humidity += random.nextDouble() * 5 - 2.5;   // -2.5 to +2.5
        pressure += random.nextDouble() * 3 - 1.5;   // -1.5 to +1.5

        // Start update animation for all observers
        for (ObserverPanel observer : observers) {
            observer.startUpdate();
        }
        updateTimer.start();
        repaint();
    }

    private void updateObservers() {
        boolean stillAnimating = false;
        for (ObserverPanel observer : observers) {
            observer.updateAnimation();
            if (observer.isReceivingUpdate) {
                stillAnimating = true;
            }
        }
        
        if (!stillAnimating) {
            updateTimer.stop();
        }
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WeatherStationDemo().setVisible(true);
        });
    }
}
