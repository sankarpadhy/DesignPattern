package structural.adapter;

/**
 * Concrete implementation of AdvancedMediaPlayer for MP4 format
 */
public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        // Do nothing - this player only plays MP4
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file: " + fileName);
    }
}
