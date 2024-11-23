package structural.decorator.animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextAnimationDemo extends JFrame {
    private JLabel textLabel;
    private Timer timer;
    private int step = 0;
    private String[] steps = {
        "Hello, World!",
        "<b>Hello, World!</b>",
        "<i><b>Hello, World!</b></i>",
        "<u><i><b>Hello, World!</b></i></u>"
    };
    private String[] descriptions = {
        "Plain Text",
        "Bold Decorator Added",
        "Italic Decorator Added",
        "Underline Decorator Added"
    };

    public TextAnimationDemo() {
        setTitle("Decorator Pattern Animation");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create main panel with black background
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create text label
        textLabel = new JLabel(steps[0], SwingConstants.CENTER);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        textLabel.setForeground(Color.GREEN);
        mainPanel.add(textLabel, BorderLayout.CENTER);

        // Create description label
        JLabel descLabel = new JLabel(descriptions[0], SwingConstants.CENTER);
        descLabel.setForeground(Color.WHITE);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        mainPanel.add(descLabel, BorderLayout.SOUTH);

        // Add control buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.BLACK);
        
        JButton startButton = new JButton("Start Animation");
        startButton.addActionListener(e -> startAnimation());
        
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetAnimation());
        
        buttonPanel.add(startButton);
        buttonPanel.add(resetButton);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        add(mainPanel);

        // Create timer for animation
        timer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                step++;
                if (step < steps.length) {
                    textLabel.setText(steps[step]);
                    descLabel.setText(descriptions[step]);
                } else {
                    timer.stop();
                }
            }
        });

        // Center the frame
        setLocationRelativeTo(null);
    }

    private void startAnimation() {
        if (!timer.isRunning()) {
            if (step >= steps.length - 1) {
                resetAnimation();
            }
            timer.start();
        }
    }

    private void resetAnimation() {
        timer.stop();
        step = 0;
        textLabel.setText(steps[0]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TextAnimationDemo().setVisible(true);
        });
    }
}
