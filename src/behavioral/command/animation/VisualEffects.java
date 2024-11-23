package behavioral.command.animation;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Manages visual effects for the Command Pattern animation
 */
public class VisualEffects {
    private List<Effect> activeEffects = new ArrayList<>();
    
    /**
     * Updates all active effects and removes completed ones
     */
    public void update() {
        Iterator<Effect> it = activeEffects.iterator();
        while (it.hasNext()) {
            Effect effect = it.next();
            effect.update();
            if (effect.isComplete()) {
                it.remove();
            }
        }
    }
    
    /**
     * Draws all active effects
     * @param g2d Graphics context
     */
    public void draw(Graphics2D g2d) {
        for (Effect effect : activeEffects) {
            effect.draw(g2d);
        }
    }
    
    /**
     * Adds a ripple effect at the specified location
     */
    public void addRipple(int x, int y) {
        activeEffects.add(new RippleEffect(x, y));
    }
    
    /**
     * Adds a sparkle effect at the specified location
     */
    public void addSparkle(int x, int y) {
        activeEffects.add(new SparkleEffect(x, y));
    }
    
    /**
     * Adds a fade effect at the specified location
     */
    public void addFade(int x, int y, int width, int height, Color color) {
        activeEffects.add(new FadeEffect(x, y, width, height, color));
    }

    private abstract class Effect {
        protected int x, y;
        protected int lifetime;
        protected int maxLifetime;
        
        public Effect(int x, int y, int maxLifetime) {
            this.x = x;
            this.y = y;
            this.maxLifetime = maxLifetime;
            this.lifetime = 0;
        }
        
        public void update() {
            lifetime++;
        }
        
        public boolean isComplete() {
            return lifetime >= maxLifetime;
        }
        
        public abstract void draw(Graphics2D g2d);
        
        protected float getProgress() {
            return (float)lifetime / maxLifetime;
        }
    }
    
    private class RippleEffect extends Effect {
        private static final int MAX_RADIUS = 50;
        
        public RippleEffect(int x, int y) {
            super(x, y, 20);
        }
        
        @Override
        public void draw(Graphics2D g2d) {
            float progress = getProgress();
            int radius = (int)(MAX_RADIUS * progress);
            float alpha = 1.0f - progress;
            
            g2d.setColor(new Color(1f, 1f, 1f, alpha));
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(x - radius, y - radius, radius * 2, radius * 2);
        }
    }
    
    private class SparkleEffect extends Effect {
        private static final int SPARKLE_COUNT = 8;
        private static final int MAX_DISTANCE = 30;
        
        public SparkleEffect(int x, int y) {
            super(x, y, 15);
        }
        
        @Override
        public void draw(Graphics2D g2d) {
            float progress = getProgress();
            float distance = MAX_DISTANCE * progress;
            float alpha = 1.0f - progress;
            
            g2d.setColor(new Color(1f, 1f, 0f, alpha));
            for (int i = 0; i < SPARKLE_COUNT; i++) {
                double angle = 2 * Math.PI * i / SPARKLE_COUNT;
                int sparkleX = (int)(x + Math.cos(angle) * distance);
                int sparkleY = (int)(y + Math.sin(angle) * distance);
                g2d.fillOval(sparkleX - 2, sparkleY - 2, 4, 4);
            }
        }
    }
    
    private class FadeEffect extends Effect {
        private Color color;
        private int width, height;
        
        public FadeEffect(int x, int y, int width, int height, Color color) {
            super(x, y, 30);
            this.width = width;
            this.height = height;
            this.color = color;
        }
        
        @Override
        public void draw(Graphics2D g2d) {
            float progress = getProgress();
            float alpha = 0.5f * (1.0f - progress);
            
            Color fadeColor = new Color(
                color.getRed() / 255f,
                color.getGreen() / 255f,
                color.getBlue() / 255f,
                alpha
            );
            
            g2d.setColor(fadeColor);
            g2d.fillRect(x, y, width, height);
        }
    }
}
