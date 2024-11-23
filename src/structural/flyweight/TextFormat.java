package structural.flyweight;

import java.awt.Color;

/**
 * Flyweight interface
 * Defines the interface through which flyweights can receive and act on extrinsic state
 */
public interface TextFormat {
    void apply(String text, int position, Color color);
}
