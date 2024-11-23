package structural.adapter;

/**
 * Target interface
 * This is the interface that the client code expects to work with
 */
public interface MediaPlayer {
    void play(String audioType, String fileName);
}
