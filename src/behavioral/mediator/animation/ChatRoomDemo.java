package behavioral.mediator.animation;

// Project imports
import behavioral.mediator.*;

// Java core imports
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import java.util.List;

// Swing imports
import javax.swing.*;

/**
 * Interactive animation demonstrating the Mediator Pattern using a chat room example
 */
public class ChatRoomDemo extends JPanel {
    private ChatRoom chatRoom;
    private List<UserNode> userNodes;
    private List<MessageAnimation> messageAnimations;
    private javax.swing.Timer animationTimer;
    private JTextField messageField;
    private JComboBox<String> senderCombo;
    private DefaultComboBoxModel<String> senderModel;
    private Random random;

    private static class UserNode {
        ChatUser user;
        Point2D position;
        Color color;
        double angle;

        UserNode(ChatUser user, Point2D position, Color color) {
            this.user = user;
            this.position = position;
            this.color = color;
            this.angle = 0;
        }
    }

    private static class MessageAnimation {
        Point2D start;
        Point2D end;
        Point2D current;
        Color color;
        String message;
        double progress;
        double speed;

        MessageAnimation(Point2D start, Point2D end, Color color, String message) {
            this.start = (Point2D) start.clone();
            this.end = (Point2D) end.clone();
            this.current = (Point2D) start.clone();
            this.color = color;
            this.message = message;
            this.progress = 0;
            this.speed = 0.02;
        }

        void update() {
            progress = Math.min(1, progress + speed);
            current.setLocation(
                start.getX() + (end.getX() - start.getX()) * progress,
                start.getY() + (end.getY() - start.getY()) * progress
            );
        }

        boolean isComplete() {
            return progress >= 1;
        }
    }

    public ChatRoomDemo() {
        chatRoom = new ChatRoom();
        userNodes = new ArrayList<>();
        messageAnimations = new ArrayList<>();
        random = new Random();

        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Create users
        createUsers();

        // Create control panel
        JPanel controlPanel = new JPanel();
        senderModel = new DefaultComboBoxModel<>();
        for (UserNode node : userNodes) {
            senderModel.addElement(node.user.getName());
        }
        senderCombo = new JComboBox<>(senderModel);
        messageField = new JTextField(20);
        JButton sendButton = new JButton("Send Message");

        sendButton.addActionListener(e -> {
            String sender = (String) senderCombo.getSelectedItem();
            String message = messageField.getText().trim();
            if (!message.isEmpty()) {
                UserNode senderNode = findUserNode(sender);
                if (senderNode != null) {
                    // Send message to all other users
                    for (UserNode targetNode : userNodes) {
                        if (targetNode != senderNode) {
                            messageAnimations.add(new MessageAnimation(
                                senderNode.position,
                                targetNode.position,
                                senderNode.color,
                                message
                            ));
                        }
                    }
                    senderNode.user.send(message);
                    messageField.setText("");
                }
            }
        });

        controlPanel.add(new JLabel("Sender:"));
        controlPanel.add(senderCombo);
        controlPanel.add(new JLabel("Message:"));
        controlPanel.add(messageField);
        controlPanel.add(sendButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Animation timer
        animationTimer = new javax.swing.Timer(16, e -> {
            // Update user animations
            for (UserNode node : userNodes) {
                node.angle += 0.02;
                double wobble = Math.sin(node.angle) * 2;
                node.position.setLocation(
                    node.position.getX(),
                    node.position.getY() + wobble
                );
            }

            // Update message animations
            for (int i = messageAnimations.size() - 1; i >= 0; i--) {
                MessageAnimation anim = messageAnimations.get(i);
                anim.update();
                if (anim.isComplete()) {
                    messageAnimations.remove(i);
                }
            }

            repaint();
        });
        animationTimer.start();
    }

    private void createUsers() {
        String[] names = {"Alice", "Bob", "Charlie", "David", "Eve"};
        Color[] colors = {
            new Color(100, 200, 100),
            new Color(200, 100, 100),
            new Color(100, 100, 200),
            new Color(200, 200, 100),
            new Color(200, 100, 200)
        };

        int centerX = 400;
        int centerY = 250;
        int radius = 150;
        int numUsers = names.length;

        for (int i = 0; i < numUsers; i++) {
            double angle = 2 * Math.PI * i / numUsers;
            Point2D position = new Point2D.Double(
                centerX + Math.cos(angle) * radius,
                centerY + Math.sin(angle) * radius
            );
            ChatUser user = new ChatUser(chatRoom, names[i]);
            userNodes.add(new UserNode(user, position, colors[i]));
            chatRoom.addUser(user);
        }
    }

    private UserNode findUserNode(String name) {
        return userNodes.stream()
            .filter(node -> node.user.getName().equals(name))
            .findFirst()
            .orElse(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw connections between users
        g2d.setColor(new Color(200, 200, 200));
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 
                                    0, new float[]{5}, 0));
        for (int i = 0; i < userNodes.size(); i++) {
            UserNode node1 = userNodes.get(i);
            for (int j = i + 1; j < userNodes.size(); j++) {
                UserNode node2 = userNodes.get(j);
                g2d.draw(new Line2D.Double(node1.position, node2.position));
            }
        }

        // Draw message animations
        g2d.setStroke(new BasicStroke(2));
        for (MessageAnimation anim : messageAnimations) {
            // Draw message path
            g2d.setColor(new Color(
                anim.color.getRed(),
                anim.color.getGreen(),
                anim.color.getBlue(),
                100));
            g2d.draw(new Line2D.Double(anim.start, anim.end));

            // Draw message bubble
            g2d.setColor(anim.color);
            double size = 30;
            Shape bubble = new RoundRectangle2D.Double(
                anim.current.getX() - size/2,
                anim.current.getY() - size/2,
                size, size, 10, 10);
            g2d.fill(bubble);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            g2d.drawString("✉", (float)anim.current.getX() - 6, 
                               (float)anim.current.getY() + 5);
        }

        // Draw users
        for (UserNode node : userNodes) {
            // Draw user circle
            double size = 60;
            Ellipse2D circle = new Ellipse2D.Double(
                node.position.getX() - size/2,
                node.position.getY() - size/2,
                size, size);
            
            // Fill with gradient
            Point2D center = new Point2D.Double(
                node.position.getX(),
                node.position.getY());
            float[] dist = {0.0f, 0.8f};
            Color[] colors = {
                node.color,
                new Color(
                    Math.min(255, node.color.getRed() + 50),
                    Math.min(255, node.color.getGreen() + 50),
                    Math.min(255, node.color.getBlue() + 50)
                )
            };
            RadialGradientPaint gradient = new RadialGradientPaint(
                center, (float)size/2, dist, colors);
            g2d.setPaint(gradient);
            g2d.fill(circle);
            
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(2));
            g2d.draw(circle);

            // Draw user name
            g2d.setFont(new Font("Arial", Font.BOLD, 14));
            FontMetrics fm = g2d.getFontMetrics();
            String name = node.user.getName();
            int textWidth = fm.stringWidth(name);
            g2d.drawString(name,
                (float)(node.position.getX() - textWidth/2),
                (float)(node.position.getY() + 5));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Chat Room - Mediator Pattern Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new ChatRoomDemo());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
