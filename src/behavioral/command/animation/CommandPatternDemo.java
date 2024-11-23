package behavioral.command.animation;

// Project imports
import behavioral.command.*;

// Java core imports
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

// Swing imports
import javax.swing.*;

/**
 * Interactive demonstration of the Command Pattern
 * Shows how commands encapsulate operations and support undo functionality
 * Includes both animation and UML class diagram visualization
 */
public class CommandPatternDemo extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel mainPanel;
    private JPanel animationPanel;
    private JPanel controlPanel;
    private RemoteControl remoteControl;
    private Light light;
    private Fan fan;
    private Stereo stereo;
    private LightOnCommand lightOn;
    private LightOffCommand lightOff;
    private FanHighCommand fanHigh;
    private FanOffCommand fanOff;
    private StereoOnWithCDCommand stereoOnWithCD;
    private StereoOffCommand stereoOff;
    private MacroCommand partyMacro;
    private boolean showingDiagram = false;
    private Timer animationTimer;
    private VisualEffects visualEffects;
    private double fanAngle = 0;
    private boolean fanSpinning = false;

    public CommandPatternDemo() {
        super("Command Pattern Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        visualEffects = new VisualEffects();
        initializeComponents();
        setupLayout();
        startAnimation();
    }

    private void initializeComponents() {
        light = new Light("Living Room");
        lightOn = new LightOnCommand(light);
        lightOff = new LightOffCommand(light);

        fan = new Fan("Living Room");
        fanHigh = new FanHighCommand(fan);
        fanOff = new FanOffCommand(fan);

        stereo = new Stereo("Living Room");
        stereoOnWithCD = new StereoOnWithCDCommand(stereo);
        stereoOff = new StereoOffCommand(stereo);

        remoteControl = new RemoteControl();
        remoteControl.setCommand(0, lightOn, lightOff);
        remoteControl.setCommand(1, fanHigh, fanOff);
        remoteControl.setCommand(2, stereoOnWithCD, stereoOff);

        Command[] partyOn = { lightOn, fanHigh, stereoOnWithCD };
        partyMacro = new MacroCommand(partyOn);
        remoteControl.setCommand(3, partyMacro, null);

        mainPanel = new JPanel(new BorderLayout());
        animationPanel = createAnimationPanel();
        controlPanel = createControlPanel();
    }

    private JPanel createAnimationPanel() {
        animationPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                drawScene(g2d);
                visualEffects.draw(g2d);
            }
        };
        animationPanel.setPreferredSize(new Dimension(600, 400));
        animationPanel.setBackground(Color.WHITE);

        // Add mouse interaction
        animationPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e.getX(), e.getY());
            }
        });
        return animationPanel;
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton toggleViewButton = new JButton("Toggle UML/Animation");
        toggleViewButton.addActionListener(e -> {
            showingDiagram = !showingDiagram;
            repaint();
        });

        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> {
            remoteControl.undoButtonWasPushed();
            repaint();
        });

        panel.add(toggleViewButton);
        panel.add(undoButton);

        JButton lightOnButton = new JButton("Light On");
        lightOnButton.addActionListener(e -> {
            remoteControl.onButtonWasPushed(0);
            repaint();
        });

        JButton lightOffButton = new JButton("Light Off");
        lightOffButton.addActionListener(e -> {
            remoteControl.offButtonWasPushed(0);
            repaint();
        });

        JButton fanHighButton = new JButton("Fan High");
        fanHighButton.addActionListener(e -> {
            remoteControl.onButtonWasPushed(1);
            repaint();
        });

        JButton fanOffButton = new JButton("Fan Off");
        fanOffButton.addActionListener(e -> {
            remoteControl.offButtonWasPushed(1);
            repaint();
        });

        JButton stereoOnButton = new JButton("Stereo On");
        stereoOnButton.addActionListener(e -> {
            remoteControl.onButtonWasPushed(2);
            repaint();
        });

        JButton stereoOffButton = new JButton("Stereo Off");
        stereoOffButton.addActionListener(e -> {
            remoteControl.offButtonWasPushed(2);
            repaint();
        });

        JButton partyModeButton = new JButton("Party Mode");
        partyModeButton.addActionListener(e -> {
            remoteControl.onButtonWasPushed(3);
            repaint();
        });

        panel.add(lightOnButton);
        panel.add(lightOffButton);
        panel.add(fanHighButton);
        panel.add(fanOffButton);
        panel.add(stereoOnButton);
        panel.add(stereoOffButton);
        panel.add(partyModeButton);

        return panel;
    }

    private void setupLayout() {
        mainPanel.add(animationPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private void startAnimation() {
        animationTimer = new Timer(20, e -> {
            if (fanSpinning) {
                fanAngle += 0.1;
            }
            visualEffects.update();
            repaint();
        });
        animationTimer.start();
    }

    private void handleMouseClick(int x, int y) {
        // Light area
        if (x >= 100 && x <= 160 && y >= 100 && y <= 160) {
            if (!light.isOn()) {
                remoteControl.onButtonWasPushed(0);
                visualEffects.addSparkle(130, 130);
                SoundManager.playClickSound();
            } else {
                remoteControl.offButtonWasPushed(0);
                visualEffects.addFade(100, 100, 60, 60, Color.YELLOW);
                SoundManager.playClickSound();
            }
        }
        // Fan area
        else if (x >= 270 && x <= 330 && y >= 90 && y <= 150) {
            if (fan.getSpeed() != Fan.HIGH) {
                remoteControl.onButtonWasPushed(1);
                fanSpinning = true;
                visualEffects.addRipple(300, 120);
                SoundManager.playFanSound();
            } else {
                remoteControl.offButtonWasPushed(1);
                fanSpinning = false;
                visualEffects.addFade(270, 90, 60, 60, Color.GRAY);
                SoundManager.playClickSound();
            }
        }
        // Stereo area
        else if (x >= 450 && x <= 530 && y >= 150 && y <= 190) {
            if (!stereo.isOn()) {
                remoteControl.onButtonWasPushed(2);
                visualEffects.addSparkle(490, 170);
                SoundManager.playStereoSound();
            } else {
                remoteControl.offButtonWasPushed(2);
                visualEffects.addFade(450, 150, 80, 40, Color.DARK_GRAY);
                SoundManager.playClickSound();
            }
        }
    }

    private void drawScene(Graphics2D g) {
        // Draw room outline with shadow
        g.setColor(new Color(240, 240, 240));
        g.fillRect(45, 45, 510, 310);
        g.setColor(Color.BLACK);
        g.drawRect(50, 50, 500, 300);

        // Draw and animate light with glow effect
        if (light.isOn()) {
            // Draw light glow
            GradientPaint glowGradient = new GradientPaint(
                130, 130, new Color(255, 255, 200, 100),
                130, 200, new Color(255, 255, 200, 0)
            );
            g.setPaint(glowGradient);
            g.fillOval(80, 80, 100, 100);
            
            g.setColor(Color.YELLOW);
            g.fillOval(100, 100, 60, 60);
        }
        g.setColor(Color.BLACK);
        g.drawOval(100, 100, 60, 60);
        g.drawString("Light", 110, 180);

        // Draw and animate fan with smooth rotation
        g.setColor(Color.GRAY);
        int fanX = 300;
        int fanY = 120;
        g.drawOval(fanX-30, fanY-30, 60, 60);
        
        if (fanSpinning) {
            fanAngle += 0.3; // Smooth rotation
            for (int i = 0; i < 4; i++) {
                double bladeAngle = fanAngle + (i * Math.PI / 2);
                int x2 = (int) (fanX + 25 * Math.cos(bladeAngle));
                int y2 = (int) (fanY + 25 * Math.sin(bladeAngle));
                g.drawLine(fanX, fanY, x2, y2);
            }
        } else {
            // Static fan blades
            g.drawLine(fanX-20, fanY, fanX+20, fanY);
            g.drawLine(fanX, fanY-20, fanX, fanY+20);
        }
        g.drawString("Fan", fanX-10, fanY+50);

        // Draw and animate stereo with LED display
        int stereoX = 450;
        int stereoY = 150;
        GradientPaint stereoPaint = new GradientPaint(
            stereoX, stereoY, Color.DARK_GRAY,
            stereoX, stereoY + 40, Color.GRAY
        );
        g.setPaint(stereoPaint);
        g.fillRect(stereoX, stereoY, 80, 40);
        g.setColor(Color.BLACK);
        g.drawRect(stereoX, stereoY, 80, 40);

        if (stereo.isOn()) {
            // Draw LED display
            g.setColor(Color.BLACK);
            g.fillRect(stereoX + 10, stereoY + 10, 60, 20);
            g.setColor(Color.GREEN);
            g.fillOval(stereoX + 15, stereoY + 15, 10, 10);
            g.setFont(new Font("Monospaced", Font.PLAIN, 12));
            g.drawString(stereo.getSource(), stereoX + 30, stereoY + 25);
        }
        g.setColor(Color.BLACK);
        g.drawString("Stereo", stereoX + 20, stereoY + 60);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CommandPatternDemo().setVisible(true);
        });
    }
}
