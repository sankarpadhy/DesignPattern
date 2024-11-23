package behavioral.state.animation;

// Project imports
import behavioral.state.*;

// Java core imports
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

// Swing imports
import javax.swing.*;

/**
 * Interactive animation demonstrating the State Pattern using a Vending Machine example
 */
public class VendingMachineDemo extends JPanel {
    private VendingMachine vendingMachine;
    private List<Product> products;
    private Rectangle2D insertCoinButton;
    private Rectangle2D ejectCoinButton;
    private Rectangle2D turnCrankButton;
    private List<Rectangle2D> productButtons;
    private String message = "";
    private int selectedProduct = -1;
    private Timer messageTimer;
    private List<Particle> particles;
    private Timer animationTimer;
    private double rotationAngle = 0;
    private boolean dispensing = false;

    private static class Product {
        String name;
        double price;
        Rectangle2D bounds;
        int quantity;

        Product(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }
    }

    private static class Particle {
        double x, y;
        double vx, vy;
        double life;
        Color color;

        Particle(double x, double y, Color color) {
            this.x = x;
            this.y = y;
            double angle = Math.random() * Math.PI * 2;
            double speed = 1 + Math.random() * 2;
            this.vx = Math.cos(angle) * speed;
            this.vy = Math.sin(angle) * speed;
            this.life = 1.0;
            this.color = color;
        }

        void update() {
            x += vx;
            y += vy;
            vy += 0.1; // gravity
            life -= 0.02;
        }
    }

    public VendingMachineDemo() {
        vendingMachine = new VendingMachine();
        products = new ArrayList<>();
        products.add(new Product("Cola", 1.00, 5));
        products.add(new Product("Chips", 0.75, 3));
        products.add(new Product("Candy", 0.50, 4));
        productButtons = new ArrayList<>();
        particles = new ArrayList<>();

        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                if (insertCoinButton.contains(p)) {
                    vendingMachine.insertCoin();
                    showMessage(vendingMachine.getCurrentState().toString());
                    createParticles(insertCoinButton.getCenterX(), insertCoinButton.getCenterY(), Color.YELLOW);
                } else if (ejectCoinButton.contains(p)) {
                    vendingMachine.ejectCoin();
                    showMessage(vendingMachine.getCurrentState().toString());
                    createParticles(ejectCoinButton.getCenterX(), ejectCoinButton.getCenterY(), Color.ORANGE);
                } else if (turnCrankButton.contains(p) && selectedProduct >= 0) {
                    vendingMachine.turnCrank();
                    if (vendingMachine.getCurrentState() instanceof DispensingState) {
                        dispensing = true;
                        products.get(selectedProduct).quantity--;
                        showMessage("Dispensing " + products.get(selectedProduct).name);
                        createParticles(turnCrankButton.getCenterX(), turnCrankButton.getCenterY(), Color.GREEN);
                    } else {
                        showMessage(vendingMachine.getCurrentState().toString());
                    }
                } else {
                    for (int i = 0; i < productButtons.size(); i++) {
                        if (productButtons.get(i).contains(p)) {
                            selectedProduct = i;
                            showMessage("Selected: " + products.get(i).name + " ($" + products.get(i).price + ")");
                            createParticles(productButtons.get(i).getCenterX(), productButtons.get(i).getCenterY(), Color.CYAN);
                            break;
                        }
                    }
                }
                repaint();
            }
        });

        messageTimer = new Timer(2000, e -> {
            message = "";
            repaint();
        });
        messageTimer.setRepeats(false);

        animationTimer = new Timer(16, e -> {
            if (dispensing) {
                rotationAngle += 0.1;
                if (rotationAngle >= Math.PI * 2) {
                    rotationAngle = 0;
                    dispensing = false;
                    vendingMachine.setState(new NoCoinState(vendingMachine));
                }
            }
            
            // Update particles
            for (int i = particles.size() - 1; i >= 0; i--) {
                Particle p = particles.get(i);
                p.update();
                if (p.life <= 0) {
                    particles.remove(i);
                }
            }
            repaint();
        });
        animationTimer.start();
    }

    private void createParticles(double x, double y, Color baseColor) {
        for (int i = 0; i < 20; i++) {
            particles.add(new Particle(x, y, baseColor));
        }
    }

    private void showMessage(String msg) {
        message = msg;
        messageTimer.restart();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw vending machine outline
        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(50, 50, 500, 300, 20, 20);

        // Draw product slots
        int x = 70;
        int y = 80;
        productButtons.clear();
        for (Product product : products) {
            Rectangle2D slot = new Rectangle2D.Double(x, y, 100, 60);
            productButtons.add(slot);
            product.bounds = slot;

            if (product == products.get(selectedProduct)) {
                g2d.setColor(new Color(200, 200, 255));
                g2d.fill(slot);
            }
            g2d.setColor(Color.BLACK);
            g2d.draw(slot);
            g2d.drawString(product.name, x + 10, y + 20);
            g2d.drawString("$" + String.format("%.2f", product.price), x + 10, y + 35);
            g2d.drawString("Qty: " + product.quantity, x + 10, y + 50);
            x += 120;
        }

        // Draw control buttons
        y = 180;
        insertCoinButton = new Rectangle2D.Double(70, y, 100, 40);
        ejectCoinButton = new Rectangle2D.Double(190, y, 100, 40);
        turnCrankButton = new Rectangle2D.Double(310, y, 100, 40);

        // Draw buttons with gradients
        GradientPaint gradient = new GradientPaint(0, y, new Color(100, 100, 255), 
                                                 0, y + 40, new Color(50, 50, 200));
        g2d.setPaint(gradient);
        g2d.fill(insertCoinButton);
        g2d.fill(ejectCoinButton);
        g2d.fill(turnCrankButton);

        g2d.setColor(Color.WHITE);
        g2d.drawString("Insert Coin", 85, y + 25);
        g2d.drawString("Eject Coin", 205, y + 25);
        g2d.drawString("Turn Crank", 325, y + 25);

        // Draw dispensing animation
        if (dispensing && selectedProduct >= 0) {
            Product product = products.get(selectedProduct);
            AffineTransform old = g2d.getTransform();
            g2d.rotate(rotationAngle, product.bounds.getCenterX(), product.bounds.getCenterY());
            g2d.setColor(Color.GREEN);
            g2d.draw(product.bounds);
            g2d.setTransform(old);
        }

        // Draw particles
        for (Particle p : particles) {
            g2d.setColor(new Color(p.color.getRed(), p.color.getGreen(), 
                                 p.color.getBlue(), (int)(p.life * 255)));
            g2d.fill(new Ellipse2D.Double(p.x - 2, p.y - 2, 4, 4));
        }

        // Draw message
        if (!message.isEmpty()) {
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 16));
            FontMetrics fm = g2d.getFontMetrics();
            int messageWidth = fm.stringWidth(message);
            g2d.drawString(message, (getWidth() - messageWidth) / 2, 280);
        }

        // Draw current state
        g2d.setColor(Color.DARK_GRAY);
        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        String stateText = "Current State: " + vendingMachine.getCurrentState().getClass().getSimpleName();
        g2d.drawString(stateText, 70, 320);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Vending Machine - State Pattern Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new VendingMachineDemo());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
