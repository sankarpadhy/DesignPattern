package behavioral.visitor.animation;

// Project imports
import behavioral.visitor.*;

// Java core imports
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

// Swing imports
import javax.swing.*;

/**
 * Interactive animation demonstrating the Visitor Pattern using a document processing example
 */
public class DocumentVisitorDemo extends JPanel {
    private DocumentCollection documents;
    private List<DocumentNode> documentNodes;
    private DocumentVisitor currentVisitor;
    private String message = "";
    private Timer messageTimer;
    private Point2D visitorPosition;
    private DocumentNode targetNode;
    private Timer animationTimer;
    private List<VisitEffect> visitEffects;

    private static class DocumentNode {
        DocumentElement document;
        Rectangle2D bounds;
        Color color;
        String type;

        DocumentNode(DocumentElement doc, Rectangle2D bounds, Color color, String type) {
            this.document = doc;
            this.bounds = bounds;
            this.color = color;
            this.type = type;
        }
    }

    private static class VisitEffect {
        Point2D position;
        double size;
        double life;
        Color color;

        VisitEffect(Point2D pos, Color color) {
            this.position = (Point2D) pos.clone();
            this.size = 1;
            this.life = 1.0;
            this.color = color;
        }

        void update() {
            size += 2;
            life -= 0.05;
        }
    }

    public DocumentVisitorDemo() {
        // Create document collection
        documents = new DocumentCollection();
        documents.addDocument(new TextDocument("Report.txt", "Annual Report Content"));
        documents.addDocument(new ImageDocument("Logo.png", 800, 600));
        documents.addDocument(new SpreadsheetDocument("Data.xlsx", 100, 50));

        documentNodes = new ArrayList<>();
        visitEffects = new ArrayList<>();
        
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);

        // Create visitor position for animation
        visitorPosition = new Point2D.Double(50, 300);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                Rectangle2D exportButton = new Rectangle2D.Double(50, 500, 150, 40);
                Rectangle2D statsButton = new Rectangle2D.Double(220, 500, 150, 40);

                if (exportButton.contains(p)) {
                    currentVisitor = new DocumentExportVisitor();
                    showMessage("Starting Export Visitor");
                    startVisitorAnimation();
                } else if (statsButton.contains(p)) {
                    currentVisitor = new DocumentStatisticsVisitor();
                    showMessage("Starting Statistics Visitor");
                    startVisitorAnimation();
                }
            }
        });

        messageTimer = new Timer(2000, e -> {
            message = "";
            repaint();
        });
        messageTimer.setRepeats(false);

        animationTimer = new Timer(16, e -> {
            if (targetNode != null) {
                double dx = targetNode.bounds.getCenterX() - visitorPosition.getX();
                double dy = targetNode.bounds.getCenterY() - visitorPosition.getY();
                double distance = Math.sqrt(dx * dx + dy * dy);

                if (distance < 5) {
                    // Visit the node
                    targetNode.document.accept(currentVisitor);
                    visitEffects.add(new VisitEffect(visitorPosition, targetNode.color));
                    showMessage("Visiting " + targetNode.type);

                    // Find next node
                    targetNode = findNextNode();
                    if (targetNode == null) {
                        showMessage(getVisitorResult());
                    }
                } else {
                    // Move towards target
                    double speed = 5.0;
                    visitorPosition.setLocation(
                        visitorPosition.getX() + (dx / distance) * speed,
                        visitorPosition.getY() + (dy / distance) * speed
                    );
                }
            }

            // Update effects
            for (int i = visitEffects.size() - 1; i >= 0; i--) {
                VisitEffect effect = visitEffects.get(i);
                effect.update();
                if (effect.life <= 0) {
                    visitEffects.remove(i);
                }
            }

            repaint();
        });
        animationTimer.start();

        // Create document nodes
        createDocumentNodes();
    }

    private void createDocumentNodes() {
        int x = 200;
        int y = 200;
        for (DocumentElement doc : documents.getDocuments()) {
            Color color;
            String type;
            if (doc instanceof TextDocument) {
                color = new Color(100, 200, 100);
                type = "Text Document";
            } else if (doc instanceof ImageDocument) {
                color = new Color(200, 100, 100);
                type = "Image Document";
            } else {
                color = new Color(100, 100, 200);
                type = "Spreadsheet Document";
            }
            documentNodes.add(new DocumentNode(doc, 
                new Rectangle2D.Double(x, y, 120, 80), color, type));
            x += 200;
        }
    }

    private void startVisitorAnimation() {
        visitorPosition.setLocation(50, 300);
        targetNode = findNextNode();
    }

    private DocumentNode findNextNode() {
        for (DocumentNode node : documentNodes) {
            if (!visitEffects.stream().anyMatch(e -> 
                node.bounds.contains(e.position))) {
                return node;
            }
        }
        return null;
    }

    private String getVisitorResult() {
        if (currentVisitor instanceof DocumentExportVisitor) {
            return "Export Complete!";
        } else if (currentVisitor instanceof DocumentStatisticsVisitor) {
            DocumentStatisticsVisitor stats = (DocumentStatisticsVisitor) currentVisitor;
            return String.format("Stats: %d words, %d pixels, %d cells", 
                stats.getTotalWords(), stats.getTotalPixels(), stats.getTotalCells());
        }
        return "";
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

        // Draw document nodes
        for (DocumentNode node : documentNodes) {
            g2d.setColor(node.color);
            g2d.fill(node.bounds);
            g2d.setColor(Color.BLACK);
            g2d.draw(node.bounds);
            
            // Draw document type
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(node.type);
            g2d.drawString(node.type, 
                (float)(node.bounds.getCenterX() - textWidth/2),
                (float)(node.bounds.getCenterY() + 5));
        }

        // Draw visitor
        if (currentVisitor != null) {
            g2d.setColor(Color.BLUE);
            double size = 20;
            Ellipse2D visitor = new Ellipse2D.Double(
                visitorPosition.getX() - size/2,
                visitorPosition.getY() - size/2,
                size, size);
            g2d.fill(visitor);
        }

        // Draw visit effects
        for (VisitEffect effect : visitEffects) {
            g2d.setColor(new Color(
                effect.color.getRed(),
                effect.color.getGreen(),
                effect.color.getBlue(),
                (int)(effect.life * 128)));
            double size = effect.size;
            Ellipse2D circle = new Ellipse2D.Double(
                effect.position.getX() - size/2,
                effect.position.getY() - size/2,
                size, size);
            g2d.draw(circle);
        }

        // Draw buttons
        g2d.setColor(new Color(100, 100, 255));
        g2d.fill(new Rectangle2D.Double(50, 500, 150, 40));
        g2d.fill(new Rectangle2D.Double(220, 500, 150, 40));
        g2d.setColor(Color.WHITE);
        g2d.drawString("Export Documents", 70, 525);
        g2d.drawString("Collect Statistics", 240, 525);

        // Draw message
        if (!message.isEmpty()) {
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 16));
            FontMetrics fm = g2d.getFontMetrics();
            int messageWidth = fm.stringWidth(message);
            g2d.drawString(message, (getWidth() - messageWidth) / 2, 450);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Document Visitor Pattern Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new DocumentVisitorDemo());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
