package structural.adapter;

/**
 * Adaptee interface
 * This is the interface that needs to be adapted to work with the client code
 */
public interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}
