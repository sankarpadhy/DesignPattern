package structural.adapter;

/**
 * Concrete implementation of AdvancedMediaPlayer for VLC format
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // Do nothing - this player only plays VLC
    }
}
